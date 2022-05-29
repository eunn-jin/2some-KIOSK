package manager.main;

import common.util.InputUtil;
import manager.analysis.IncomeStatus;
import manager.analysis.ProductRank;
import manager.menu.MenuManager;
import manager.menu.OptionManager;

public class ManagerMain {

	public static boolean successLogin = false;
	public static void managerMain() {
		
		boolean isExit = false;
		while(!isExit) {
			System.out.println("\n====== 관리자 프로그램 메인 화면 ======");
			System.out.println("1. 회원가입");
			System.out.println("2. 로그인");
			System.out.println("0. 종료하기");
			System.out.print("선택 : ");
			int n = InputUtil.inputInt();
			
			switch(n) {
				case 1 : new ManagerLogin().join(); break;
				case 2 : ManagerLogin ml = new ManagerLogin();
							successLogin = ml.login();
							if(successLogin) { managerHome();}
							break;
				case 0 : isExit = true; break;
				default : System.out.println("번호를 잘못 입력하셨습니다.");
			}
		}  
	}
	
	public static void managerHome() {
		System.out.println("===================");
		System.out.println("===== 관리자 홈 =====");
		System.out.println("===================");
		System.out.println("1. 메뉴 관리하기");
		System.out.println("2. 메뉴옵션 관리하기");
		System.out.println("3. 매출현황 확인하기");
		System.out.println("4. 판매 상품 순위 확인하기");
		System.out.println("0. 로그아웃");
		
		boolean isExit = false;
		while(!isExit) {
			System.out.print("선택 : ");
			int n = InputUtil.inputInt();
			
			switch(n) {
				case 1 : new MenuManager().showCategory(); break;
				case 2 : new OptionManager().showCategory(); break;
				case 3 : new IncomeStatus().start(); break;
				case 4 : new ProductRank().start(); break;
				case 0 : isExit = new ManagerLogin().logout(); break;
				default : System.out.println("번호를 잘못 입력하셨습니다.");
			}
		}
	}
}
