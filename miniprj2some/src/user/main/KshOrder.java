package user.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import common.db.OracleDB;
import common.util.InputUtil;
import manager.menu.MenuManager;

public class KshOrder {
	
//	1. 디비접근
	
	
	
//	2. '주문아이템' (+ 추가옵션) 테이블에서 데이터 보여주기
//	3. '결제하기'로 넘어갈건지, '메뉴'로 돌아갈가 추가주문 할건지 선택
//	4. '결제하기'로 넘어가면, '주문'테이블에 데이터 추가입력
	
public void showCart() {
	
		Connection conn = OracleDB.getOracleConnection();
		
		System.out.println("");
		System.out.println(" ================== 주문 내역 ================== ");
		System.out.println("");
		
			String sql = "SELECT MN_NAME , PRICE , ITEM_NUM , ITEM_PRICE"
					+ " FROM MENU M "
					+ "JOIN ORDER_ITEM O "
					+ "ON M.MN_IDX = O.MN_IDX";
			PreparedStatement pstmt = null;
			try {
				pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					String mname = rs.getString("MN_NAME");
					int qty = rs.getInt("ITEM_NUM");
					int price = rs.getInt("PRICE");
					int tprice = rs.getInt("ITEM_PRICE");
					
					System.out.print("  메뉴명 : " + mname);
					System.out.print("   ");
					System.out.print("수량 : " + qty + " 개");
					System.out.print("   ");
					System.out.println("가격 : " + price + " 원");
					
					
				}
			} catch (SQLException e) {
				System.out.println("에러 작작좀;;");
			}finally {
				OracleDB.close(conn);
				OracleDB.close(pstmt);
			}
			
			getSum();
			
	}

	public void getSum() {
		
		Connection conn = OracleDB.getOracleConnection();
		
		String sqlSum = "SELECT ITEM_PRICE FROM ORDER_ITEM";
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlSum);
			if(rs.next()) {
				int tPrice = rs.getInt("ITEM_PRICE");
				System.out.println("");
				System.out.println(" --------------------------------------------- ");
				System.out.println("                               합계 : " + tPrice + " 원");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		choice();
		
	}
	
	
	public void choice() {
		
		Connection conn = OracleDB.getOracleConnection();
		
		System.out.println("");
		System.out.println(" ============================================= ");
		System.out.println("");
		System.out.println("결제하시려면 \"Y\", 메뉴를 다시 고르시려면 \"N\"을 입력해주세요");
		
		String checkYn = InputUtil.inputStr();
		if(checkYn.equalsIgnoreCase("Y")) {
			KshCheckOut co = new KshCheckOut();
			co.confirmOrder();
//			inputOrder();
		}else {
			//'메뉴'로 돌아가기
//				MenuManager manager = new MenuManager();
//				manager.showCategory();
		}
	}
	
	public int getCouval() {
		
		Connection conn = OracleDB.getOracleConnection();
		int cVal = 0;
		String sql = "SELECT LPAD(VAL,10,'0') AS V FROM COUVAR";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cVal = rs.getInt("V");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cVal;
	}
	
	
	public void showDiscount() {
		
	}
	
	public void inputOrder() {
		
		Connection conn = OracleDB.getOracleConnection();
		
		//4. '결제하기'로 넘어가면, '주문'테이블에 데이터 추가입력
		//입력해야 할 데이터 : 주문번호,구입날짜,고객번호,결제금액,할인금액
		//조회해야 할 테이블 : ORDER_ITEM , CUSTOMER , COUVAR
		//1)테이블 조인해서 조회
		//2)조회한 RS 를 다른 곳에 또 담아서 데이터입력(INSERT)를 해야하나??
		
		int cVal = getCouval();
		
		String sqlSelect = "INSERT INTO ORDER_GROUP"
				+ "(GROUP_NO,ORDER_DATE,U_NUM,TOTAL_PRICE,DISCOUNT_PRICE)"
				+ "VALUES"
				+ "(GROUP_SEQ.NEXTVAL,SYSDATE,GROUP_SEQ.NEXTVAL,ORDER_ITEM.ITEM_PRICE,?)";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sqlSelect);
			pstmt.setInt(1, cVal);
			int result = pstmt.executeUpdate();
			

			if(result == 1) {
				System.out.println("데이터 입력 성공^^");
			}else {
				System.out.println("데이터 입력 오류ㅠㅠ");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
	}

}