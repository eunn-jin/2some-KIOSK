package user.stamp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import common.db.OracleDB;
import user.info.Customer;
import user.info.CustomerHub;

public class Stamp {
	
	public static int usingStamp = 0;
	
	public static void showCustomersStamp() {
		
		if(Customer.loginCustomerNo == 0) {
			System.out.println("비정상적인 접근입니다. 로그인 후 이용해주시길 바랍니다.");
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
			System.out.println("");
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
		
		if(Customer.loginCustomerNo == 0) {
			System.out.println("비정상적인 접근입니다. 로그인 후 이용해주시길 바랍니다.");
			CustomerHub.plaitLoginJoin();
		} else if (Customer.keepStamp < 10) {
			System.out.println("");
			Customer.sleepThread();
			System.out.println("죄송합니다. 고객님의 스탬프는 " + Customer.keepStamp + "개 입니다.");
			System.out.println("해당 기능은 스탬프를 10개 이상 가지고 있을 때 사용 가능합니다.");
			Customer.sleepThread2();
			System.out.println("마이 멤버쉽 페이지로 이동합니다..");
			return;
		}
		
		Customer.sleepThread();
		System.out.println("");
		System.out.println("");
		System.out.println("========================");
		System.out.println("");
		Customer.sleepThread();
		System.out.println("현재 고객님께서 가지고 계신 스탬프는 " + Customer.keepStamp + "개 입니다.");
		Customer.sleepThread();
		System.out.println("스탬프는 10개 이상 가지고 있을 때 사용 가능하며,");
		Customer.sleepThread();
		System.out.println("스탬프는 사용할 때마다 10개를 차감한 뒤 총 금액에서 4,000원을 할인해드립니다.");
		System.out.println("");
		Customer.sleepThread();
		System.out.println("스탬프 할인은 결제 1회당 1번 사용 가능하므로, 이 점 유의하시길 바랍니다.");
		Customer.sleepThread();
		
		System.out.println("");
		System.out.println("스탬프를 사용하시려면 1번, 뒤로 가기를 선택하시려면 2번을 눌러주세요.");
		System.out.println("");
		
		int selectUseStamp = common.util.InputUtil.inputInt();
		
		if(selectUseStamp == 1) {
			usingStamp = 1;
			/*
			 * 스탬프 사용 및 db 업데이트.
			 */
			System.out.println("");
			Customer.sleepThread();
			System.out.println("스탬프 사용을 선택하셨습니다.");
			System.out.println("스탬프 10개를 사용하였습니다.");
			Customer.sleepThread();
			System.out.println("총 결제액의 4,000원이 할인되었습니다.");
			
			Customer.sleepThread();
			
			System.out.println("추가 할인을 위해 쿠폰을 사용하시려면 1번, 바로 결제하기를 원하신다면 2번을 눌러주세요.");
			
			
			
		} else if (selectUseStamp == 2) {
			
			System.out.println("뒤로가기를 선택하셨습니다.");
			Customer.sleepThread();
			System.out.println("마이 멤버쉽 페이지로 이동합니다.");
			Customer.sleepThread();
			return;
			
		} else {
			
			System.out.println("잘못된 번호입니다. 다시 시도해주시길 바랍니다.");
			return;
		}
	}
	
	

}
