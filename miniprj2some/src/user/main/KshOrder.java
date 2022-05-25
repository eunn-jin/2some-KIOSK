package user.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KshOrder {
	
//	1. 디비접근	
//	2. '주문아이템' (+ 추가옵션) 테이블에서 데이터 보여주기
//	3. '결제하기'로 넘어갈건지, '메뉴'로 돌아갈가 추가주문 할건지 선택
//	4. '결제하기'로 넘어가면, '주문'테이블에 데이터 추가입력
	
	public void showCart() {
		
		System.out.println(" ========== 주문 내역 ========== ");
		System.out.println("");
		
		
		//1.
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "C##KH";
		String pwd = "KH";
		
		Connection conn = null;
		//2.
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT MN_NAME, ITEM_NUM, PRICE, ITEM_PRICE "
				+ "FROM MENU N"
				+ "JOIN ORDER_ITEM O"
				+ "ON N.MN_IDX = O.MN_IDX";
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,id,pwd);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
		System.out.println(rs);
		
	}
	
	public void inputOrder() {
		
	}
	
}
