package user.menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import common.db.OracleDB;
import common.util.InputUtil;
import user.main.AdditionalOption;
import user.main.Product;
import user.main.UserMain;

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
			System.out.println("0. 뒤로가기");

			int categoryNum = InputUtil.inputInt();
			System.out.println();

			if(categoryNum == 0) {
				return;
			} else {
				showMenu(categoryNum);				
			}

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
			System.out.println("0. 뒤로가기");

			while (rs2.next()) {
				System.out.println(rs2.getInt(1) + ". " + rs2.getString(2));
			}

			System.out.println();
			System.out.println("===================================");
			System.out.println();
			System.out.println("메뉴 번호를 입력해주세요.");

			int menuNum = InputUtil.inputInt();

			// 디테일 메소드 연결
			if(menuNum == 0) {
				return;
			} else {
				detail(menuNum, categoryNum);				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt2);
			OracleDB.close(rs2);
		}
	}

	// 디테일 메소드
	public void detail(int menuNum, int categoryNum) {

		Connection conn = OracleDB.getOracleConnection();

		String sql2 = "SELECT DETAIL, CAL, CAFFEINE, CARBOHYDRATE, SUGAR, FAT, PROTEIN, NATRIUM, MN_NAME, PRICE FROM MENU WHERE MN_IDX = ? ";

		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;

		String mnName = ""; // 메뉴 이름
		int price = 0;   // 가격

		try {
			pstmt2 = conn.prepareStatement(sql2);

			pstmt2.setInt(1, menuNum);

			rs2 = pstmt2.executeQuery();

			while (rs2.next()) {
				mnName = rs2.getString(9); // 메뉴 이름
				price = rs2.getInt(10); // 가격

				System.out.println();
				System.out.println("-----------[ 제 품 정 보 ]----------");
				System.out.println(rs2.getString(1));

				if (categoryNum != 3) {
					System.out.println("----------------------------------");
					System.out.println("칼로리: " + rs2.getString(2) + "kcal"); // 칼로리
					System.out.println("----------------------------------");
					System.out.println("카페인: " + rs2.getString(3) + "g"); // 카페인
					System.out.println("----------------------------------");
					System.out.println("탄수화물: " + rs2.getString(4) + "mg"); // 탄수화물
					System.out.println("----------------------------------");
					System.out.println("당류: " + rs2.getString(5) + "mg"); // 당류
					System.out.println("----------------------------------");
					System.out.println("지방: " + rs2.getString(6) + "g"); // 지방
					System.out.println("----------------------------------");
					System.out.println("단백질: " + rs2.getString(7) + "g"); // 단백질
					System.out.println("----------------------------------");
					System.out.println("나트륨: " + rs2.getString(8) + "g"); // 나트륨
					System.out.println("----------------------------------");
				}
			} // while문

			System.out.println("해당 제품을 구입하시겠습니까? (Y/ N)");
			String check = InputUtil.inputStr();
			
			if("Y".equalsIgnoreCase(check)) {
				// 선택한 메뉴가 DRINK일 경우에만 옵션 메서드 호출, 이 외(FOOD, GOODS)에는 바로 장바구니로!
				if (categoryNum == 1) {
					showOption(menuNum, price, mnName);
				} else {
					int n = getNum();
					setProduct(menuNum, n, price, mnName);
				}				
			} else {
				return;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt2);
			OracleDB.close(rs2);
		}
	}
	
	public int getNum() {
		System.out.println("몇 개를 구입하시겠습니까?");
		return InputUtil.inputInt();
	}
	

	// 옵션 선택
	public void showOption(int menuNum, int price, String mnName) {
		Connection conn = OracleDB.getOracleConnection();

		// 옵션 종류 조회용 - Type
		PreparedStatement pstmt_optionType = null;
		ResultSet rs_optionType = null;

		// 옵션 선택지 조회용 - Choice
		PreparedStatement pstmt_optionChoice = null;
		ResultSet rs_optionChoice = null;

		// 선택한 옵션 가격 조회용 - Price
		PreparedStatement pstmt_optionPrice = null;
		ResultSet rs_optionPrice = null;

		int optIdx = 0;
		int optionPrice = 0;
		int sum = price;

		try {

			String optionName = "";
			String optionChoiceName = "";

			// ******************* sql String 미리 생성해두기 *********************************
			String sql_optionPrice = "";

			String sql_optionChoice = "";
			
			String sql_optionType = "SELECT distinct(AO.ADI_OPT_NAME) "
						+ "FROM MENU M "
						+ "INNER JOIN MENU_ADDITIONAL_OPTION_MAP MAO ON M.MN_IDX = MAO.MN_IDX "
						 + "INNER JOIN ADDITIONAL_OPTION AO ON AO.ADI_OPT_NAME = MAO.ADI_OPT_NAME "
						+ "WHERE M.MN_IDX =  ? ";
						

			// ********************* 옵션 종류 조회하기 **************************************
			System.out.println();
			System.out.println();
			System.out.println("===========[ 추 가 옵 션 ]===========");

			pstmt_optionType = conn.prepareStatement(sql_optionType);
			
			pstmt_optionType.setInt(1, menuNum);

			rs_optionType = pstmt_optionType.executeQuery();
			// ****************************************************************************

			
			
			// ********************* 옵션 종류 출력 while문 **************************************
			while (rs_optionType.next()) {

				optionName = rs_optionType.getString(1); 

				System.out.println(optionName + " ?"); 

//				------------------------

				// ********************* 옵션 이름 가격 조회하기 **************************************

				// 옵션 종류에 따른 옵션 이름, 가격 조회 쿼리 ex) TALL, 500
				sql_optionChoice = "select ADI_OPT_IDX, ADI_OPT_CHOICE, NVL(ADI_OPT_PRICE, 0) from ADDITIONAL_OPTION where ADI_OPT_NAME = ? ";

				pstmt_optionChoice = conn.prepareStatement(sql_optionChoice);

				pstmt_optionChoice.setString(1, optionName); // where절에 변수 할당

				rs_optionChoice = pstmt_optionChoice.executeQuery();

				while (rs_optionChoice.next()) {
					System.out.println(rs_optionChoice.getInt(1) + ". " + rs_optionChoice.getString(2) + " ("
							+ rs_optionChoice.getInt(3) + "원)");
				}

				// ********************* 선택한 옵션 가격, 이름 조회 ************************************** //  //

				sql_optionPrice = "select ADI_OPT_IDX, ADI_OPT_PRICE, ADI_OPT_CHOICE from ADDITIONAL_OPTION where ADI_OPT_IDX = ? ";

				// 사용자에게 답변받은 것을 optIdx변수에 저장
				optIdx = InputUtil.inputInt();

				pstmt_optionPrice = conn.prepareStatement(sql_optionPrice);

				pstmt_optionPrice.setInt(1, optIdx);

				rs_optionPrice = pstmt_optionPrice.executeQuery();

				// ********************* 옵션 가격, 이름 저장 while문
				// **************************************
				while (rs_optionPrice.next()) {
					optionPrice = rs_optionPrice.getInt(2);
					optionChoiceName = rs_optionPrice.getString(3);
				}
				
				AdditionalOption option = new AdditionalOption(optIdx, optionChoiceName);
				UserMain.optionList.add(option);
				// ****************************************************************************

				// 선택한 옵션 가격을 총액 변수에 저장
				sum = sum + optionPrice;

				System.out.println();
				System.out.println("sum : " + sum); // -- 여기도 큰 틀처럼 계속 반복
				System.out.println();
			}// 큰 while문.

			int num = getNum();
			// 장바구니 호출
			setProduct(menuNum, num, sum, mnName);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt_optionType);
			OracleDB.close(rs_optionType);
			OracleDB.close(pstmt_optionType);
			OracleDB.close(rs_optionPrice);
			OracleDB.close(pstmt_optionChoice);
			OracleDB.close(rs_optionChoice);
		}
	}


	// 장바구니 & 선택한 옵션
	public void setProduct(int mnIdx, int num, int price, String name) {
		
		// Product객체를 생성해서 
		Product product = new Product(mnIdx, num, price, name);

		// linkedlist에 넣어주는 작업 - linkedlist main에 있음.
		UserMain.orderlist.add(product);
		
		System.out.println("장바구니에 저장이 완료되었습니다.");
	}//setProduct

}