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
import user.menu.CategoryMenu;

public class KshOrder {
	
//	1. 디비접근
	
//	2. '주문아이템' (+ 추가옵션) 테이블에서 데이터 보여주기
//	3. '결제하기'로 넘어갈건지, '메뉴'로 돌아갈가 추가주문 할건지 선택
//	4. '결제하기'로 넘어가면, '주문'테이블에 데이터 추가입력
	
public void showCart() {
	
		Connection conn = OracleDB.getOracleConnection();
		
		System.out.println("");
		System.out.println(" ================== 주문 내역 ================== ");
		
		
			String sql = "SELECT LPAD(MN_NAME,10,' ') MNAME , PRICE , ITEM_NUM , ITEM_PRICE"
					+ " FROM MENU M "
					+ "JOIN ORDER_ITEM O "
					+ "ON M.MN_IDX = O.MN_IDX";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				System.out.print(" |" + String.format("%12s", "메뉴명 |"));
				System.out.print(String.format("%10s", "추가옵션 |"));
				System.out.print(String.format("%5s", "수량 |"));
				System.out.println(String.format("%13s", "가격 |"));
				System.out.println(" --------------------------------------------- ");
				
				while(rs.next()) {
					String mname = rs.getString("MNAME");
					int qty = rs.getInt("ITEM_NUM");
					int price = rs.getInt("PRICE");
					
					
					System.out.print(String.format("%12s", mname));
					System.out.print(String.format("%15d", qty) + " 개");
					System.out.println(String.format("%10d", price) + " 원");
					
					
				}
			} catch (SQLException e) {
				System.out.println("에러 작작좀;;");
			}finally {
				OracleDB.close(conn);
				OracleDB.close(pstmt);
				OracleDB.close(rs);
			}
			
			getSum();
			
	}

	public void getSum() {
		
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
	
	
	public void choice() {
		
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
//			'메뉴'로 돌아가기
			CategoryMenu cMenu = new CategoryMenu();
				cMenu.showCategory();
		}
	}
	
	public int getCouval() {
		
		int cVal = 0;
		Connection conn = OracleDB.getOracleConnection();
		
		String sql = "SELECT LPAD(VAL,10,'0') AS V FROM COUVAR";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cVal = rs.getInt("V");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}
		return cVal;
	}
	

	

		 
	}

