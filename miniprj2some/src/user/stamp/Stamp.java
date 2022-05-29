package user.stamp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import common.db.OracleDB;
import common.util.InputUtil;
import user.info.Customer;
import user.info.CustomerHub;

public class Stamp {
	
	public static int usingStamp = 0;
	
	public static void showCustomersStamp() {
		
		if(Customer.loginCustomerNo == 0) {
			System.out.println("비정상적인 접근입니다. 로그인 후 이용해주시길 바랍니다.");
			CustomerHub.plaitLoginJoin();
			
		} else if(Customer.inspectQuit.equals("Y")) {
			 System.out.println("비정상적인 접근입니다. 고객님께선 탈퇴회원입니다.");
			 System.out.println("해당 서비스를 이용하기 위해서 회원가입 후 로그인 후 사용 부탁드립니다.");
			 System.out.println("감사합니다.");
			 Customer.sleepThread();
			 CustomerHub.plaitLoginJoin();
		 }
		
		Customer.sleepThread2();
		
		Connection conn = OracleDB.getOracleConnection();
		
		String sql = "SELECT * FROM CUSTOMER WHERE NO = ?";
		
		
		int logNo = Customer.loginCustomerNo;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, logNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
			String name = rs.getString("NAME");
			String phone = rs.getString("PHONE");
			int stamp = rs.getInt("STAMP");
			String birth = rs.getString("BIRTH");
			Timestamp enroll = rs.getTimestamp("ENROLL");
			
			System.out.println("");
			System.out.println("======"+Customer.customersName+"님의 정보======");
			System.out.print("이름 : ");
			System.out.println(name);
			System.out.print("전화번호 : ");
			System.out.println(phone);
			System.out.print("보유 스탬프 : ");
			System.out.println(stamp);
			System.out.print("생년월일 : ");
			System.out.println(birth);
			System.out.print("가입일자 : ");
			System.out.println(enroll);
			System.out.println("=====================");
			
			}
			
			} catch (SQLException e) {
				System.out.println("SQL에서 예외 발생");
			} finally {
				OracleDB.close(conn);
				OracleDB.close(pstmt);
				OracleDB.close(rs);
			}
		
	}
	
	public static void useStamp() {
		
		
		//로그인이 된 사용자만 접근가능
		if(Customer.loginCustomerNo == 0) {
			System.out.println("비정상적인 접근입니다. 로그인 후 이용해주시길 바랍니다.");
			CustomerHub.plaitLoginJoin();
		}//스탬프가 10개 미만인 사용자는 접근 불가
		 else if (Customer.keepStamp < 10) {
			System.out.println("");
			System.out.println("죄송합니다. 고객님의 스탬프는 " + Customer.keepStamp + "개 입니다.");
			System.out.println("해당 기능은 스탬프를 10개 이상 가지고 있을 때 사용 가능합니다.");
			Customer.sleepThread2();
			System.out.println("마이 멤버쉽 페이지로 이동합니다..");
			return;
		 }else if(Customer.inspectQuit.equals("Y")) {
			 System.out.println("비정상적인 접근입니다. 고객님께선 탈퇴회원입니다.");
			 System.out.println("해당 서비스를 이용하기 위해서 회원가입 후 로그인 후 사용 부탁드립니다.");
			 System.out.println("감사합니다.");
			 Customer.sleepThread();
			 CustomerHub.plaitLoginJoin();
		 }
			
		
		if(usingStamp != 0) {
			System.out.println("");
			System.out.println("고객님. 한 결제에 스탬프할인은 한 번만 적용 가능합니다.");
			System.out.println("이용에 불편을 드려 죄송합니다.");
			System.out.println("");
			System.out.println("마이 멤버쉽 페이지로 이동합니다.");
			Customer.sleepThread2();
			return;
		}
		
		//안내문
		Customer.sleepThread();
		System.out.println("");
		System.out.println("========================");
		System.out.println("");
		Customer.sleepThread();
		System.out.println("현재 고객님께서 가지고 계신 스탬프는 " + Customer.keepStamp + "개 입니다.");
		System.out.println("스탬프는 10개 이상 가지고 있을 때 사용 가능하며,");
		System.out.println("스탬프는 사용할 때마다 10개를 차감한 뒤 총 금액에서 1,000원을 할인해드립니다.");
		System.out.println("스탬프 할인은 결제 1회당 1번 사용 가능하므로, 이 점 유의하시길 바랍니다.");
		System.out.println("스탬프를 사용하시려면 '사용', 뒤로 가기를 선택하시려면 '뒤로가기'를 눌러주세요.");
		Customer.sleepThread();
		System.out.print("번호입력 : ");
		
		//1 입력 시 스탬프 사용, 2 입력 시 뒤로가기(마이 멤버쉽 페이지)
		String select = common.util.InputUtil.sc.nextLine().trim();
		
		if(select.equals("사용")) {
			
			Connection conn = common.db.OracleDB.getOracleConnection();
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
//			UPDATE CUSTOMER SET STAMP =STAMP+1 WHERE NO = 15;
			
			//NO를 통해 조회 후 STAMP 10개 차감
			String sql = "UPDATE CUSTOMER SET STAMP = STAMP-10 WHERE NO = ?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, Customer.loginCustomerNo);
				int result = pstmt.executeUpdate();
				
				if (result == 1 ) {
					
					//성공 메시지 출력 시 
					//스탬프 사용함을 증명
					usingStamp = 1;
					
					System.out.println("");
					Customer.sleepThread();
					System.out.println("스탬프 사용을 선택하셨습니다.");
					System.out.println("스탬프 10개를 사용하였습니다.");
					Customer.sleepThread();
					System.out.println("");
					
					Customer.sleepThread();
					System.out.println("총 결제액의 1,000원이 할인되었습니다.");
					
					Customer.sleepThread();
					
					System.out.println("");
					System.out.println("=============================");
					System.out.println("추가 할인을 위해 쿠폰을 사용하시려면 1번, 바로 결제하기를 원하신다면 2번을 눌러주세요.");
					System.out.println("다른 번호를 입력하면 마이 멤버쉽 페이지로 돌아갑니다.");
					System.out.println("");
					System.out.print("입력 : ");
					int qCoupon = InputUtil.inputInt();
					
					//1번 선택 시 할인쿠폰 사용, 2번 선택 시 바로 결제창으로 이동
					if (qCoupon == 1) {
						
						Customer.sleepThread();
						
						System.out.println("할인쿠폰등록을 선택하셨습니다.");
						System.out.println("할인쿠폰 허브페이지로 이동합니다.");
						
						Customer.sleepThread();
						
						user.coupon.CouponHub.accessCouponHub();
						return;
						
					} else if (qCoupon == 2) {
						
						Customer.sleepThread();
						System.out.println("바로결제를 선택하셨습니다.");
						Customer.sleepThread();
						System.out.println("결제 페이지로 이동합니다.");
						
						user.main.CheckOut.confirmOrder();
						return;
						
					} else {
						Customer.sleepThread();
						System.out.println("마이 멤버쉽 페이지로 이동합니다.");
						return;
					}
					
				} else {
					System.out.println("할인적용이 실패하였습니다.");
					System.out.println("다시 시도하시길 바랍니다.");
					return;
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
			
			//2번 - 뒤로가기
		} else if (select.equals("뒤로가기")) {
			
			System.out.println("뒤로가기를 선택하셨습니다.");
			Customer.sleepThread();
			System.out.println("마이 멤버쉽 페이지로 이동합니다.");
			Customer.sleepThread();
			return;
			
		} else {
			System.out.println("잘못된 입력입니다. 다시 시도해주시길 바랍니다.");
			return;
		}
	}
	
	

}
