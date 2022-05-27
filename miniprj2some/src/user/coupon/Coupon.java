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
		System.out.println("쿠폰정보조회를 위해서 가지고 계신 쿠폰의 쿠폰번호,");
		System.out.println("그리고 유효기간을 슬래쉬(/)포함하여 8글자(yyyy/mm/dd) 적어주시길 바랍니다.");
		Customer.sleepThread();
		
		System.out.println("");
		
		System.out.print("쿠폰번호 : ");
		int couNo = InputUtil.inputInt();
		System.out.print("유효기간 : ");
		String limitDay = InputUtil.sc.nextLine().trim();
	
		/*유효성 검사*/
		
		
		Connection conn = OracleDB.getOracleConnection();
		
		String sql = "SELECT * FROM COUPON C "
				+ "INNER JOIN COU_VAR V "
				+ "ON C.VARNO = V.VARNO "
				+ "WHERE C.NO = ? AND C.LIMITDATE = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
			
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, couNo);
			pstmt.setString(2, limitDay);
			rs = pstmt.executeQuery();
			
				
			//쿠폰번호, 유효기간, 쿠폰 이름, 액면가, 쿠폰 설명
			if(rs.next()) {
				
				String name = rs.getString("NAME");
				int value = rs.getInt("VALUE");
				String introdu = rs.getString("INTRODU");
				
				System.out.println("");
				System.out.println("=====쿠폰번호의 정보=====");
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
				Customer.sleepThread2(); // 2초 쓰레드 슬립
				
			}
			
			}catch (SQLException e) {
				System.out.println("왜...?");
			} finally {
				OracleDB.close(conn);
				OracleDB.close(pstmt);
				OracleDB.close(rs);
			}
		
	}
	
	public static void useCoupon() {
		
		Customer.sleepThread();
		System.out.println("");
		System.out.println("");
		System.out.println("========================");
		System.out.println("");
		Customer.sleepThread();
		
		System.out.println("할인쿠폰 적용 페이지로 이동하였습니다.");
		Customer.sleepThread();
		System.out.println("사용하실 쿠폰의 쿠폰번호와 유효기간을 입력하시면 사용 가능합니다.");
		Customer.sleepThread();
		System.out.println("유효기간은 슬래쉬(/)를 포함한 8자리 숫자를 전부 입력하셔야 하며");
		Customer.sleepThread();
		System.out.println("뒤로가기를 원하시는 고객님께선 쿠폰번호 입력란에 0번을 입력해주시길 바랍니다.");
		Customer.sleepThread();
		System.out.println("");
		System.out.println("========================");
		
		System.out.print("쿠폰번호 : ");
		int couNo = InputUtil.inputInt();
		
		if(couNo == 0) {
			System.out.println("뒤로가기를 선택하셨습니다.");
			Customer.sleepThread();
			System.out.println("쿠폰 메인페이지로 이동합니다.");
			Customer.sleepThread();
			System.out.println("");
			return;
		}
		
		System.out.print("유효기간 : ");
		String limitDay = InputUtil.sc.nextLine().trim();
		
		//유효성 검사
		
		Connection conn = common.db.OracleDB.getOracleConnection();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "쿼리 ??????";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Customer.loginCustomerNo);
			int result = pstmt.executeUpdate();
			
			if (result == 1 ) {
				
				usingCoupon = 1;
				
				System.out.println("");
				Customer.sleepThread();
				System.out.println("name" + "쿠폰을 사용하셨습니다.");
				Customer.sleepThread();
				System.out.println("");
				
				Customer.sleepThread();
				System.out.println("총 결제액의" + "value" + "원이 할인되었습니다.");
				
				Customer.sleepThread();
				
				System.out.println("");
				System.out.println("로그인을 하여 스탬프 적립 혹은 할인을 받으시려면 1번, 바로 결제하기를 원하신다면 2번을 눌러주세요.");
				System.out.println("");
				int qLogin = InputUtil.inputInt();
				
				if (qLogin == 1) {
					
					Customer.sleepThread();
					
					System.out.println("로그인을 선택하셨습니다.");
					System.out.println("로그인 및 회원가입 페이지로 이동합니다.");
					
					Customer.sleepThread();
					
					user.info.CustomerHub.plaitLoginJoin();
					return;
					
				} else if (qLogin == 2) {
					
					System.out.println("바로결제를 선택하셨습니다.");
					Customer.sleepThread();
					System.out.println("결제 페이지로 이동합니다.");
					
					user.main.CheckOut.confirmOrder();
					return;
					
				} else {
					System.out.println("할인적용이 실패하였습니다.");
					System.out.println("다시 시도하시길 바랍니다.");
					return;
				}
						
				}
			}
			 catch (SQLException e) {
				System.out.println("죄송합니다. 서버와 연결이 끊겼습니다.");
				System.out.println("입력하신 정보가 옳바른 것인지 확인 부탁드립니다.");
				return;
			} finally {
				OracleDB.close(conn);
				OracleDB.close(pstmt);
				OracleDB.close(rs);
			}
			
		
	}
	
				
				
				
}
		
		
		
	
	
	
	
	
	


