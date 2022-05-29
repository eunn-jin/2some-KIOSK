package user.coupon;

import common.util.InputUtil;
import user.info.Customer;

public class CouponHub {
	
public static void accessCouponHub() {
		
		Customer.sleepThread();
		
		System.out.println("");
		System.out.println("=========================");
		System.out.println("쿠폰할인을 적용할 수 있는 허브 페이지로 이동하였습니다.");
		System.out.println("해당 페이지에서는 할인쿠폰조회 및 등록, 뒤로가기기능을 지원합니다.");
		
		boolean bl = true;
		while(bl = true) {
			
		Customer.sleepThread();
		System.out.println("");
		System.out.println("============================");
		System.out.println("=======[1.할인쿠폰조회]========");
		System.out.println("=======[2.할인쿠폰사용]========");
		System.out.println("=======[3. 뒤로가기 ]=========");
		System.out.println("============================");
		System.out.println("");
		
		Customer.sleepThread();
		System.out.println("사용하시려는 기능의 번호를 입력하시기를 바랍니다.");
		
		System.out.print("번호입력 : ");
		int inputInt = InputUtil.inputInt();
		
		switch(inputInt) {
		
		case 1 : System.out.println("할인쿠폰조회를 선택하셨습니다.");  Coupon.showCouponInfo(); break;
		case 2 : System.out.println("할인쿠폰사용을 선택하셨습니다.");  Coupon.useCoupon(); break;
		case 3 : System.out.println("뒤로가기를 선택하셨습니다."); user.main.Order.showCart();break;
		default : System.out.println("잘못된 번호입니다. 다시 입력해주세요."); bl = false; break;
		}
		}
		
	}

}
