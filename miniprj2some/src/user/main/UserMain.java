package user.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.db.OracleDB;
import common.util.InputUtil;
import user.menu.CategoryMenu;

public class UserMain {

			
	public static void Usermain() {
		
		// 환영인사 추가..? (자유)
		CategoryMenu cm = new CategoryMenu();
		cm.showCategory();
		
		
//		System.out.println("카테고리를 선택하세요.");
//		System.out.print("카테고리 번호 : ");
//		int categoryNum = InputUtil.inputInt();
//		
//		
//		
//		Connection conn = OracleDB.getOracleConnection();
//		
//		String sql = "SELECT MN_NAME FROM MENU WHERE CATEGORY_IDX = ? ";
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//		pstmt = conn.prepareStatement(sql);
//		pstmt.setInt(1, categoryNum);
//		rs = pstmt.executeQuery();
//
//		while(rs.next()) {
//			System.out.println(rs.getString(1));
//		 }
		 
		
		
//		// 메뉴 객체 생성
//		DrinkMenu dm = new DrinkMenu();
////		
////		
////		
//		// 메뉴 카테고리 보여준 후 이동.
//		List<String> list = new ArrayList<String>();
//		System.out.println("= = = = = =  category = = = = = = =  ");
//		System.out.println();
//		list.add("    [ DRINK ]  [ FOOD ]  [ GOODS ]");
//		
//
//		for(int i=0; i<list.size(); i++) {
//			System.out.println(list.get(i));
//		}
//		
//		System.out.println();
//		System.out.println("= = = = = = = = = = = = = = = = = =");
//		System.out.println();
//		System.out.println("카테고리를 선택하세요.");
//		System.out.print("카테고리 번호 : ");
//		int categoryNum = InputUtil.inputInt();
//		
//		
//		
//		
//		// 유저가 카테고리 번호 입력한대로 이동
//		while(true) {
//			
//			switch(categoryNum) {
//		case 1: 
//			System.out.println("==============[ DRINK ]==============");
//			System.out.println();
//			dm.drink();  // 각 클래스로 이동해야 되니깐 호출
//			break; 
//		
//		case 2: 
//			System.out.println("==============[ FOOD ]===============");
//			System.out.println();
////			fd.food();
//			break;
//		
//			
//		case 3: 
//			System.out.println("==============[ GOODS ]===============");
//			System.out.println();
////			gd.goods();
//			break;
//			
//		default:
//			System.out.println("다시 입력하세요.");
//			}
//		}
		
		
//		}	
	}
	
	

}//class
