package user.main;

import common.util.InputUtil;
import user.menu.CategoryMenu;

public class UserMain {
	public static void userMain() {
		
		System.out.println("\n====== 사용자 프로그램에 접속하셨습니다 ======");
		
		System.out.println("1. 메뉴보기");
		System.out.println("0. 종료하기");
		
		boolean isexit = false;
		while(!isexit) {
			System.out.print("선택 : ");
			int n = InputUtil.inputInt();
			
			switch(n) {
				case 1 : new CategoryMenu().showCategory(); break;
				case 0 : isexit = true; break;
				default : System.out.println("번호를 잘못 입력하셨습니다.");
			}
		}  
		
	}
}
