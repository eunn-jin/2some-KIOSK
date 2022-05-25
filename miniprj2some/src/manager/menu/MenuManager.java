package manager.menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class MenuManager {
	
	public int categoryNum;
	public int menuNum;	
	
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
		}
		
	}
	

	
	public void showMenu(int categoryNum) {
		this.categoryNum = categoryNum;
		
		
		Connection conn = OracleDB.getOracleConnection();
		
		String sql = "SELECT ROWNUM, MN_NAME "
				+ "FROM MENU "
				+ "INNER JOIN CATEGORY C ON C.CATEGORY_IDX = MENU.CATEGORY_IDX "
				+ "WHERE C.CATEGORY_IDX = ?"; //메뉴에 어떻게 숫자 붙이지? -> 일단 ROWNUM으로 해놨음
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categoryNum);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				   int mIdx = rs.getInt("ROWNUM");
				   String mName = rs.getString("MN_NAME");
				   System.out.println(mIdx + ". " + mName);
		   }
			
			System.out.println("----------------------------");
			System.out.println("[메뉴추가] + 를 입력하시오");
			System.out.println("[메뉴수정] 메뉴 번호를 입력하시오");
			System.out.print("입력 : ");
			
			
		} catch (SQLException e) {
			System.out.println("SQL 예외 발생 - showMenu");
			e.printStackTrace();
			
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}
		
	
	}
	
	
}	
