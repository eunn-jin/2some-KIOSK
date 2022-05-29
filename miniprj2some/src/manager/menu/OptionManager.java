package manager.menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import common.db.OracleDB;
import common.util.InputUtil;
import manager.main.ManagerMain;


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
			ManagerMain.managerHome();
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
			System.out.println("SQL 예외 발생 - OptionManager.showMenu");
			e.printStackTrace();
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}
		
	}
	
	
	public void showOption(int categoryNum, int menuNum) {

			
			System.out.println("----------------------------");
			System.out.println();
			System.out.println("[옵션추가] 1 입력");
			System.out.println("[옵션삭제] 2 입력");
			System.out.println("[뒤로가기] 1111 입력");
			System.out.print("입력 : ");		
			int selectNum = InputUtil.sc.nextInt();
			System.out.println();
			System.out.println();
			
			if(selectNum == 1) {
				plus(categoryNum, menuNum);
			} else if (selectNum == 2) {
				delete(categoryNum, menuNum);
			} else if (selectNum == 1111) {
				showMenu(categoryNum);
			} else {
				System.out.println("올바른 번호를 입력하시오");
				showOption(categoryNum, menuNum);
			}

		
	}
	
	
	
	
	public void plus(int categoryNum, int menuNum) { 
		
		System.out.println("[추가] 할 옵션번호를 입력하시오");
		System.out.println("[뒤로가기] 1111 을 입력하시오");
		showMappingOption(categoryNum, menuNum);
		List<String> notMappingOptionList = showNotMappingOption(categoryNum, menuNum);
		if(notMappingOptionList.size() == 0) {
			System.out.println("[ 추가할 수 있는 추가옵션이 없습니다 ]");
			System.out.println("[ 메뉴 화면으로 이동합니다 ]");
			showMenu(categoryNum);
		}
		System.out.println("---------------------------");
		System.out.print("입력 : ");
		int optionNum = InputUtil.inputInt();
		
		if(optionNum == 1111) {
			showMenu(categoryNum);
		}
		
		Connection conn = OracleDB.getOracleConnection();
		PreparedStatement pstmt = null;
		
		//1, 2번 카테고리는 아래 상세 포함 insert
		
			String sql = "INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (?, ?)";
		
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, menuNum);
				pstmt.setString(2, notMappingOptionList.get(optionNum - 1));
				int r = pstmt.executeUpdate();
				
				if(r == 1) {
					System.out.println();
					System.out.println("   [ 옵션 추가 완료되었습니다 ]   ");
					System.out.println();
				} else {
					System.out.println();
					System.out.println("   [ 옵션 추가 실패하였습니다 ]   ");
					System.out.println();
				}
				
			} catch (SQLException e) {
				System.out.println("SQL 예외 발생 - OptionManager.plus");
				e.printStackTrace();
			} finally {
				OracleDB.close(conn);
				OracleDB.close(pstmt);
			}
			
			
			
			//옵션 추가 후 옵션목록 갱신
			plus(categoryNum, menuNum);
		
		
		
	}
	
	
	public void delete(int categoryNum, int menuNum) {
		
		System.out.println("[삭제] 할 옵션번호를 입력하시오");
		System.out.println("[뒤로가기] 1111 을 입력하시오");
		List<String> mappingOptionList = showMappingOption(categoryNum, menuNum);
		showNotMappingOption(categoryNum, menuNum);
		if(mappingOptionList.size() == 0) {
			System.out.println("[ 추가할 수 있는 추가옵션이 없습니다 ]");
			System.out.println("[ 메뉴 화면으로 이동합니다 ]");
			showMenu(categoryNum);
		}
		System.out.println("---------------------------");
		System.out.print("입력 : ");
		int optionNum = InputUtil.inputInt();
		
		if(optionNum == 1111) {
			showMenu(categoryNum);
		}
		
		
		
		Connection conn = OracleDB.getOracleConnection();
		PreparedStatement pstmt = null;
		
		
			String sql = "DELETE FROM MENU_ADDITIONAL_OPTION_MAP "
					+ "WHERE MN_IDX = ? "
					+ "AND ADI_OPT_NAME = ?";
		
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, menuNum);
				pstmt.setString(2, mappingOptionList.get(optionNum - 1));
				int r = pstmt.executeUpdate();
				
				if(r == 1) {
					System.out.println();
					System.out.println("   [ 옵션 삭제 완료되었습니다 ]   ");
					System.out.println();
				} else {
					System.out.println();
					System.out.println("   [ 옵션 삭제 실패하였습니다 ]   ");
					System.out.println();
				}
				
			} catch (SQLException e) {
				System.out.println("SQL 예외 발생 - OptionManager.delete");
				e.printStackTrace();
			} finally {
				OracleDB.close(conn);
				OracleDB.close(pstmt);
			}
			
			
			
			delete(categoryNum, menuNum);
		
	}
	
	
	public List<String> showMappingOption(int categoryNum, int menuNum) {
		
		Connection conn = OracleDB.getOracleConnection();
		
		
		String sql = "SELECT "
				+ "AO.ADI_OPT_NAME AS 옵션명 "
				+ "FROM MENU M "
				+ "INNER JOIN MENU_ADDITIONAL_OPTION_MAP MAO ON M.MN_IDX = MAO.MN_IDX "
				+ "INNER JOIN ADDITIONAL_OPTION AO ON AO.ADI_OPT_NAME = MAO.ADI_OPT_NAME "
				+ "WHERE M.MN_IDX = ? "
				+ "GROUP BY AO.ADI_OPT_NAME";
		
			
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<String>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, menuNum);
			rs = pstmt.executeQuery();
			
			System.out.println("-------현재 존재하는 옵션-------");
			
			while(rs.next()) {
				String opName = rs.getString("옵션명");
				list.add(opName);
				System.out.println(list.size() + ". " + opName);
			}
			

		} catch (SQLException e) {
			System.out.println("SQL 예외 발생 - OptionManager.showMappingOption");
			e.printStackTrace();
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}
		
		return list;
	}
	
	
	
	
	public List<String> showNotMappingOption(int categoryNum, int menuNum) {
		
		Connection conn = OracleDB.getOracleConnection();
		
		String sql = "SELECT  AO.ADI_OPT_NAME "
				+ "FROM "
				+ "ADDITIONAL_OPTION AO "
				+ "WHERE AO.ADI_OPT_NAME NOT IN "
				+ "      (SELECT ADI_OPT_NAME FROM MENU_ADDITIONAL_OPTION_MAP WHERE MN_IDX = ?) "
				+ "GROUP BY ADI_OPT_NAME";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<String>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, menuNum);
			rs = pstmt.executeQuery();
			
			System.out.println("--------추가 가능한 옵션--------");

			
			while(rs.next()) {
				String opName = rs.getString("ADI_OPT_NAME");
				list.add(opName);
				System.out.println(list.size() + ". " +  opName);
			}
			
			

		} catch (SQLException e) {
			System.out.println("SQL 예외 발생 - OptionManager.showNotMappingOption");
			e.printStackTrace();
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}
			
		
		return list;
	}
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

