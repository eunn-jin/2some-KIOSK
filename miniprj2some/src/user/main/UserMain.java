package user.main;

import java.util.LinkedList;

import common.util.InputUtil;
import user.menu.CategoryMenu;

public class UserMain {
	
	public static LinkedList<Product> orderlist = new LinkedList<Product>();
	public static LinkedList<AdditionalOption> optionList = new LinkedList<AdditionalOption>();
	
	public static void userMain() {
		
		boolean isExit = false;
		while(!isExit) {
			System.out.println("\n====== 사용자 프로그램 메인 화면 ======");
			System.out.println("1. 메뉴보기");
			System.out.println("2. 장바구니 보기");
			System.out.println("3. 결제하기");
			System.out.println("0. 종료하기");
			
			System.out.print("선택 : ");
			int n = InputUtil.inputInt();
			
			switch(n) {
				case 1 : new CategoryMenu().showCategory(); break;
				case 2 : Order.showCart(); Order.choice(); break;
				case 3 : CheckOut.confirmOrder(); break;//결제하기 메소드의 return이 true 이면 list초기화 break;
				case 0 : System.exit(0); break;
				default : System.out.println("번호를 잘못 입력하셨습니다.");
			}
		}  
	}
}
