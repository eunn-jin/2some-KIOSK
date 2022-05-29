package user.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import common.db.OracleDB;
import common.util.InputUtil;
import user.menu.CategoryMenu;
import user.purchase.Purchase;
import user.stamp.Stamp;

public class Order {
	
//	1. 디비접근
	
//	2. '주문아이템' (+ 추가옵션) 테이블에서 데이터 보여주기
//	3. '결제하기'로 넘어갈건지, '메뉴'로 돌아갈가 추가주문 할건지 선택
//	4. '결제하기'로 넘어가면, '주문'테이블에 데이터 추가입력
	
public static int showCart() {
	
		Connection conn = OracleDB.getOracleConnection();
		
		System.out.println("");
		System.out.println(" ================== 주문 내역 ================== ");
		System.out.print(" |" + String.format("%12s", "메뉴명 |"));
		System.out.print(String.format("%10s", "추가옵션 |"));
		System.out.print(String.format("%5s", "수량 |"));
		System.out.println(String.format("%13s", "가격 |"));
		System.out.println(" --------------------------------------------- ");
		
		int sum = 0;
		for(Product p : UserMain.orderlist) {
			System.out.print(String.format("%12s", p.name));
			System.out.print(String.format("%15d", p.item_num) + " 개");
			System.out.println(String.format("%10d", p.item_price*p.item_num) + " 원");
			
			sum += (p.item_price*p.item_num);
		}
		
		System.out.println(String.format("%35s", "합계 : ") + String.format("%,7d", sum) + " 원");

		return sum;
}

	
	public static void choice() {
		
		System.out.println("");
		System.out.println(" ============================================= ");
		System.out.println("");
		System.out.println("[1. 쿠폰 사용] [2. 로그인] [3. 계산] [4. 메뉴 다시 선택]");
		
		int chooseNo = InputUtil.inputInt();	
		
		switch(chooseNo) {
		case 1 : user.coupon.CouponHub.accessCouponHub();  break;
		case 2 : user.info.CustomerHub.plaitLoginJoin(); break;
		case 3 : CheckOut.confirmOrder(); break;
		case 4 : CategoryMenu cMenu = new CategoryMenu(); cMenu.showCategory(); break;
			default : System.out.println("다시 입력해주세요.");
		}
		
	}
	

	

		 
	}

