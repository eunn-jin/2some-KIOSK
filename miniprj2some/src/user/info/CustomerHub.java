package user.info;

import common.util.InputUtil;

public class CustomerHub {
	
	
	public static void plaitLoginJoin() {
		
		Customer.sleepThread();
		
		System.out.println("");
		System.out.println("=========================");
		System.out.println("포인트 사용 및 적립을 위하여 허브 페이지로 이동하였습니다.");
		System.out.println("해당 페이지에서는 로그인 및 회원가입이 가능합니다.");
		
		boolean bl = true;
		while(bl = true) {
		
		System.out.println("");
		System.out.print("현재 로그인 상태 : ");
		if(Customer.loginCustomerNo != 0) {
			System.out.println(Customer.customersName + "님으로 로그인 되어있습니다.");
		} else {
			System.out.println("");
			System.out.println("로그아웃");
		}
		
		Customer.sleepThread();
		System.out.println("");
		System.out.println("============================");
		System.out.println("=======[1. 로그인  ]==========");
		System.out.println("=======[2. 로그아웃 ]==========");
		System.out.println("=======[3. 회원가입 ]==========");
		System.out.println("=======[4. 로그아웃 ]==========");
		System.out.println("=======[5. 뒤로가기 ]==========");
		System.out.println("============================");
		System.out.println("");
		
		Customer.sleepThread();
		System.out.println("사용하시려는 기능의 번호를 입력하시기를 바랍니다.");
		
		System.out.print("번호입력 : ");
		int a = InputUtil.inputInt();
		
		switch(a) {
		case 1 : System.out.println("로그인을 선택하셨습니다."); Customer.login(); break;
		case 2 : System.out.println("로그아웃을 선택하셨습니다."); Customer.logout();break;
		case 3 : System.out.println("회원가입을 선택하셨습니다."); Customer.join(); break;
		case 4 : System.out.println("로그아웃을 선택하셨습니다."); Customer.quitMember();break;
		case 5 : System.out.println("뒤로가기를 선택하셨습니다."); user.main.Order.showCart(); break;
		default : System.out.println("잘못된 번호입니다. 다시 입력해주세요."); bl = false; break;
		}
		
		}
		
	}
	
	}


