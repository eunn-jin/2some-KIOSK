package user.info;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import user.stamp.MembershipHub;
import user.stamp.Stamp;
import common.db.OracleDB;
import common.util.InputUtil;


public class Customer {
	

	 public static int loginCustomerNo = 0;
	 public static int keepStamp = 0;
	 public static String customersName = "";
	 public static String inspectQuit = "N";
	
	public static boolean login() {
		
		int cnt = 0;
		boolean bl = true;
		
		
		if (loginCustomerNo != 0) {
			sleepThread();
			
			System.out.println("");
			System.out.println("이미 접속한 계정입니다.");
			System.out.println(customersName + "님의 마이 멤버쉽 페이지로 이동합니다.");
			System.out.println("");
			sleepThread2();
			
			MembershipHub.plaitCustomersStamp();
		}
		
		sleepThread();
		
		System.out.println("");
		
		System.out.println("======================");
		System.out.println("=========로그인=========");
		
		sleepThread();
		
		System.out.println("");
		System.out.println("로그인 페이지입니다.");
		
		sleepThread();
		System.out.println("고객님의 성함과 대쉬(-)를 포함한 전화번호 11자리,");
		sleepThread();
		System.out.println("그리고 슬래쉬(/)를 포함한 생년월일 8자리(yyyy/mm/dd)를 입력하여 주시길 바랍니다.");
		sleepThread();
		System.out.println("뒤로가기를 원하시는 고객님께선 이름에 뒤로가기를 입력하여 주시길 바랍니다.");
		
		sleepThread();
		
		System.out.println("");
		
		
		while(bl) {
		
		System.out.print("이름 : ");
		String name = InputUtil.sc.nextLine().trim();
		
		if(name.equals("뒤로가기")) {
			System.out.println("뒤로가기를 입력하셨습니다.");
			
			CustomerHub.plaitLoginJoin();
		}
		
		System.out.print("전화번호 : ");
		String phone = InputUtil.sc.nextLine().trim();
		
		System.out.print("생년월일 : ");
		String birthDay = InputUtil.sc.nextLine().trim();
		
		//DB 연결 얻기 
		
		Connection conn = common.db.OracleDB.getOracleConnection();
		
		
		//해당 이름에 맞는 전화번호 디비에서 조회하기
		String sql = "SELECT * FROM CUSTOMER WHERE PHONE = ? AND QUIT = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String no = "N";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, phone);
			pstmt.setString(2, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				String dbPhone = rs.getString("PHONE");
				int dbNo = rs.getInt("NO");
				int dbStamp = rs.getInt("STAMP");
				String dbName = rs.getString("NAME");
				String dbQuit = rs.getString("QUIT");
				
				if(dbPhone.equals(phone)) {
					
					loginCustomerNo = dbNo;
					keepStamp = dbStamp;
					customersName = dbName;
					inspectQuit = dbQuit;
								
					sleepThread();
					
					System.out.println("");
					System.out.println("=======================");
					System.out.println("");
					System.out.println("안녕하세요." + customersName + "님.");
					System.out.println("로그인 성공하였습니다.");
					System.out.println("마이 멤버쉽 페이지로 이동합니다.");
					System.out.println("");
					sleepThread();
					
					MembershipHub.plaitCustomersStamp();
				
				}		
			} 
		} catch (SQLException e) {
			System.out.println("SQL에서 예외 발생");
			CustomerHub.plaitLoginJoin();
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}
		
		System.out.println("");
		System.out.println("로그인에 실패하였습니다.");
		System.out.println("안내된대로 옳바른 정보가 기입되었는지 확인 부탁드립니다.");
		System.out.println("신규 고객님, 혹은 멤버쉽 탈퇴 회원인 고객님은 회원가입 후 진행해주시길 바랍니다. ");
		sleepThread();
		System.out.println("로그인은 3회까지 시도 할 수 있습니다.");
		sleepThread();
		cnt++;
		if(cnt == 3) {bl = false;}
		System.out.println("로그인을 다시 시도합니다.");
		System.out.println("");
		}
		
		System.out.println("로그인에 3회 실패하였습니다.");
		System.out.println("회원가입 페이지로 이동합니다.");
		join();
		return false;
		
		
		
		
	}
	
	
	public static boolean logout() {
		
		if (loginCustomerNo == 0) {
			
			System.out.println("");
			System.out.println("고객님은 현재 로그아웃 상태입니다.");
			System.out.println("");
			Customer.sleepThread3();
			
			return false;
		}
		
		
		System.out.println("");
		System.out.println("===================");
		System.out.println("");
		System.out.println("로그아웃 중 ...");
		
		if (Stamp.usingStamp != 0) {
			
			Customer.sleepThread();
			System.out.println(customersName + " 님은 현재 스탬프 할인을 적용하셨습니다.");
			Customer.sleepThread();
			System.out.println("로그아웃을 하신다면 스탬프는 반환되고 할인도 취소됩니다.");
			Customer.sleepThread();
			System.out.println("그래도 로그아웃을 원하시면 '로그아웃'을 입력해주시길 바랍니다.");
			System.out.print("입력 : ");
			String inputStr = InputUtil.sc.nextLine();
			
			if(inputStr.equals("로그아웃")) {
				System.out.println("");
				Customer.sleepThread();
				System.out.println("로그아웃을 진행합니다.");

				/*
				 * 로그아웃
				 */ 
				  Connection conn = common.db.OracleDB.getOracleConnection();
		
				  PreparedStatement pstmt = null;
				  ResultSet rs = null;
		
			String sql = "UPDATE CUSTOMER SET STAMP = STAMP+10 WHERE NO = ? ";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, Customer.loginCustomerNo);
				int result = pstmt.executeUpdate();
				
				if (result == 1 ) {
					
					Customer.sleepThread();
					System.out.println("");
					loginCustomerNo = 0;
					Stamp.usingStamp = 0;
					System.out.println("로그아웃이 완료되었습니다.");
					Customer.sleepThread();
					System.out.println("");
					System.out.println("로그인 허브 페이지로 이동합니다...");
					Customer.sleepThread();
					return true;
				}
				
				else {
					Customer.sleepThread();
					System.out.println("로그아웃에 실패하였습니다.");
					Customer.sleepThread();
					System.out.println("다시 시도하시길 바랍니다.");
					return false;
				}
				
			}catch (SQLException e) {
				System.out.println("죄송합니다. 서버와 연결이 끊겼습니다.");
				System.out.println("입력하신 정보가 옳바른 것인지 확인 부탁드립니다.");
			} finally {
				OracleDB.close(conn);
				OracleDB.close(pstmt);
				OracleDB.close(rs);
			}

			}
		} else {
			
			Customer.sleepThread();
			System.out.println("");
			loginCustomerNo = 0;
			Stamp.usingStamp = 0;
			System.out.println("로그아웃이 완료되었습니다.");
			Customer.sleepThread();
			System.out.println("");
			System.out.println("로그인 허브 페이지로 이동합니다...");
			Customer.sleepThread();
			return true;
			
		}
		
		
		
		return false;
		
	}
	
	public static boolean quitMember() {
		
		
		sleepThread();
		System.out.println("=========================");
		System.out.println("");
		System.out.println("멤버쉽 회원탈퇴 페이지로 이동하였습니다.");
		System.out.println("");
		System.out.println("=========================");
		
		if(loginCustomerNo != 0) {
		System.out.println("");
		sleepThread();
		System.out.println(customersName + "님의 회원탈퇴를 위해 성함과 대쉬(-)를 포함한 전화번호 11글자" );
		System.out.println("그리고 슬래쉬(/)를 포함한 생년월일 8글자를 입력하여 주시길 바랍니다.");
		sleepThread();
		} else {
			System.out.println("");
			sleepThread();
			System.out.println("고객님의 회원탈퇴를 위해 성함과 대쉬(-)를 포함한 전화번호 11글자" );
			System.out.println("그리고 슬래쉬(/)를 포함한 생년월일 8글자를 입력하여 주시길 바랍니다.");
			sleepThread();
		}
		
		sleepThread();
		System.out.println("뒤로가기를 원하시는 고객님께선 이름에 뒤로가기를 입력하여 주시길 바랍니다.");
		sleepThread();
		System.out.println("");
		
		System.out.print("이름 : ");
		String name = InputUtil.sc.nextLine().trim();
		
		if(name.equals("뒤로가기")) {
			System.out.println("뒤로가기를 입력하셨습니다.");
			return true;
		}
		
		System.out.print("전화번호 : ");
		String phone = InputUtil.sc.nextLine().trim();
		
		System.out.print("생년월일 : ");
		String birthDay = InputUtil.sc.nextLine().trim();
		
		
		//DB 연결 얻기 
		
		Connection conn = common.db.OracleDB.getOracleConnection();
		
		
		//해당 이름에 맞는 전화번호 디비에서 조회하기
		String sql = "SELECT * FROM CUSTOMER WHERE PHONE = ? AND BIRTH = ? ";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, phone);
			pstmt.setString(2, birthDay);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				int dbNo = rs.getInt("NO");
				String dbPhone = rs.getString("PHONE");
				int dbStamp = rs.getInt("STAMP");
				String dbName = rs.getString("NAME");
				String dbbirth = rs.getString("BIRTH");
				String dbQuit = rs.getString("QUIT"); 
				
				if(dbQuit.equals("Y")) {
					sleepThread();
					System.out.println("");
					System.out.println(dbPhone + "님은 이미 탈퇴된 회원이십니다.");
					sleepThread2();
					System.out.println("로그인 허브 페이지로 이동합니다...");
					
					return false;
				}
			
				if(dbPhone.equals(phone)) {
					sleepThread();
					System.out.println("");
					System.out.println("입력이 완료되었습니다.");
					sleepThread2();
					System.out.println(dbName + "님은 현재 " + dbStamp + "개의 스탬프를 가지고 계십니다.");
					sleepThread2();
					System.out.println("회원탈퇴 시 남은 스탬프는 모두 소멸합니다.");
					sleepThread2();
					System.out.println("탈퇴 하시려면 '회원탈퇴'를, 나머지를 입력하시면 로그인 허브 페이지로 이동합니다.");
					System.out.print("입력 : ");
					String qQuit = InputUtil.sc.nextLine().trim();
					
					
					if(qQuit.equals("회원탈퇴")) {
						
						System.out.println("");
						System.out.println("회원탈퇴를 선택하셨습니다.");
						sleepThread();
						System.out.println("회원탈퇴를 진행합니다.");
						System.out.println("");
						
						
						String sql2 = "UPDATE CUSTOMER SET STAMP = ?, QUIT = ?  WHERE NO = ?";
						
						PreparedStatement pstmt2 = null;
						
						try {
							pstmt2 = conn.prepareStatement(sql2);
							pstmt2.setInt(1, 0);
							pstmt2.setString(2, "Y");
							pstmt2.setInt(3, dbNo);
							int result = pstmt2.executeUpdate();
							
							
							if (result == 1 ) {
								
								System.out.println("로그인된 경우 자동으로 로그아웃 됩니다.");
								sleepThread3();
								System.out.println(dbName+"님. 지금까지 2SOME을 멤버쉽을 이용해주셔서 감사합니다.");
								
								Stamp.usingStamp = 0;
								loginCustomerNo = 0;
								
								sleepThread();
								System.out.println("회원탈퇴가 완료되었습니다.");
								System.out.println("즐거운 하루 되시길 바랍니다.");
								sleepThread();
								
								return true;
								
							} else {
								System.out.println("회원탈퇴에 실패하였습니다.");
								System.out.println("다시 한 번 시도해보시길 바랍니다.");
								return false;
							}
						} catch (SQLException e) {
							System.out.println("죄송합니다. 서버와 연결이 끊겼습니다.");
							System.out.println("입력하신 정보가 옳바른 것인지 확인 부탁드립니다.");
							return false ;
							
						} finally {
							OracleDB.close(conn);
							OracleDB.close(pstmt);
							OracleDB.close(rs);
						}
						
						
					} else {
						System.out.println("");
						System.out.println("로그인 허브 페이지로 돌아갑니다...");
						sleepThread();
						
						return false;
					}
					
				}
			}
			
		} catch (SQLException e) {
			System.out.println("죄송합니다. 서버와의 연결 중 오류가 발생하였습니다.");
			sleepThread();
			System.out.println("입력하신 정보가 옳바른 정보인지 다시 한 번 확인해보시고");
			sleepThread();
			System.out.println("다시 시도해보시길 바랍니다.");
			sleepThread();
			return false;
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}
		return false;
		
		
		/*로그아웃 */
		
		
		
		
		
	}
	
	
	public static boolean join() {
		
		int cnt = 0;
		boolean bl = true;
		
		System.out.println("");
		
		System.out.println("2some 포인트맴버쉽 회원가입 페이지입니다.");
		sleepThread();
		if (loginCustomerNo != 0) {
			sleepThread();
			
			System.out.println("");
			System.out.println("안녕하세요 " + customersName + " 님.");
			System.out.println("이미 접속중 이므로 " + customersName + "님의 마이 멤버쉽 페이지로 이동합니다.");
			System.out.println("");
			sleepThread2();
			
			MembershipHub.plaitCustomersStamp();
		}
		System.out.println("고객님을 환영합니다.");
		sleepThread();
		System.out.println("");
		System.out.println("======================");
		System.out.println("========회원가입========");
		
		sleepThread();
		System.out.println("고객님의 성함과 대쉬(-)를 포함한 전화번호 11자리");
		sleepThread();
		System.out.println("그리고 슬래쉬(/)를 포함한 생년월일 8자리(yyyy/mm/dd)를 차례대로 입력해주시길 바랍니다.");
		sleepThread();
		System.out.println("뒤로가기를 원하시는 고객님께선 이름에 '뒤로가기'를 입력하여 주시길 바랍니다.");
		sleepThread();
		System.out.println("재가입을 원하시는 고객님께선 이름에 '재가입'을 입력하여 주시길 바랍니다.");
		System.out.println("");
		
		while(bl) {
		System.out.print("이름 : ");
		String name = InputUtil.sc.nextLine().trim();
		
		if(name.equals("뒤로가기")) {
			System.out.println("뒤로가기를 입력하셨습니다.");
			CustomerHub.plaitLoginJoin();
		}
		
		if(name.equals("재가입")) {
			sleepThread();
			System.out.println("재가입을 입력하셨습니다.");
			System.out.println("");
			sleepThread();
			reJoin();
		}
		
		System.out.print("전화번호 : ");
		String phone = InputUtil.sc.nextLine().trim();
		
		System.out.print("생년월일 : ");
		String birthDay = InputUtil.sc.nextLine().trim();
		
		//전화번호 유효성 검사 (db 접속 필요 x)
		
		
		if(phone.length() != 13) {
			System.out.println("전화번호는 대쉬(-) 포함 13자리를 입력하여야 합니다.");
			System.out.println("회원가입을 다시 진행하여 주시길 바랍니다.");
			CustomerHub.plaitLoginJoin();
		} 
		else if (validPhoneNumber(phone) == false ) {
			System.out.println("010-OOOO-OOOO와 같은 형태로 작성해주시길 바랍니다.");
			System.out.println("회원가입을 다시 진행하여 주시길 바랍니다.");
			CustomerHub.plaitLoginJoin();
		}
		if(name.length() > 4 || name.length() < 2) {
			System.out.println("고객님의 성함 2~4자리를 입력하여 주시길 바랍니다.");
			System.out.println("회원가입을 다시 진행하여 주시길 바랍니다.");
			CustomerHub.plaitLoginJoin();
		} else if (validName(name) == false ) {
			System.out.println("성함은 한글이름으로 적어주시길 바랍니다.");
			System.out.println("회원가입을 다시 진행하여 주시길 바랍니다.");
			CustomerHub.plaitLoginJoin();
		} 
		if(birthDay.length() != 10) {
			sleepThread();
			System.out.println("생년월일은 yyyy/mm/dd 형식으로만 기입할 수 있습니다.");
			System.out.println("회원가입을 다시 진행하여 주시길 바랍니다.");
			CustomerHub.plaitLoginJoin();
			
		} else if(validationDate(birthDay) == false) {
			sleepThread();
			System.out.println("생년월일은 yyyy/mm/dd 형식으로만 기입할 수 있습니다.");
			System.out.println("회원가입을 다시 진행하여 주시길 바랍니다.");
			CustomerHub.plaitLoginJoin();
		}

			
		//1. db접속
		
		Connection conn = common.db.OracleDB.getOracleConnection();
		
		//2. db에서 현재 전화번호와 일치하는 전화번호 조회
		
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		
			String sql = "SELECT * FROM CUSTOMER WHERE PHONE = ? ";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, phone);
				rs = pstmt.executeQuery();
				
				
				if(rs.next()) {
					System.out.println("죄송합니다. 이미 사용중인 전화번호입니다.");
					CustomerHub.plaitLoginJoin();
				} 
				
				String sqlInsert
				= "INSERT INTO CUSTOMER(NO,NAME,PHONE,BIRTH)"
					+ "VALUES(CUSTOMER_NO_SEQ.NEXTVAL,?,?,?)";
				pstmt2 = conn.prepareStatement(sqlInsert);
				pstmt2.setString(1, name);
				pstmt2.setString(2, phone);
				pstmt2.setString(3, birthDay);
				int result = pstmt2.executeUpdate();
				
				
				if (result ==1 ) {
					System.out.println("");
					System.out.println("회원가입에 성공하였습니다.");
					sleepThread();
					System.out.println("로그인 페이지으로 이동합니다.");
					sleepThread();
					login();
				}
				
				}
						
			
			 catch (SQLException e) {
				System.out.println("죄송합니다. 서버와 연결이 끊겼습니다.");
				System.out.println("입력하신 정보가 옳바른 것인지 확인 부탁드립니다.");
			
			 } finally {
				OracleDB.close(conn);
				OracleDB.close(pstmt);
				OracleDB.close(rs);
			}
			
			sleepThread();
			System.out.println("회원가입에 실패하였습니다. 계속 시도하시려면 1번, 바로 결제하시려면 2번을 선택해주세요.");
			sleepThread();
			System.out.println("1 : 계속 시도, 2 : 바로 결제");
			
			
			
			String inputInt = InputUtil.sc.nextLine();
			boolean blValidNo = validNo(inputInt);
			if(blValidNo == false) {
				System.out.println("잘못된 번호입니다. 다시 시도하시길 바랍니다.");
				CustomerHub.plaitLoginJoin();
			}
			
			if(inputInt.equals("1")) {
				System.out.println("회원가입 계속 시도를 선택하셨습니다.");
				cnt++;
				}
			else if (inputInt.equals("2")) {
				System.out.println("바로 결제를 선택하셨습니다.");
				sleepThread();
				System.out.println("결제 페이지로 이동합니다.");
				
				//결제 페이지로 이동
			}
			else {
				System.out.println("잘못된 번호입니다."); 
				System.out.println("회원가입을 다시 시도합니다.");}
			if(cnt == 3) {bl = false;}
			
			}
			
			System.out.println("회원가입에 3회 실패하였습니다.");
			sleepThread();
			System.out.println("결제 페이지로 이동합니다.");
			//결제 페이지 이동
			
			return false;
		}


	
	
	public static void reJoin() {
		
		System.out.println("");
		
		System.out.println("=======재가입 페이지=======");
		sleepThread();
		System.out.println("재가입 페이지로 이동하였습니다.");
		sleepThread();
		System.out.println("멤버십을 이미 탈퇴한 고객님의 재가입을 할 수 있습니다.");
		sleepThread();
		System.out.println("");
		
		if (loginCustomerNo != 0) {
			sleepThread();
			
			System.out.println("");
			System.out.println("안녕하세요 " + customersName + " 님.");
			System.out.println("이미 접속중 이므로 " + customersName + "님의 마이 멤버쉽 페이지로 이동합니다.");
			System.out.println("");
			sleepThread2();
			
			MembershipHub.plaitCustomersStamp();
		}
			sleepThread();
			System.out.println("고객님의 성함과 대쉬(-)를 포함한 전화번호 11자리");
			sleepThread();
			System.out.println("그리고 슬래쉬(/)를 포함한 생년월일 8자리(yyyy/mm/dd)를 차례대로 입력해주시길 바랍니다.");
			sleepThread();
			System.out.println("뒤로가기를 원하시는 고객님께선 이름에 '뒤로가기'를 입력하여 주시길 바랍니다.");
			sleepThread();
			System.out.println("");
			
//			while(bl) {
			System.out.print("이름 : ");
			String name = InputUtil.sc.nextLine().trim();
			
			if(name.equals("뒤로가기")) {
				System.out.println("뒤로가기를 입력하셨습니다.");
				CustomerHub.plaitLoginJoin();
			}
			
			System.out.print("전화번호 : ");
			String phone = InputUtil.sc.nextLine().trim();
			
			System.out.print("생년월일 : ");
			String birthDay = InputUtil.sc.nextLine().trim();
			
			
			Connection conn = common.db.OracleDB.getOracleConnection();
			
			PreparedStatement pstmt = null;
			
			String sql = "UPDATE CUSTOMER SET QUIT = ? WHERE PHONE = ?";
			
			try {
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "N");
				pstmt.setString(2, phone);
				
				
				int result = pstmt.executeUpdate();
				
				if(result == 1) {
					
					System.out.println("");
					sleepThread2();
					System.out.println("안녕하세요 " + name + "님! 돌아오신걸 환영합니다.");
					sleepThread2();
					System.out.println("지금부터 정상적으로 스탬프 적립과 사용 등 멤버쉽 혜택을 받으실 수 있습니다.");
					sleepThread2();
					System.out.println("스탬프 적립 및 사용을 하기 위해서 로그인해주시길 바랍니다.");
					sleepThread2();
					System.out.println("앞으로도 발전하는 모습을 보여드리는 2SOME이 되겠습니다.");
					sleepThread2();
					System.out.println("");
					System.out.println("감사합니다.");
					sleepThread2();
					System.out.println("");
					System.out.println("로그인 허브로 이동합니다.");
					sleepThread2();
					CustomerHub.plaitLoginJoin();
				} else {
					System.out.println("재가입에 실패하였습니다. 입력하신 정보를 다시 한 번 확인하시어");
					System.out.println("다시 시도해보시길 바랍니다.");
					sleepThread();
					System.out.println("로그인 허브로 이동합니다.");
					sleepThread();
					CustomerHub.plaitLoginJoin();
				}
				
				}	catch (SQLException e) {
					System.out.println("죄송합니다. 서버와 연결이 끊겼습니다.");
					System.out.println("입력하신 정보가 옳바른 것인지 확인 부탁드립니다.");
					return;
				} finally {
					OracleDB.close(conn);
					OracleDB.close(pstmt);
				}
			
			}
		
		
		



	public static void sleepThread() {
		try {Thread.currentThread().sleep(1000);} catch (InterruptedException e) {System.out.println("sleep 중 예외 발생!!");}
	}
	
	public static void sleepThread2() {
		try {Thread.currentThread().sleep(2000);} catch (InterruptedException e) {System.out.println("sleep 중 예외 발생!!");}
	}
	
	public static void sleepThread3() {
		try {Thread.currentThread().sleep(3000);} catch (InterruptedException e) {System.out.println("sleep 중 예외 발생!!");}
	}
	
	public static boolean validPhoneNumber(String number) {
        Pattern pattern = Pattern.compile("010-\\d{4}-\\d{4}");
        Matcher matcher = pattern.matcher(number);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
	
	public  static boolean  validationDate(String checkDate){
		
		try{
		  SimpleDateFormat  dateFormat = new  SimpleDateFormat("yyyy/MM/dd");
		  dateFormat.setLenient(false);
		  dateFormat.parse(checkDate);
		  return  true;
		   }catch (ParseException  e){return  false;}
		}
	
	public static boolean validNo(String checkNo) {
		
		Pattern pattern = Pattern.compile("^[0-9]*$");
        Matcher matcher = pattern.matcher(checkNo);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }

	}
	
	public static boolean validName(String checkName) {
		
		Pattern pattern = Pattern.compile("^[ㄱ-ㅎ가-힣]*$");
        Matcher matcher = pattern.matcher(checkName);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }

	}
	
	
	
}
	