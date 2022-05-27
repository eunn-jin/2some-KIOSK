package user.info;

import common.util.InputUtil;

public class CustomerHub {
	
	
	public static void plaitLoginJoin() {
		
		System.out.println("=========================");
		System.out.println("포인트 사용 및 적립을 위하여 허브 페이지로 이동하였습니다.");
		System.out.println("해당 페이지에서는 로그인 및 회원가입이 가능합니다.");
		
		boolean bl = true;
		while(bl = true) {
			
		
		Customer.sleepThread2();
		System.out.println("");
		System.out.println("============================");
		System.out.println("=======[1. 로그인  ]==========");
		System.out.println("=======[2. 회원가입 ]==========");
		System.out.println("=======[3. 뒤로가기 ]==========");
		System.out.println("============================");
		System.out.println("");
		
		Customer.sleepThread();
		System.out.println("사용하시려는 기능의 번호를 입력하시기를 바랍니다.");
		
		
		int a = InputUtil.inputInt();
		
		switch(a) {
		case 1 : System.out.println("로그인을 선택하셨습니다."); Customer.login(); break;
		case 2 : System.out.println("회원가입을 선택하셨습니다."); Customer.join(); break;
		case 3 : System.out.println("뒤로가기를 선택하셨습니다."); /*뒤로가기*/ break;
		default : System.out.println("잘못된 번호입니다. 다시 입력해주세요."); bl = false; break;
		}
		
		}
		
	}
	
	}


