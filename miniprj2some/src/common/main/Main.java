package common.main;

import common.util.InputUtil;
import manager.main.ManagerMain;
import user.main.UserMain;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("=================");
		System.out.println("1. 관리자");
		System.out.println("2. 유저");
		
		int n = InputUtil.inputInt();
			
		switch(n) {
			case 1 : ManagerMain.Managermain(); break;
			case 2 : UserMain.Usermain(); break;
			default : System.out.println("번호를 잘못 입력하셨습니다.");
		}
	}

}
