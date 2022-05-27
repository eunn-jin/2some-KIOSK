package common.main;

import common.util.InputUtil;
import manager.main.ManagerMain;
import user.main.UserMain;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("======== 해당하는 계정을 선택하세요 =========");
		System.out.println("1. 관리자");
		System.out.println("2. 유저");
		System.out.println("0. 종료하기");
		
		boolean isexit = false;
		while(!isexit) {
			System.out.print("선택 : ");
			int n = InputUtil.inputInt();
			
			switch(n) {
				case 1 : ManagerMain.managerMain(); break;
				case 2 : UserMain.userMain(); break;
				case 0 : break;
				default : System.out.println("번호를 잘못 입력하셨습니다."); continue;
			}
			isexit = true;
		}  
	}

}
