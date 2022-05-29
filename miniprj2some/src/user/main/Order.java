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
	
public static void showCart() {
	
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
			System.out.println(String.format("%10d", p.item_price) + " 원");
			
			sum += p.item_price;
		}
		
		System.out.println(String.format("%35s", "합계 : ") + String.format("%,7d", sum) + " 원");
			
	}
//	public static void showCart() {
//		Purchase purchase = new Purchase();
//		int x = purchase.testShowCart();
//		System.out.println(x);
//	}
	
	
	public void getItemPrice() {
		
//		Connection conn = OracleDB.getOracleConnection();
//		
//		String sqlPrice = "SELECT ITEM_PRICE FROM ORDER_ITEM";
//		
//			PreparedStatement pstmt = null;
//			ResultSet rs = null;
//			try {
//				pstmt = conn.prepareStatement(sqlPrice);
//				rs = pstmt.executeQuery();
//				
//				int idx = 0;
//				
//				while(rs.next()) {
//					int price = rs.getInt("ITEM_PRICE");
//					arr[idx] = price;
//					idx++;
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			
//			
		
		UserMain.orderlist.get(1);

		
		
	}


	public static void getSum() {
		
		Connection conn = OracleDB.getOracleConnection();
		Statement stmt = null;
		ResultSet rs = null;
		
		String sqlSum = "SELECT ITEM_PRICE FROM ORDER_ITEM";
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sqlSum);
			if(rs.next()) {
				int tPrice = rs.getInt("ITEM_PRICE");
				System.out.println("");
				
				System.out.println(" ............................................. ");
				System.out.println(String.format("%35s", "합계 : ") + String.format("%,7d", tPrice) + " 원");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			OracleDB.close(conn);
			OracleDB.close(stmt);
			OracleDB.close(rs);
		}
		
		choice();
		
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
	
//	public int getCouval() {
//		
//		int cVal = 0;
//		Connection conn = OracleDB.getOracleConnection();
//		
//		String sql = "SELECT VALUE FROM COU_VAR";
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = conn.prepareStatement(sql);
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()) {
//				cVal = rs.getInt("VALUE");
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			OracleDB.close(conn);
//			OracleDB.close(pstmt);
//			OracleDB.close(rs);
//		}
//		return cVal;
//	}
	

	

		 
	}

