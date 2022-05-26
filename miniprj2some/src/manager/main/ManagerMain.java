package manager.main;

import common.util.InputUtil;
import manager.main.ManagerLogin;

public class ManagerMain {

	public static void Managermain() {

		System.out.println("=====관리자=====");
		
		System.out.println("=================");
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.println("0. 종료하기");
		
		boolean isexit = false;
		while(!isexit) {
			int n = InputUtil.inputInt();
			
			switch(n) {
				case 1 : new ManagerLogin().join(); break;
				case 2 : new ManagerLogin().login(); break;
				case 0 : isexit = true; break;
				default : System.out.println("번호를 잘못 입력하셨습니다.");
			}
		}
		
	}

}
