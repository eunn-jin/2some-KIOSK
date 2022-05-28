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
		System.out.println("쿠폰정보조회를 위해서 가지고 계신 쿠폰의 쿠폰번호 및 유효기간을");
		System.out.println("슬래쉬(/)포함하여 8글자(yyyy/mm/dd) 적어주시길 바랍니다.");
		Customer.sleepThread();
		
		System.out.println("");
		
		System.out.print("쿠폰번호 : ");
		int couNo = InputUtil.inputInt();
		
		System.out.print("유효기간 : ");
		String limitDay = InputUtil.sc.nextLine().trim();
		
		boolean bl = Customer.validationDate(limitDay);
		if(bl == false) {
			System.out.println("");
			System.out.println("================================");
			Customer.sleepThread();
			System.out.println("유효기간은 슬래쉬(/)를 포함하여 8글자 (yyyy/mm/dd) 형식으로 입력 바랍니다.");
			System.out.println("다시 시도해 주시길 바랍니다.");
			return;
		}
	
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
				String useDate = rs.getString("USEDATE");
				String useCheck = rs.getString("USE_CHECK");
				
				if(useCheck.equals("Y")) {
					
					System.out.println("");
					System.out.println("=================");
					System.out.println("이미 사용된 쿠폰입니다.");
					System.out.println("=================");
					Customer.sleepThread();
					
					System.out.println("");
					System.out.println("=====쿠폰번호의 정보=====");
					System.out.print("쿠폰사용일 : ");
					System.out.println(useDate);
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
					System.out.println("쿠폰 페이지로 이동합니다.");
					Customer.sleepThread2();
					
				} else {
				
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
				System.out.println("쿠폰 페이지로 이동합니다.");
				Customer.sleepThread2(); // 2초 쓰레드 슬립
				
				return;
				}
			} else {
				System.out.println("");
				System.out.println("입력된 쿠폰을 찾을 수 없습니다.");
				System.out.println("잘 입력되었는지 확인 후 다시 시도해주시길 바랍니다.");
				Customer.sleepThread();
				return;
			}
			
			}catch (SQLException e) {
				System.out.println("");
				System.out.println("서버와의 접속이 실패하였습니다.");
				System.out.println("입력된 정보가 맞는 정보인지 확인하신 후 다시 시도하시길 바랍니다.");
				Customer.sleepThread();
				return;
				
			} finally {
				OracleDB.close(conn);
				OracleDB.close(pstmt);
				OracleDB.close(rs);
			}
		
	}
	
	public static void useCoupon() {
		
		Customer.sleepThread();
		System.out.println("");
		System.out.println("========================");
		System.out.println("");
		Customer.sleepThread();
		
		System.out.println("할인쿠폰 적용 페이지로 이동하였습니다.");
		Customer.sleepThread();
		
		if(usingCoupon != 0) {
			System.out.println("");
			Customer.sleepThread();
			System.out.println("고객님. 한 결제에 쿠폰은 한 개만 적용 가능합니다.");
			System.out.println("이용에 불편을 드려 죄송합니다.");
			System.out.println("");
			System.out.println("장바구니 페이지로 이동합니다.");
			Customer.sleepThread2();
			user.main.Order.showCart();
		}
		
		System.out.println("사용하실 쿠폰의 쿠폰번호와 유효기간을 입력하시면 조회 후 사용 가능합니다.");
		Customer.sleepThread();
		System.out.println("유효기간은 슬래쉬(/)를 포함한 8자리 숫자를 전부 입력하셔야 하며");
		Customer.sleepThread();
		System.out.println("뒤로가기를 원하시는 고객님께선 쿠폰번호 입력란에 0번을 입력해주시길 바랍니다.");
		Customer.sleepThread();
		System.out.println("");
		System.out.println("========================");
		System.out.println("");
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
		
		boolean bl = Customer.validationDate(limitDay);
		if(bl == false) {
			System.out.println("");
			System.out.println("================================");
			Customer.sleepThread();
			System.out.println("유효기간은 슬래쉬(/)를 포함하여 8글자 (yyyy/mm/dd) 형식으로 입력 바랍니다.");
			System.out.println("다시 시도해 주시길 바랍니다.");
			return;
		}
		
		
		Connection conn = common.db.OracleDB.getOracleConnection();
		
		
		String sql = "SELECT * FROM COUPON C "
				+ "INNER JOIN COU_VAR V "
				+ "ON C.VARNO = V.VARNO "
				+ "WHERE C.NO = ? AND C.LIMITDATE = ?";
		
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		
		/*첫번째로, 셀렉트해서 모든 정보를 담아주고, 사용여부 확인(USE_CHECK 통해서)*/
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, couNo);
			pstmt.setString(2, limitDay);
			rs = pstmt.executeQuery();
			
			//쿠폰번호, 유효기간, 쿠폰 이름, 액면가, 쿠폰 설명
			if(rs.next()) {
				
				String useDate = rs.getString("USEDATE");
				String useCheck = rs.getString("USE_CHECK");
				//limitday
				String name = rs.getString("NAME");
				int value = rs.getInt("VALUE");
				String introdu = rs.getString("INTRODU");
				
				if(useCheck.equals("Y")) {
					System.out.println("");
					System.out.println("이미 사용된 쿠폰입니다.");
					Customer.sleepThread();
					System.out.print("사용일시 :");
					System.out.println(useDate);
					System.out.println("================================");
					System.out.println("");
					Customer.sleepThread();
					System.out.println("쿠폰 페이지로 돌아갑니다.");
					Customer.sleepThread();
					return;
				}
				
				System.out.println("");
				System.out.println("입력하신 '" + name + "' 쿠폰의 정보를 읽어왔습니다.");
				Customer.sleepThread();
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
				Customer.sleepThread(); 
				
				System.out.println(" 해당 쿠폰을 사용하시려면 1번, 뒤로 가기를 선택하시려면 2번을 눌러주세요. ");
				System.out.print("번호 입력 :");
				int aaa = common.util.InputUtil.inputInt();
				
				if(aaa==1) {
					
					String sql2 = "UPDATE COUPON SET USEDATE = SYSTIMESTAMP"
							+ ", USE_CHECK = 'Y' WHERE NO = ? AND LIMITDATE = ? ";
					
					pstmt2 = conn.prepareStatement(sql2);
					pstmt2.setInt(1, couNo);
					pstmt2.setString(2, limitDay);
					int result = pstmt2.executeUpdate();
					
					if (result == 1 ) {
						
						//성공 메시지 출력 시 
						//스탬프 사용함을 증명
						usingCoupon = 1;
						
						System.out.println("");
						Customer.sleepThread();
						System.out.println("쿠폰 사용을 선택하셨습니다.");
						System.out.println(name + "쿠폰 1개를 사용하였습니다.");
						Customer.sleepThread();
						System.out.println("총 결제액의" + value + "원이 할인되었습니다.");
						Customer.sleepThread();
						
						System.out.println("");
						System.out.println("=============================");
						System.out.println("스탬프 할인/적립을 위하여 로그인을 하시려면 1번,");
						System.out.println("바로 결제하기를 원하신다면 2번을 눌러주세요.");
						System.out.print("번호 입력 :");
						int qlogin = InputUtil.inputInt();
						
						if(qlogin == 1) {
							Customer.sleepThread();
							
							System.out.println("로그인을 선택하셨습니다.");
							
							if(Customer.loginCustomerNo != 0 ) {
								Customer.sleepThread();
								System.out.println("");
								System.out.println(Customer.customersName + "님. 이미 로그인 되어있습니다.");
								Customer.sleepThread();
								System.out.println("마이 멤버쉽 페이지로 이동합니다..");
								Customer.sleepThread();
								user.stamp.MembershipHub.plaitCustomersStamp();
							
							} else {
							Customer.sleepThread();
							System.out.println("");
							System.out.println("로그인 허브 페이지로 이동합니다.");
							Customer.sleepThread();
							
							user.info.CustomerHub.plaitLoginJoin();
							return;
							}
						} else if (qlogin == 2) {
						
							Customer.sleepThread();
							System.out.println("바로결제를 선택하셨습니다.");
							Customer.sleepThread();
							System.out.println("결제 페이지로 이동합니다.");
							
							user.main.CheckOut.confirmOrder();
							return;
						} else {
							Customer.sleepThread();
							System.out.println("잘못된 번호입니다.");
							Customer.sleepThread();
							System.out.println("쿠폰 페이지로 이동합니다.");
							return;
						}
					}	else {
						System.out.println("할인적용이 실패하였습니다.");
						System.out.println("다시 시도하시길 바랍니다.");
						return;
					}
					
					
				} else if (aaa==2) {
					
					System.out.println("뒤로가기를 선택하셨습니다.");
					Customer.sleepThread();
					System.out.println("쿠폰 페이지로 이동합니다.");
					Customer.sleepThread();
					return;
					
				} else {
					System.out.println("잘못된 번호를 입력하셨습니다.");
					System.out.println("다시 한 번 시도하시길 바랍니다.");
					return;
				}
			} else {
				System.out.println("");
				System.out.println("입력된 쿠폰을 찾을 수 없습니다.");
				System.out.println("잘 입력되었는지 확인 후 다시 시도해주시길 바랍니다.");
				Customer.sleepThread();
				return;
			}
			}	catch (SQLException e) {
				System.out.println("");
				System.out.println("서버와의 접속이 실패하였습니다.");
				System.out.println("입력된 정보가 맞는 정보인지 확인하신 후 다시 시도하시길 바랍니다.");
				Customer.sleepThread();
				return;
				
			} finally {
				OracleDB.close(conn);
				OracleDB.close(pstmt);
				OracleDB.close(rs);
			}
	}
}
		
	
				

		
		
		
	
	
	
	
	
	


