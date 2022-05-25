package manager.main;

import common.util.InputUtil;
import manager.main.ManagerLogin;

public class ManagerMain {

	public static void Managermain() {

		System.out.println("=====관리자=====");
		
		System.out.println("=================");
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		
		int n = InputUtil.inputInt();
		String str = InputUtil.inputStr();
		
		
		switch(n) {
		case 1 : new ManagerLogin().join(); break;
		case 2 : new ManagerLogin().login(); break;
		default : System.out.println("번호를 잘못 입력하셨습니다.");
		}
		
	}

}
