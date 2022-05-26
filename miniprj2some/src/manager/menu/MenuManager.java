package manager.menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import common.db.OracleDB;
import common.util.InputUtil;



public class MenuManager {
	
	public int categoryNum;
	public int menuNum;	
	public int detailNum;
		
	public void showCategory() {
		System.out.println("=============================");
		System.out.println("           메뉴 관리 ");
		System.out.println("=============================");	
		System.out.println("1. DRINK");
		System.out.println("2. FOODS");
		System.out.println("3. GOODS");
		System.out.println("0. 뒤로 가기");
		System.out.println();
		System.out.println("카테고리를 선택하시오");
		System.out.print("카테고리 번호 : ");
		this.categoryNum = InputUtil.sc.nextInt();
		System.out.println();
		System.out.println("----------------------------");
		System.out.println();
				
		if(this.categoryNum == 0) {
			//뒤로가기
		} else if(this.categoryNum != 1 && this.categoryNum != 2 && this.categoryNum != 3) {
			System.out.println("올바른 번호를 입력하시오");
		}
		
	}
	

	
	public void showMenu(int categoryNum) {
		this.categoryNum = categoryNum;
		
		
		Connection conn = OracleDB.getOracleConnection();
		
		String sql = "SELECT MN_IDX, MN_NAME "
				+ "FROM MENU "
				+ "INNER JOIN CATEGORY C ON C.CATEGORY_IDX = MENU.CATEGORY_IDX "
				+ "WHERE C.CATEGORY_IDX = ?"
				+ "ORDER BY MENU.MN_IDX"; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categoryNum);
			rs = pstmt.executeQuery();
			
			System.out.println("----------------------------");
			while(rs.next()) {
				   int mIdx = rs.getInt("MN_IDX");
				   String mName = rs.getString("MN_NAME");
				   System.out.println(mIdx + ". " + mName);
		   }
			System.out.println();
			System.out.println("----------------------------");
			System.out.println("[메뉴추가] 00 을 입력하시오");
			System.out.println("[메뉴수정] 메뉴 번호를 입력하시오");
			System.out.print("입력 : ");
			
			this.menuNum = InputUtil.sc.nextInt();
			System.out.println();
			System.out.println();
			
			if(this.menuNum == 00) {
				plus(); //메뉴추가 페이지로 이동
			} else {
				showDetail(this.menuNum);//해당 메뉴 수정 페이지로 이동
			}
			
			
		} catch (SQLException e) {
			System.out.println("SQL 예외 발생 - showMenu");
			e.printStackTrace();
			
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}
		
		
	}
	
	
	public void plus() {
		
		System.out.println("---------- 메뉴 추가 ----------");		
		System.out.println();
		InputUtil.inputStr();
		System.out.print("1. 상품 명 : ");
		String mName = InputUtil.inputStr();
		System.out.print("2. 가격 : ");
		int mPrice = InputUtil.inputInt();
		System.out.print("3. 상품 소개 : ");
		String mDetail = InputUtil.inputStr();
		
		Connection conn = OracleDB.getOracleConnection();
		PreparedStatement pstmt = null;
		
		
		
		//1, 2번 카테고리는 아래 상세 포함 insert]
		if(this.categoryNum == 3) {
			String sql = "INSERT INTO MENU(MN_IDX, CATEGORY_IDX, MN_NAME, PRICE, DETAIL"
					+ "VALUES(MENU_IDX_SEQ,?,?,?,?)";
		
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, this.categoryNum);
				pstmt.setString(2, mName);
				pstmt.setInt(3, mPrice);
				pstmt.setString(4, mDetail);
				int r = pstmt.executeUpdate();
				
				System.out.println();
				System.out.println("   [ 메뉴 등록이 완료되었습니다 ]   ");
				System.out.println();
				
				showMenu(this.categoryNum);
				
			} catch (SQLException e) {
				System.out.println("SQL 예외 발생 - showMenu");
				e.printStackTrace();
			} finally {
				OracleDB.close(conn);
				OracleDB.close(pstmt);
			}
			
		} else if(this.categoryNum == 1 || this.categoryNum == 2) {
			
			System.out.print("4. 칼로리 : ");
			int cal = InputUtil.inputInt();
			System.out.print("5. 카페인 : ");
			int caff = InputUtil.inputInt();
			System.out.print("6. 탄수화물 : ");
			int carb = InputUtil.inputInt();
			System.out.print("7. 당류 : ");
			int sugar = InputUtil.inputInt();
			System.out.print("8. 지방 : ");
			int fat = InputUtil.inputInt();
			System.out.print("9. 단백질 : ");
			int protein = InputUtil.inputInt();
			System.out.print("10. 나트륨 : ");
			int nat = InputUtil.inputInt();
			
			String sql = "INSERT INTO MENU(MN_IDX, CATEGORY_IDX, MN_NAME, PRICE, DETAIL, CAL, CAFFEINE, CARBOHYDRATE, "
					+ "SUGAR, FAT, PROTEIN, NATRIUM) "
					+ "VALUES(MENU_IDX_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?)";
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, this.categoryNum);
				pstmt.setString(2, mName);
				pstmt.setInt(3, mPrice);
				pstmt.setString(4, mDetail);
				pstmt.setInt(5, cal);
				pstmt.setInt(6, caff);
				pstmt.setInt(7, carb);
				pstmt.setInt(8, sugar);
				pstmt.setInt(9, fat);
				pstmt.setInt(10, protein);
				pstmt.setInt(11, nat);
				int r = pstmt.executeUpdate();
				
				
				if(r == 1 ) {
				System.out.println();
				System.out.println("   [ 메뉴 등록이 완료되었습니다 ]   ");
				System.out.println();
				
				showMenu(this.categoryNum);
				} else {
					System.out.println();
					System.out.println("   [ 메뉴 등록이 실패하였습니다 ]   ");
					System.out.println();
				}
				
			} catch (SQLException e) {
				System.out.println("SQL 예외 발생 - showMenu");
				e.printStackTrace();
			} finally {
				OracleDB.close(conn);
				OracleDB.close(pstmt);
			}
			
		} else {
				System.out.println("올바른 번호를 입력하시오");
		}
		
	}
	
	
	public void showDetail(int menuNum) {
		//이 안에 삭제도 존재 - 00번으로 ㄱㄱ
		this.menuNum = menuNum;
		System.out.println("-----------메뉴 수정-----------");
		System.out.println();
		
		//메뉴 상세 쫘르륵...
		Connection conn = OracleDB.getOracleConnection();
		
		String sql = "SELECT MN_IDX, MN_NAME, PRICE, DETAIL, CAL, CAFFEINE, CARBOHYDRATE, "
				+ "SUGAR, FAT, PROTEIN, NATRIUM "
				+ "FROM MENU "
				+ "WHERE MN_IDX = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, this.menuNum);
			rs = pstmt.executeQuery();
			
			System.out.println("----------------------------");
			while(rs.next()) {
				   int mIdx = rs.getInt("MN_IDX");
				   String mName = rs.getString("MN_NAME");
				   int mPrice = rs.getInt("PRICE");
				   String mDetail = rs.getString("DETAIL");
				   int cal = rs.getInt("CAL");
				   int caff = rs.getInt("CAFFEINE");
				   int carb = rs.getInt("CARBOHYDRATE");
				   int sugar = rs.getInt("SUGAR");
				   int fat = rs.getInt("FAT");
				   int protein = rs.getInt("PROTEIN");
				   int nat = rs.getInt("NATRIUM");
				   System.out.println("1. 상품 명 : " + mName);
				   System.out.println("2. 가격 : " + mPrice);
				   System.out.println("3. 상품 소개 : " + mDetail);
				   System.out.println("4. 칼로리 : " + cal);
				   System.out.println("5. 카페인 : " + caff);
				   System.out.println("6. 탄수화물 : " + carb);
				   System.out.println("7. 당류 : " + sugar);
				   System.out.println("8. 지방 : " + fat);
				   System.out.println("9. 단백질 : " + protein);
				   System.out.println("10. 나트륨 : " + nat);
				   System.out.println("9999. 상품 삭제");
				   
				   System.out.print("수정할 정보 번호 입력 : ");
				   this.detailNum = InputUtil.inputInt();
				   
				   if(this.detailNum == 9999) {
						delete();
					} else if(this.detailNum > 0 && this.detailNum < 11) {
						update(this.detailNum);
					} else {
						System.out.println("올바른 번호를 입력하시오");
					}
						
		   }
			
		
		} catch (SQLException e) {
			System.out.println("SQL 예외 발생 - showMenu");
			e.printStackTrace();
			
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}
		
		
	}
	
	
	public void update(int detailNum) {
		this.detailNum = detailNum;
		
		
		Connection conn = OracleDB.getOracleConnection();
		
		String sql = "UPDATE MENU SET ? = ? "
				+ "WHERE MN_IDX = ?";
		
		PreparedStatement pstmt = null;
		
		try {
			
			
			
			if(this.detailNum == 1) {
				pstmt = conn.prepareStatement(sql);
				System.out.print("1. 상품 명 : ");
				String mName = InputUtil.inputStr();
				String mn = "MN_NAME";
				pstmt.setString(1, "mn");
				pstmt.setString(2, mName); 
				pstmt.setInt(3, this.menuNum);
				int r = pstmt.executeUpdate();
				showDetail(this.menuNum);
			}	
/////////////////하는중임
		
		} catch (SQLException e) {
			System.out.println("SQL 예외 발생 - showMenu");
			e.printStackTrace();
			
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
		}
		
		
		
	
		
		
		
		
		
		
		
	}
	
		
		
		
	
	public void delete() {
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}	
