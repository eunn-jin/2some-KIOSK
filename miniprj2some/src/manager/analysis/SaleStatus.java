package manager.analysis;

import common.util.InputUtil;

public class SaleStatus {	//기간 별 매출 현황 조회
	
	public void start() {
		getList();
		
		boolean loopflag = true;
		while(loopflag) {
			int n = InputUtil.inputInt();
			
			switch(n) {
				case 1: break;
				case 2: break;
				case 3: choiceperiod(); getList(); break;
				case 0: loopflag = false; break;
				default: System.out.println("잘못입력하셨습니다. 정확한 번호를 입력하세요.");
			}
		}
	}
	
	public void getMonthIncome() {
		
	}
	
	public void choiceperiod() {
		
	}
	
	public void getList() {
		System.out.println("===== 확인하고 싶은 기간을 선택하시오 =====");
		System.out.println("1. 월간");
		System.out.println("2. 일간");
		System.out.println("3. 기간 지정");
		System.out.println("0. 이전 화면으로 돌아가기");
	}
	
}
