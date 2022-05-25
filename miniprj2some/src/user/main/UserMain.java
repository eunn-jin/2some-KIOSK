package user.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.db.OracleDB;
import common.util.InputUtil;

public class UserMain {

	public static void Usermain() {
		System.out.println("여기는 사용자 부분..!");
		
		
		// 메뉴 카테고리 보여준 후 이동.
		List<String> list = new ArrayList<String>();
		System.out.println("= = = = = =  category = = = = = = =  ");
		System.out.println();
		list.add("    [ DRINK ]  [ FOOD ]  [ GOODS ]");
		

		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		System.out.println();
		System.out.println("= = = = = = = = = = = = = = = = = =");
		System.out.println();
		System.out.println("카테고리를 선택하세요.");
		System.out.print("카테고리 번호 : ");
		int categoryNum = InputUtil.inputInt();
		
		
		// 카테고리 디비 조회하기
		Connection conn = OracleDB.getOracleConnection();
	
		String sql = "SELECT CATEGORY_IDX, CATEGORY_NAME FROM CATEGORY = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categoryNum);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				   int idx = rs.getInt("CATEGORY_IDX");
				   String name = rs.getString("CATEGORY_NAME");
				   System.out.println(idx + ". " + name);
		   }
			
		} catch (SQLException e) {
			System.out.println("SQL 예외 발생 !!");
			e.printStackTrace();
			
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}
		
		// 유저가 카테고리 번호 입력한대로 이동
		while(true) {
			
			switch(categoryNum) {
		case 1: 
			System.out.println("==============[ DRINK ]==============");
			System.out.println();
//			cf.food();  // 각 클래스로 이동해야 되니깐 호출
			break; 
		
		case 2: 
			System.out.println("==============[ FOOD ]===============");
			System.out.println();
//			fd.food();
			break;
		
			
		case 3: 
			System.out.println("==============[ GOODS ]===============");
			System.out.println();
//			gd.goods();
			break;
			
		default:
			System.out.println("다시 입력하세요.");
			}
		}
		
		
	}
	
	

}//class
