package user.stamp;

import common.util.InputUtil;
import user.info.Customer;
import user.info.CustomerHub;
import user.info.Customer;
import user.stamp.Stamp;

public class MembershipHub {
	
	public static void plaitCustomersStamp() {
		
		if(Customer.loginCustomerNo == 0) {
			System.out.println("비정상적인 접근입니다. 로그인 후 이용해주시길 바랍니다.");
			CustomerHub.plaitLoginJoin();
		}
		
		System.out.println("=========================");
		
		System.out.println("");
		System.out.println("마이 멤버쉽 페이지에 접속하였습니다.");
		
		boolean bl = true;
		while(bl = true) {
		
		Customer.sleepThread();
		System.out.println("");
		System.out.println("==========================");
		System.out.println("======[0. 스템프란?  ]======="); //완료
		System.out.println("======[1.내 정보 조회 ]======");
		System.out.println("======[2. 스템프 사용 ]======");
		System.out.println("======[3. 바로 결제  ]======"); //추후
		System.out.println("======[4. 뒤로 가기  ]======"); //완료
		System.out.println("==========================");
		System.out.println("");
		
		System.out.println("사용하시려는 기능의 번호를 입력하시기를 바랍니다.");
		
		int a = InputUtil.inputInt();
		
		switch(a) {
		case 0 : whatIsStamp(); break;
		case 1 : System.out.println("내 정보 조회를 선택하셨습니다."); Stamp.showCustomersStamp(); break;
		case 2 : System.out.println("스템프 사용을 선택하셨습니다."); Stamp.useStamp(); break;
		case 3 : System.out.println("바로결제를 선택하셨습니다."); /*스템프 사용(할인) 메소드*/ break;
		case 4 : System.out.println("뒤로가기를 선택하셨습니다."); CustomerHub.plaitLoginJoin(); break;
		default : System.out.println("잘못된 번호입니다. 다시 입력해주세요."); bl = false; break;
		}
		
		}
		
	}
	
	
		public static void whatIsStamp() {
			
			Customer.sleepThread();
			System.out.println("");
			System.out.println("=========================");
			System.out.println("스템프란 ? ");
			System.out.println("=========================");
			System.out.println("");
			
			System.out.println("스템프는 2SOME 카페의 포인트 제도입니다.");
			Customer.sleepThread3();
			System.out.println("2SOME 멤버쉽 고객이라면 누구나 결제 시 1 스템프가 적립됩니다.");
			Customer.sleepThread3();
			System.out.println("스템프가 10개 이상이 모이면 언제든지 사용 가능하며,");
			Customer.sleepThread3();
			System.out.println("사용은 로그인 후 마이 멤버쉽 페이지에서 사용 가능합니다.");
			Customer.sleepThread3();
			System.out.println("현재 스템프 10개 사용으로 적용되는 할인액은 4,000원입니다.");
			Customer.sleepThread3();
			System.out.println("늘 저희 2SOME을 찾아주시는 고객님들께 감사드립니다.");
			
			Customer.sleepThread3();
			
			System.out.println("마이 멤버쉽 페이지로 이동합니다.");
			
			Customer.sleepThread2();
			
			return;
			
		}

}
