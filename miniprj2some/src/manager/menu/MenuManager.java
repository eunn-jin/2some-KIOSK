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
//		this.categoryNum = InputUtil.sc.nextInt();
		System.out.println();
		System.out.println("----------------------------");
		System.out.println();
				
		if(this.categoryNum == 0) {
			//뒤로가기
		}
		
	}
	

	
	public void showMenu(int categoryNum) {
		this.categoryNum = categoryNum;
		
		
//		Connection conn = OracleDB.getOracleConnection();
//		
//		String sql = "SELECT ROWNUM, MN_NAME "
//				+ "FROM MENU "
//				+ "INNER JOIN CATEGORY C ON C.CATEGORY_IDX = MENU.CATEGORY_IDX "
//				+ "WHERE C.CATEGORY_IDX = ?"; //메뉴에 어떻게 숫자 붙이지? -> 일단 ROWNUM으로 해놨음
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, categoryNum);
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				   int mIdx = rs.getInt("ROWNUM");
//				   String mName = rs.getString("MN_NAME");
//				   System.out.println(mIdx + ". " + mName);
//		   }
//			
//			System.out.println("----------------------------");
//			System.out.println("[메뉴추가] 00 을 입력하시오");
//			System.out.println("[메뉴수정] 메뉴 번호를 입력하시오");
//			System.out.print("입력 : ");
//			
//			this.menuNum = InputUtil.sc.nextInt();
//			System.out.println();
//			System.out.println();
//			
//			if(menuNum == 00) {
//				plus(); //메뉴추가 페이지로 이동
//			} else {
//				update();//해당 메뉴 수정 페이지로 이동
//			}
//			
//			
//		} catch (SQLException e) {
//			System.out.println("SQL 예외 발생 - showMenu");
//			e.printStackTrace();
//			
//		} finally {
//			OracleDB.close(conn);
//			OracleDB.close(pstmt);
//			OracleDB.close(rs);
//		}
		
	//db, util 클래스 추가 후 수정
		
	}
	
	
	public void plus() {
		
		System.out.println("---------- 메뉴 추가 ----------");		
		System.out.println("( * 표시는 필수 입력입니다 )");		
		
		
		
		
		
		
		
	}
	
	
	public void update() {
		//이 안에 삭제도 존재 - 00번으로 ㄱㄱ
		System.out.println("-----------메뉴 수정-----------");
		System.out.println();
		//메뉴 상세 쫘르륵...
		
		//수정할 상세 번호 입력받고
		//수정..
		//어쩌고..
		
		
		
		
	}
	
	
	
	
	
	public void delete() {
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}	
