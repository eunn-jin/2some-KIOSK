package user.menu;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import common.db.OracleDB;
import common.util.InputUtil;


public class DrinkMenu {

	
	public void drink(int categoryNum) {
		
		// DRINK 메뉴 리스트 보여주기 - (커피/ 프라푸치노/ 블렌디드/ 티바나)
		
		// 주석 처리 된 부분을 디비로 조회
		Connection conn = OracleDB.getOracleConnection();
		
		
		List<String> list = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		List<String> list3 = new ArrayList<String>();
		List<String> list4 = new ArrayList<String>();
		List<String> list5 = new ArrayList<String>();
		
		list.add(" 커피");
		list.add(" 프라푸치노");
		list.add(" 블렌디드");
		list.add(" 티바나");
		
		for(int i=0; i<list.size(); i++) {
			System.out.println(i+1 + ":" + list.get(i));
		}
		System.out.println();
		System.out.println("===================================");
		System.out.println();
		System.out.println("구매하실 음료를 선택해주세요.");
		
		// 음료 한가지 고르게 하기
		
		while(true) {
			int num = InputUtil.inputInt();
			
			switch(num) {
		case 1: // 에스프레소
			System.out.println("==============[ 커피 ]==============");
			System.out.println();
//			list2.add(" 카페 아메리카노 - 4000원");
//			list2.add(" 카페 라떼 - 5000원");
//			list2.add(" 돌체 라떼 - 5900원");
//			list2.add(" 카푸치노 - 5000원");
//			list2.add(" 에스프레소 - 4000원");
			
			for(int i=0; i<list2.size(); i++) {
				System.out.println(i+1 + ":" + list2.get(i));
			}
			
			System.out.println();
			System.out.println("===================================");
			// 디테일 보여주기 - 설명 / 영양성분
//			cd.coffeedetail();
//			cd.cfodetailll();
//			cfo.coffeeoption();
			break; 
		
		
		case 2: //프라푸치노
			System.out.println("============[ 프라푸치노 ]============");
			System.out.println();
//			list3.add(" 자바 칩 프라푸치노 - 6300원");
//			list3.add(" 초콜릿 크림 칩 프라푸치노 - 6000원");
//			list3.add(" 모카 프라푸치노 - 5700원");
//			list3.add(" 카라멜 프라푸치노 - 5700원");
//			list3.add(" 바닐라 크림 프라푸치노 - 5100원");
			
			for(int i=0; i<list3.size(); i++) {
				System.out.println(i+1 + ":" + list3.get(i));
			}
			
			System.out.println();
			System.out.println("===================================");
//			pp.purapuccinodetail();
//			pp.ppccinodetail();		
//			dfo.drinkoption();
			break; 
		
		
		case 3: //블렌디드	
			System.out.println("============[ 블렌디드 ]============");
			System.out.println();
//			list4.add(" 민트 초콜릿 칩 블렌디드 - 6300원");
//			list4.add(" 망고 바나나 블렌디드 - 6300원");
//			list4.add(" 레드 파워 스매시 블렌디드 - 6500원");
//			list4.add(" 피치 & 레몬 블렌디드 - 6300원");
//			list4.add(" 딸기 딜라이트 요거트 블렌디드 - 6300원");
			
			for(int i=0; i<list4.size(); i++) {
				System.out.println(i+1 + ":" + list4.get(i));
			}
			
			System.out.println();
			System.out.println("===================================");
//			bb.blendeddetail();
//			bb.bbdetail();
//			dfo.drinkoption();
			break; 
			
		case 4: // 티바나
			System.out.println("=============[ 티바나 ]==============");
			System.out.println();
//			list5.add(" 자몽 허니 블랙 티 - 5700원");
//			list5.add(" 얼 그레이 티 - 4500원");
//			list5.add(" 유스베리 티 - 4500원");
//			list5.add(" 민트 블렌드 티 - 4500원");
//			list5.add(" 히비스커스 블렌드 티 - 4500원");
			
			for(int i=0; i<list5.size(); i++) {
				System.out.println(i+1 + ":" + list5.get(i));
			}
			
			System.out.println();
			System.out.println("===================================");
//			tt.tevanadetail();
//			tt.tvdetail();
//			cfo.coffeeoption();
			break; 
			
		default:
			System.out.println("다시 입력하세요.");
			}
		}
			
					
					
	}//drink

	
}//class
