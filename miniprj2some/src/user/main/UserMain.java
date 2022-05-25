package user.main;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import manager.menu.OracleDB;
import user.menu.MyUtil;

public class UserMain {

	public static void Usermain() {
		System.out.println("여기는 사용자 부분..!");
		
		
		// 메뉴 카테고리 보여준 후 이동.
		List<String> list = new ArrayList<String>();
		System.out.println("= = = = = =  category = = = = = = =  ");
		System.out.println();
		list.add("    [ DRINK ]  [ FOODS ]  [ GOODS ]");
		

		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		System.out.println();
		System.out.println("= = = = = = = = = = = = = = = = = =");
		System.out.println();
		System.out.println("카테고리를 선택하세요.");
		System.out.print("카테고리 번호 : ");
		int categoryNum = MyUtil.scInt();
		
		Connection conn = OracleDB.getOracleConnection();
		
//		while(true) {
//			
//			switch(categoryNum) {
//		case 1: 
//			System.out.println("==============[ DRINK ]==============");
//			System.out.println();
////			cf.drink();  // 각 클래스로 이동해야 되니깐 호출
//			break; 
//		
//		case 2: 
//			System.out.println("==============[ FOODS ]===============");
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
		
		
		
	}

}//class
