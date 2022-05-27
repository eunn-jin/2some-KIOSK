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
			System.out.println();

			while (rs.next()) {
				System.out.println(rs.getInt(1) + ". " + rs.getString(2));    
			}

			System.out.println();
			System.out.println("===================================");
			System.out.println();
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

		String sql2 = "SELECT MN_IDX, MN_NAME FROM MENU WHERE CATEGORY_IDX = ? ";

		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;

		try {
			pstmt2 = conn.prepareStatement(sql2);
			
			pstmt2.setInt(1, categoryNum);   

			rs2 = pstmt2.executeQuery();
			
			System.out.println("=============[ 메 뉴 ]==============");
			System.out.println("메뉴를 선택하세요.");
			System.out.println();

			while (rs2.next()) {
				System.out.println(rs2.getInt(1) + ". " + rs2.getString(2));
			}

			System.out.println();
			System.out.println("===================================");
			System.out.println();
			System.out.println("메뉴 번호를 입력해주세요.");
			
			int menuNum = InputUtil.inputInt();
			
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

		String sql2 = "SELECT DETAIL, CAL, CAFFEINE, CARBOHYDRATE, SUGAR, FAT, PROTEIN, NATRIUM FROM MENU WHERE MN_IDX = ? ";
		
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;

		try {
			pstmt2 = conn.prepareStatement(sql2);
			
			pstmt2.setInt(1, menuNum);

			rs2 = pstmt2.executeQuery();

			while (rs2.next()) {
				System.out.println("-----------[ 제 품 정 보 ]----------");
				System.out.println(rs2.getString(1));
				
				
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