	package user.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.db.OracleDB;

public class KshCheckOut {
	
	
//	- 메뉴명, 수량, 추가옵션, 가격, 할인가격(쿠폰), 총합 금액 (vat 포함) 보여주기 
//		1. 디비접근
//		2. '주문아이템' (+ 추가옵션) 테이블 , 쿠폰+쿠폰종류 테이블(쿠폰액면가)에서 데이터 보여주기
//	-결제할 금액 입력받고 결제완료 메세지 띄우기
//	-메뉴판보기 페이지로 돌아가기
	
	public void confirmOrder() {
		
		System.out.println("");
		System.out.println("");
		System.out.println(" ================== 결제 확인 ================== ");
		System.out.println("");
		System.out.println("");
		
		Connection conn = OracleDB.getOracleConnection();
		
		String sql = "SELECT LPAD(ITEM_PRICE,10,' ') AS PRICE FROM ORDER_ITEM";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int tPrice = rs.getInt("PRICE");
				
				System.out.print("                       총 금액 :   ");
				System.out.println(tPrice + "   원");
				System.out.println(" ");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		showDiscount();
		
	}
	
	public void showDiscount() {
		
		KshOrder ko = new KshOrder();
		
		System.out.print("                        할인금액 :   ");
		System.out.println(ko.getCouval() + "   원");
		
		
		
	}
	
	
}
