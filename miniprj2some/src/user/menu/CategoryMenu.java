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

			while (rs.next()) {
				System.out.println(rs.getInt(1) + ". " + rs.getString(2));
			}

			System.out.println("카테고리 번호를 선택하세요 ");

			int categoryNum = InputUtil.inputInt();

			showMenuType(categoryNum);

		} catch (SQLException e) {
			e.printStackTrace();
		
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}

	}
	
	// 문제상황 보여주세요 ~

	// menutype
	public void showMenuType(int categoryNum) {
		Connection conn = OracleDB.getOracleConnection();
		
		String sql = "select distinct(menu_type) from menu where category_idx = ? ";

		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		
		try {
			pstmt2 = conn.prepareStatement(sql);
		
//			pstmt2.setString(1, Integer.toString(categoryNum));
			
			pstmt2.setInt(1, categoryNum);
			
			rs = pstmt2.executeQuery();
			
			System.out.println("==========================");
			System.out.println("메뉴 종류를 선택하세요.");
			
			while (rs.next()) {
				String type = rs.getString(1);
				System.out.println(type);
			}

			System.out.println();
			System.out.println("메뉴 종류 이름을 한글로 입력해주세요.");
			System.out.print("메뉴 종류 : ");
			
			String menuType = InputUtil.inputStr();
			
			showMenu(menuType);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt2);
			OracleDB.close(rs);
		}

	}

	// 카테고리 불러와서 메뉴 보여주는 메소드
	public void showMenu(String menuType) {

		Connection conn = OracleDB.getOracleConnection();

		String sql2 = "select mn_idx, mn_name from menu where menu_type = ? ";

		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;

		try {
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, menuType);

			
			//tlfdgodgo 실행해주세요 ~ 
			//에러 보옂세요 ~~
			// 된건가요 ?? 저는 몰라요 된건지 ,,, ㅋㅋㅋㅋ
			// 문제상황 말씀해주세요 ~~~ 아마 ㅋ거ㅡ밋 
			//>> 그...카톡으로 사진 보냈었는데 잠시만요 다시 실행해보고 알려드릴게요 
			//네네 지금 해보세요 지금 바로 ㄱㄱㄱ
			// 드래그 친 부분은 없앨 수 있나요?
			
//			된거같아요 !!!!갑사하빈다
			//굿 ,,,, 화면 공유 종료할게요 ㅎㅎㅎ 축하드립니다 ~~~
			rs2 = pstmt2.executeQuery();
			
			System.out.println("==========================");
			System.out.println("메뉴를 선택하세요.");

			while (rs2.next()) {
				System.out.println(rs2.getInt(1) + ". " + rs2.getString(2));
			}

			System.out.println();
			System.out.println("메뉴 번호를 입력해주세요.");
			System.out.print("메뉴 번호 : ");

			int menuNum = InputUtil.inputInt();

			detail(menuNum);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt2);
			OracleDB.close(rs2);
		}
	}

	// 상세정보 불러와서 보여주는 메소드
	public void detail(int menuNum) {

		Connection conn = OracleDB.getOracleConnection();

		String sql2 = "select DETAIL, CAL, CAFFEINE, CARBOHYDRATE, SUGAR, FAT, PROTEIN, NATRIUM from menu where mn_idx = ? ";

		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;

		try {
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1, menuNum);

			rs2 = pstmt2.executeQuery();

			while (rs2.next()) {
				System.out.println("-----------[ 제 품 정 보 ]----------");
				System.out.println(rs2.getString(1));

				// 음식일 경우에만 영양정보 출력
					System.out.println("----------------------------------");
					System.out.println("칼로리: " + rs2.getString(2) + "kcal");
					System.out.println("----------------------------------");
					System.out.println("탄수화물: " + rs2.getString(3) + "g");
					System.out.println("----------------------------------");
					System.out.println("당류: " + rs2.getString(4) + "mg");
					System.out.println("----------------------------------");
					System.out.println("카페인: " + rs2.getString(5) + "mg");
					System.out.println("----------------------------------");
					System.out.println("나트륨: " + rs2.getString(6) + "g");
					System.out.println("----------------------------------");
					System.out.println("단백질: " + rs2.getString(7) + "g");
					System.out.println("----------------------------------");
					System.out.println("포화지방: " + rs2.getString(8) + "g");
					System.out.println("----------------------------------");
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
