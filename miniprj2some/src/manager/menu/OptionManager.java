package manager.menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import common.db.OracleDB;
import common.util.InputUtil;

public class OptionManager {
	
	//카테고리 보여줌
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
		int categoryNum = InputUtil.inputInt();
		System.out.println();
		
		if(categoryNum > 0 && categoryNum < 4) {
			showMenu(categoryNum);
		} else if(categoryNum == 0) {
			//뒤로가기
		} else if(categoryNum != 1 && categoryNum != 2 && categoryNum != 3) {
			System.out.println("올바른 번호를 입력하시오");
			showCategory();
		}
		
	}
	
	
	//메뉴 보여줌
	public void showMenu(int categoryNum) {
		
		Connection conn = OracleDB.getOracleConnection();
		
		String sql = "SELECT MN_IDX, MN_NAME "
				+ "FROM MENU "
				+ "INNER JOIN CATEGORY C ON C.CATEGORY_IDX = MENU.CATEGORY_IDX "
				+ "WHERE C.CATEGORY_IDX = ?"
				+ "ORDER BY MENU.MN_IDX"; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Set<Integer> mnIdxSet = new HashSet<>(); 
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categoryNum);
			rs = pstmt.executeQuery();
			
			System.out.println("----------------------------");
			while(rs.next()) {
				   int mIdx = rs.getInt("MN_IDX");
				   mnIdxSet.add(mIdx);
				   String mName = rs.getString("MN_NAME");
				   System.out.println(mIdx + ". " + mName);
		   }
			System.out.println();
			System.out.println("----------------------------");
			System.out.println("[옵션수정] 메뉴 번호를 입력하시오");
			System.out.println("[뒤로가기] 1111 을 입력하시오");
			System.out.print("입력 : ");		
			int menuNum = InputUtil.sc.nextInt();
			System.out.println();
			System.out.println();
			
		if(mnIdxSet.contains(menuNum)) {
			showOption(categoryNum, menuNum);
		} else if(menuNum == 1111) {
			showCategory();
		}  else {
			System.out.print("메뉴 번호를 다시 입력하세요");
			showMenu(categoryNum);
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
	
	
	public void showOption(int categoryNum, int menuNum) {
		//메뉴에 맵핑되어있는 추가옵션을 보여주는 method
		
		Connection conn = OracleDB.getOracleConnection();
		
		String sql = "SELECT ROWNUM,"
				+ "MN_NAME AS 메뉴명 "
				+ ", AO.ADI_OPT_NAME AS 옵션명 "
				+ "FROM MENU M "
				+ "INNER JOIN MENU_ADDITIONAL_OPTION_MAP MAO ON M.MN_IDX = MAO.MN_IDX "
				+ "INNER JOIN ADDITIONAL_OPTION AO ON AO.ADI_OPT_IDX = MAO.ADI_OPT_IDX "
				+ "WHERE M.MN_IDX = ?"
				+ "ORDER BY ROWNUM"; 
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, menuNum);
			rs = pstmt.executeQuery();
			
			int mIdx = rs.getInt("MN_IDX");
			System.out.println("----------" +  mIdx + "----------");	
			while(rs.next()) {
				int rownum = rs.getInt("ROWNUM");
			   String opName = rs.getString("ADI_OPT_NAME AS");
			   System.out.println(rownum + ". " + opName);
		   }
			System.out.println();
			System.out.println("----------------------------");
			System.out.println("[옵션수정] 번호를 입력하시오");
			System.out.println("[뒤로가기] 1111 을 입력하시오");
			System.out.print("입력 : ");		
			int optionNum = InputUtil.sc.nextInt();
			System.out.println();
			System.out.println();
			

			
		} catch (SQLException e) {
			System.out.println("SQL 예외 발생 - showOption");
			e.printStackTrace();
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	

}
