package user.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class KshOrder {
	
//	1. 디비접근	
//	2. '주문아이템' (+ 추가옵션) 테이블에서 데이터 보여주기
//	3. '결제하기'로 넘어갈건지, '메뉴'로 돌아갈가 추가주문 할건지 선택
//	4. '결제하기'로 넘어가면, '주문'테이블에 데이터 추가입력
	
	public void showCart() {
		
		System.out.println(" ================== 주문 내역 ================== ");
		System.out.println("");
		
		
		//1.
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "C##KH";
		String pwd = "KH";
		
		String sql = "SELECT MN_NAME , PRICE , ITEM_NUM , ITEM_PRICE"
				+ " FROM MENU M "
				+ "JOIN ORDER_ITEM O "
				+ "ON M.MN_IDX = O.MN_IDX";
		ResultSet rs = null;
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, id, pwd);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String mname = rs.getString("MN_NAME");
				int qty = rs.getInt("ITEM_NUM");
				int price = rs.getInt("PRICE");
				int tprice = rs.getInt("ITEM_PRICE");
				System.out.println("메뉴명 : " + mname + "수량 : " + qty + "가격 : " + price + "총 가격 :" + tprice);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("");
		System.out.println("===============================================");
		System.out.println("결제하시려면 \"Y\", 메뉴를 다시 고르시려면 \"N\"을 입력해주세요");
		
		Scanner sc = new Scanner(System.in);
		String proceed = sc.nextLine();
		if(proceed.equalsIgnoreCase("Y")) {
			KshCheckOut co = new KshCheckOut();
			co.show();
			inputOrder();
		}else {
			//'메뉴'로 돌아가기
		}
	}
	
	public void inputOrder() {
		
	}

}