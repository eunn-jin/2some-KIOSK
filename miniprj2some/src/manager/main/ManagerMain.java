package manager.main;

import common.util.InputUtil;
import manager.analysis.IncomeStatus;
import manager.analysis.ProductRank;

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
		System.out.println("2. 매출현황 확인하기");
		System.out.println("3. 판매 상품 순위 확인하기");
		System.out.println("0. 로그아웃");
		
		boolean isExit = false;
		while(!isExit) {
			System.out.print("선택 : ");
			int n = InputUtil.inputInt();
			
			switch(n) {
				case 1 : break;
				case 2 : new IncomeStatus().start(); break;
				case 3 : new ProductRank().start(); break;
				case 0 : isExit = true; new ManagerLogin().logout(); break;
				default : System.out.println("번호를 잘못 입력하셨습니다.");
			}
		}
	}
}
