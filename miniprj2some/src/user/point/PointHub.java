package user.point;

import common.util.InputUtil;
import user.info.Customer;
public class PointHub {
	
	public static void plaitCustomersPoint() {
		
		System.out.println("=========================");
		
		System.out.println("");
		System.out.println("마이 멤버쉽 페이지에 접속하였습니다.");
		
		boolean bl = true;
		while(bl = true) {
		
		Customer.sleepThread();
		System.out.println("");
		System.out.println("==========================");
		System.out.println("======[1. 스템프 조회 ]======");
		System.out.println("======[2. 스템프 적립 ]======");
		System.out.println("======[3. 스템프 사용 ]======");
		System.out.println("======[4. 뒤로 가기  ]======");
		System.out.println("==========================");
		System.out.println("");
		
		System.out.println("사용하시려는 기능의 번호를 입력하시기를 바랍니다.");
		
		int a = InputUtil.inputInt();
		
		switch(a) {
		case 1 : System.out.println("로그인을 선택하셨습니다."); /*스템프 조회 메소드*/ break;
		case 2 : System.out.println("회원가입을 선택하셨습니다."); /*스템프 적립 메소드*/ break;
		case 3 : System.out.println("뒤로가기를 선택하셨습니다."); /*스템프 사용(할인) 메소드*/ break;
		case 4 : System.out.println("뒤로가기를 선택하셨습니다."); user.info.CustomerHub.plaitLoginJoin(); break;
		default : System.out.println("잘못된 번호입니다. 다시 입력해주세요."); bl = false; break;
		}
		
		}
		
		
		
		
	}

}
