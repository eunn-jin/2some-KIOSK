package user.coupon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import common.db.OracleDB;

import common.util.InputUtil;
import user.info.Customer;

public class Coupon {
	
	public static int usingCoupon = 0;
	
	public static void showCouponInfo() {
		
		//쿠폰 정보 조회
		
		Customer.sleepThread();
		System.out.println("");
		System.out.println("==========================");
		System.out.println("쿠폰정보조회 페이지로 이동하였습니다.");
		System.out.println("==========================");
		System.out.println("");
		
		
		
		Customer.sleepThread();
		System.out.println("쿠폰정보조회를 위해서 가지고 계신 쿠폰의 쿠폰번호");
		System.out.println("그리고 유효기간을 슬래쉬(/)포함하여 8글자(yyyy/mm/dd) 적어주시길 바랍니다.");
		Customer.sleepThread();
		
		System.out.println("");
		
		System.out.print("쿠폰번호 : ");
		int couNo = InputUtil.inputInt();
		System.out.print("유효기간 : ");
		String limitDay = InputUtil.sc.nextLine().trim();
	
		/*유효성 검사*/
		
		
		Connection conn = OracleDB.getOracleConnection();
		
		String sql = "SELECT * FROM COUPON "
				+ "INNER JOIN COU_VAR"
				+ "ON COUPON.VARNO = COU_VAR.VARNO"
				+ "WHERE COUPON.NO = ? AND COUPON.LIMITDATE = ? ";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Customer.loginCustomerNo);
			pstmt.setString(2, limitDay);
			rs = pstmt.executeQuery();
			
			//쿠폰번호, 유효기간, 쿠폰 이름, 액면가, 쿠폰 설명
			if(rs.next()) {
				String name = rs.getString("NAME");
				String value = rs.getString("VALUE");
				String introdu = rs.getString("INTRODU");
				
				System.out.println("");
				System.out.println("===쿠폰번호"+couNo+"의 정보======");
				System.out.println("");
				System.out.print("쿠폰번호 : ");
				System.out.println(couNo);
				System.out.print("쿠폰이름 : ");
				System.out.println(name);
				System.out.print("쿠폰금액 : ");
				System.out.println(value);
				System.out.print("쿠폰소개 : ");
				System.out.println(introdu);
				System.out.print("유효기간 : ");
				System.out.println(limitDay);
				System.out.println("=====================");
				
				System.out.println("");
				Customer.sleepThread2();
				
				return;
			}
			
			}catch (SQLException e) {
				System.out.println("SQL에서 예외 발생");
			} finally {
				OracleDB.close(conn);
				OracleDB.close(pstmt);
				OracleDB.close(rs);
			}
		
	}
	
	public static void useCoupon() {
		
		System.out.println("할인쿠폰사용");
		
	}
	
	
	
	
	

}
