package user.menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.db.OracleDB;
import common.util.InputUtil;

public class CategoryMenu {
	
	// 카테고리 보여주는 메소드
	public void showCategory() {

		Connection conn = OracleDB.getOracleConnection();

		String sql = "SELECT CATEGORY_IDX, CATEGORY_NAME FROM CATEGORY";

		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			System.out.println("==========[ 카 테 고 리 ]===========");

			// 나중에 공부할 때 보기 쉬우라고 주석 달았음. 혹시나 까먹을까봐 (혼자 주절주절..)
			// rs.getInt(1): 첫번째인 CATEGORY_IDX가 쫘르륵. // rs.getString(2): 두번째인 CATEGORY_NAME이 쫘르륵.
			while (rs.next()) {
				System.out.println(rs.getInt(1) + ". " + rs.getString(2));    
			}

			System.out.println("카테고리 번호를 선택하세요 ");

			int categoryNum = InputUtil.inputInt();

			showMenu(categoryNum);
			
		} catch (SQLException e) {
			e.printStackTrace();
		
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}

	}

	// 카테고리 불러와서 메뉴 보여주는 메소드
	public void showMenu(int categoryNum) {

		Connection conn = OracleDB.getOracleConnection();

		// menu 테이블에서 mn_idx, mn_name을 조회할거임. category_idx가 ?인 부분 중에서.
		// ? 에 들어가는 부분은 밑에 있는 try문 안에 있는 pstmt2.setInt(1, categoryNum);에서 해결할거임.
		String sql2 = "select mn_idx, mn_name from menu where category_idx = ? ";

		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;

		try {
			pstmt2 = conn.prepareStatement(sql2);
			
			// where category_idx = ? -> where 뒤에 category_idx 한개(1)만 있으니까 1 쓴거임. 물음표 자리에 아까 입력받은 categoryNum이 들어감.
			pstmt2.setInt(1, categoryNum);   

			rs2 = pstmt2.executeQuery();
			
			System.out.println("==========[ 메 뉴 ]===========");
			System.out.println("메뉴를 선택하세요.");

			while (rs2.next()) {
				System.out.println(rs2.getInt(1) + ". " + rs2.getString(2));
			}

			System.out.println();
			System.out.println("메뉴 번호를 입력해주세요.");
			
			int menuNum = InputUtil.inputInt();
			
			// 아까 입력받은 categoryNum이 3이 아닌 경우만! -> 1: DRINK, 2: FOOD만 해당.
			boolean isFood = (categoryNum != 3);
			
			// 디테일 메소드 연결
			detail(menuNum, isFood);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt2);
			OracleDB.close(rs2);
		}
	}

	// 디테일 메소드
	public void detail(int menuNum, boolean isFood) {

		Connection conn = OracleDB.getOracleConnection();

		String sql2 = "select DETAIL, CAL, CAFFEINE, CARBOHYDRATE, SUGAR, FAT, PROTEIN, NATRIUM from menu where mn_idx = ? ";

		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;

		try {
			pstmt2 = conn.prepareStatement(sql2);
			
			// where mn_idx = ? -> where 뒤에 mn_idx 한개(1)만 있으니까 1 쓴거임. 물음표 자리에 아까 입력받은 menuNum이 들어감.
			pstmt2.setInt(1, menuNum);

			rs2 = pstmt2.executeQuery();

			while (rs2.next()) {
				System.out.println("-----------[ 제 품 정 보 ]----------");
				System.out.println(rs2.getString(1));
				
//				CAL, CAFFEINE, CARBOHYDRATE, SUGAR, FAT, PROTEIN, NATRIUM
//				칼로리, 카페인, 탄수화물. 당류. 지방, 단백질, 나트륨
				
				if(isFood) {
					System.out.println("----------------------------------");
					System.out.println("칼로리: " + rs2.getString(2) + "kcal");  // 칼로리
					System.out.println("----------------------------------");
					System.out.println("카페인: " + rs2.getString(3) + "g");   // 카페인
					System.out.println("----------------------------------");  
					System.out.println("탄수화물: " + rs2.getString(4) + "mg");       // 탄수화물
					System.out.println("----------------------------------");
					System.out.println("당류: " + rs2.getString(5) + "mg");     // 당류
					System.out.println("----------------------------------");
					System.out.println("지방: " + rs2.getString(6) + "g");         // 지방
					System.out.println("----------------------------------");
					System.out.println("단백질: " + rs2.getString(7) + "g");        // 단백질
					System.out.println("----------------------------------");
					System.out.println("나트륨: " + rs2.getString(8) + "g");      // 나트륨
					System.out.println("----------------------------------");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt2);
			OracleDB.close(rs2);
		}
	}

}