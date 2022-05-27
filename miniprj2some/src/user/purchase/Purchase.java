package user.purchase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.db.OracleDB;
import user.main.Product;
import user.main.UserMain;

public class Purchase {
	
	public void testMain() {
		System.out.println("장바구니에 집어넣기");
		testMethod();
		
		System.out.println("장바구니 확인하기");
		int sum = testShowCart();
		
		System.out.println("db에 집어넣기");
		testInputOrder(sum);
		int group = testGetGroup();
		
		if(group != 0) {
			for(Product p : UserMain.orderlist) {
				testInputOrderItem(group, p);
			}
		}
	}

	public void testMethod() {
		Product p1 = new Product(1, 2, 8000, "아메리카노");
		UserMain.orderlist.add(p1);
		
		Product p2 = new Product(2, 2, 8000, "카페라떼");
		UserMain.orderlist.add(p2);
	}
	
	public int testShowCart() {
		int sum = 0;
		for(Product p : UserMain.orderlist) {
			System.out.println(p.name +"을 "+ p.item_num + "개, 가격 : " + p.item_price);
			sum += p.item_price;
		}
		
		System.out.println("최종 금액 : "+ sum);
		
		return sum;
	}
	
	public void testInputOrder(int sum) {
		Connection conn = OracleDB.getOracleConnection();
		PreparedStatement pstmt = null;
		
		String sqlForGroup = "INSERT INTO ORDER_GROUP"
				+ "(GROUP_NO, ORDER_DATE, TOTAL_PRICE)"	//할인이랑 포인트는 우선 생략했어요..
				+ "VALUES"
				+ "(GROUP_SEQ.NEXTVAL, SYSDATE, ?)";
		
		try {
			pstmt = conn.prepareStatement(sqlForGroup);
			pstmt.setInt(1, sum);
			int result = pstmt.executeUpdate();
			
			if(result == 1) {
				System.out.println("주문 테이블에 데이터가 정상적으로 입력되었습니다.");
			}else {
				System.out.println("데이터 입력 오류ㅠㅠ");
			}
			
		} catch (SQLException e) {
			System.out.println("jdbc 어딘가 오류,,,");
			e.printStackTrace();
		}finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
		}
	}
	
	public int testGetGroup() {
		Connection conn = OracleDB.getOracleConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int group= 0;
		String sqlGetGroup = "SELECT MAX(GROUP_NO) FROM ORDER_GROUP";
		
		try {
			pstmt = conn.prepareStatement(sqlGetGroup);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				 group = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			System.out.println("jdbc 어딘가 오류,,,");
			e.printStackTrace();
		}finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
		}
		
		return group;
	}
	
	private void testInputOrderItem(int group, Product p) {
		Connection conn = OracleDB.getOracleConnection();
		PreparedStatement pstmt = null;
		
		String sqlForGroup = "INSERT INTO ORDER_ITEM"
				+ "(NO, MN_IDX, ITEM_NUM, ITEM_PRICE, GROUP_NO)"	//할인이랑 포인트는 우선 생략했어요..
				+ "VALUES"
				+ "(ITEM_SEQ.NEXTVAL, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sqlForGroup);
			pstmt.setInt(1, p.mn_idx);
			pstmt.setInt(2, p.item_num);
			pstmt.setInt(3, p.item_price);
			pstmt.setInt(4, group);
			int result = pstmt.executeUpdate();
			
			if(result == 1) {
				System.out.println("주문 테이블에 데이터가 정상적으로 입력되었습니다.");
			}else {
				System.out.println("데이터 입력 오류ㅠㅠ");
			}
			
		} catch (SQLException e) {
			System.out.println("jdbc 어딘가 오류,,,");
			e.printStackTrace();
		}finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
		}
	}
}
