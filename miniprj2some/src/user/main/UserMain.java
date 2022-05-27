package user.main;

import common.util.InputUtil;
import user.menu.CategoryMenu;

public class UserMain {
	public static void userMain() {
		
		boolean isExit = false;
		while(!isExit) {
			System.out.println("\n====== 사용자 프로그램 메인 화면 ======");
			System.out.println("1. 메뉴보기");
			System.out.println("0. 종료하기");
			
			System.out.print("선택 : ");
			int n = InputUtil.inputInt();
			
			switch(n) {
				case 1 : new CategoryMenu().showCategory(); break;
				case 0 : isExit = true; break;
				default : System.out.println("번호를 잘못 입력하셨습니다.");
			}
		}  
		
	}
}
