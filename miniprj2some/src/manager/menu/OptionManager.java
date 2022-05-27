package manager.menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.db.OracleDB;
import common.util.InputUtil;

public class OptionManager {
	
	public void showCategory() {
		System.out.println("=============================");
		System.out.println("         추가옵션 관리 ");
		System.out.println("=============================");	
		System.out.println("1. DRINK");
		System.out.println("2. FOODS");
		System.out.println("3. GOODS");
		System.out.println("0. 뒤로 가기");
		System.out.println();
		System.out.println("카테고리를 선택하시오");
		System.out.print("카테고리 번호 : ");
		int categoryNum = InputUtil.sc.nextInt();
		System.out.println();
		System.out.println("----------------------------");
		System.out.println();
				
		if(categoryNum == 0) {
			//뒤로가기
		} else if(categoryNum != 1 && categoryNum != 2 && categoryNum != 3) {
			System.out.println("올바른 번호를 입력하시오");
		}
		showMenu(categoryNum);
		
	}
	
	
	public void showMenu(int categoryNum) {
		
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
			System.out.println("----------------------------");
			System.out.println("메뉴 번호를 입력하시오");
			System.out.print("입력 : ");
			int menuNum = InputUtil.sc.nextInt();			
			System.out.println();
			
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
