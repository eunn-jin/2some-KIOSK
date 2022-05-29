	package user.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.db.OracleDB;
import common.util.InputUtil;
import user.info.Customer;
import user.stamp.Stamp;

public class CheckOut {
	
	static Order ko = new Order();
	
//	- 메뉴명, 수량, 추가옵션, 가격, 할인가격(쿠폰), 총합 금액 (vat 포함) 보여주기 
//		1. 디비접근
//		2. '주문아이템' (+ 추가옵션) 테이블 , 쿠폰+쿠폰종류 테이블(쿠폰액면가)에서 데이터 보여주기
//	-결제할 금액 입력받고 결제완료 메세지 띄우기
//	-메뉴판보기 페이지로 돌아가기
	
	public static void confirmOrder() {
		
		System.out.println("");
		System.out.println("");
		System.out.println(" ================== 결제 확인 ================== ");
		System.out.println("");
		System.out.println("");
		
		Connection conn = OracleDB.getOracleConnection();
		int tPrice = 0;
		String sql = "SELECT LPAD(ITEM_PRICE,10,' ') AS PRICE FROM ORDER_ITEM";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
				
				System.out.println(String.format("%30s", "총 가격") + " : ");
				System.out.println(" ");
			
			if(rs.next()) {
				tPrice = rs.getInt("PRICE");
				
				
				System.out.println(String.format("%,7d", tPrice) + " 원");
				System.out.println(" ");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		showDiscount();
		
		System.out.println(" ------------------------------------------- ");
		System.out.print(String.format("%30s", "최종 결제금액") + " : ");
		
		Order ko = new Order();
		System.out.println(String.format("%,7d", tPrice - getCouval()) + " 원");
		System.out.println(" ");
		 
		proceed();
		
	}
	
	public static int getCouval() {
		
		int cVal = 0;
		Connection conn = OracleDB.getOracleConnection();
		
		String sql = "SELECT VALUE FROM COU_VAR";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cVal = rs.getInt("VALUE");
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
	
	public static int getStampValue() {
		
		if(Stamp.usingStamp == 1) {
			return 1000; //스탬프 할인가격,,
		}else {
			return 0;
		}
		
	}
	
	public static int showDiscount() {
		
		System.out.print(String.format("%30s", "(쿠폰 할인)") + " : ");
		System.out.println(String.format("%,7d", getCouval()) + " 원");
		
		System.out.print(String.format("%30s", "(스탬프 할인)") + " : ");
		System.out.println(String.format("%,7d", getStampValue()) + " 원");
		
		System.out.print(String.format("%30s", "총 할인 금액") + " : ");
		System.out.println(String.format("%,7d", getCouval() + getStampValue()) + " 원");
		return 0;
		
	}
	
	public static int getTotalPrice() {
		
		Connection conn = OracleDB.getOracleConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalPrice = 0;
		String sql = "SELECT ITEM_PRICE FROM ORDER_ITEM";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				totalPrice = rs.getInt("ITEM_PRICE");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}
		return totalPrice;
		
	}
	
	public static void proceed() {
		
		System.out.printf("%30s", "\"Y\" 를 입력하여 계산하기");
		System.out.printf("\n");
		
		String proceed =  InputUtil.inputStr();
			if("y".equalsIgnoreCase(proceed)) {
				System.out.println("계산하시려면 결제금액을 입력해주세요.");
			
			}else {
				System.out.println("다시");
			}
		
		int tPrice = InputUtil.inputInt();
			
			if(tPrice == (getTotalPrice()-getCouval())) {
				System.out.println("계산이 완료되었습니다.");
				collectStamp();
			}else {
				System.out.println("다시 입력해주세요");
			}
			
			
			inputOrder();
			orderListClear();
	}
	
	public static void inputOrder() {
	
		Connection conn = OracleDB.getOracleConnection();
		
		//4. '결제하기'로 넘어가면, '주문'테이블에 데이터 추가입력
		//입력해야 할 데이터 : 주문번호,구입날짜,고객번호,결제금액,할인금액
		//조회해야 할 테이블 : ORDER_ITEM , CUSTOMER , COUVAR
		//1)테이블 조인해서 조회
		//2)조회한 RS 를 다른 곳에 또 담아서 데이터입력(INSERT)를 해야하나??
		
		int cpVal = getCouval();
		PreparedStatement pstmt = null;
		
		String sqlSelect = "INSERT INTO ORDER_GROUP"
				+ "(GROUP_NO, ORDER_DATE, TOTAL_PRICE, DISCOUNT_PRICE)"
				+ "VALUES"
				+ "(GROUP_SEQ.NEXTVAL, SYSDATE, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sqlSelect);
			pstmt.setInt(1, getTotalPrice());
			pstmt.setInt(2, cpVal);
			int result = pstmt.executeUpdate();
			
	
			if(result == 1) {
				System.out.println("주문 테이블에 데이터가 정상적으로 입력되었습니다.");
			}else {
				System.out.println("데이터 입력 오류ㅠㅠ");
			}
			
		} catch (SQLException e) {
			System.out.println("jdbc 어딘가 오류,,,");
		}finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
		}
	
	}
	
	public static void collectStamp() {
		
		if(Customer.loginCustomerNo == 1) {
			Connection conn = OracleDB.getOracleConnection();
			
			String sql = "UPDATE CUSTOMER SET STAMP = STAMP + 1 WHERE NO = ?";
			PreparedStatement pstmt = null;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, Customer.loginCustomerNo);
				
				int result = pstmt.executeUpdate();
				
				if(result == 1) {
					System.out.println("스탬프 1 개가 성공적으로 적립되었습니다.");
				}else {
					System.out.println("먼가 오류남,,,");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				OracleDB.close(conn);
				OracleDB.close(pstmt);
			}
			
		}
		
	}

	public static void orderListClear() {
		
		Connection conn = OracleDB.getOracleConnection();
		PreparedStatement pstmt = null;
		String sqlClear = "DELETE * FROM ORDER_ITEM";
		try {
			pstmt = conn.prepareStatement(sqlClear);
			int result = pstmt.executeUpdate();
			
			if(result == 1) {
				System.out.println("장바구니가 초기화 되었습니다.");
			}else {
				System.out.println("초기화 에러,,");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
		}
	}
	
	
	
}