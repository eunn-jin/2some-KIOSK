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

import common.db.OracleDB;
import common.util.InputUtil;


public class Customer {
	
	 private String name = "";
	 private String phone = "";
	 private String birthDay = "";
	 public static int loginCustomerNo = 0;
	 public static int keepStamp =0;
	 public static String customersName = "";
	
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
			return true;
		}
		
		System.out.print("전화번호 : ");
		String phone = InputUtil.sc.nextLine().trim();
		
		System.out.print("생년월일 : ");
		String birthDay = InputUtil.sc.nextLine().trim();
		
		
		
		
		//DB 연결 얻기 
		
		Connection conn = common.db.OracleDB.getOracleConnection();
		
		
		//해당 이름에 맞는 전화번호 디비에서 조회하기
		String sql = "SELECT NO, PHONE, STAMP, NAME FROM CUSTOMER WHERE NAME = ? AND BIRTH = ? ";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, birthDay);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String dbPhone = rs.getString("PHONE");
				int dbNo = rs.getInt("NO");
				int dbStamp = rs.getInt("STAMP");
				String dbName = rs.getString("NAME");
				
				if(dbPhone.equals(phone)) {
					
					loginCustomerNo = dbNo;
					keepStamp = dbStamp;
					customersName = dbName;
					sleepThread();
					
					System.out.println("");
					System.out.println("=======================");
					System.out.println("");
					System.out.println("안녕하세요." + name + "님.");
					System.out.println("로그인 성공하였습니다.");
					System.out.println("마이 멤버쉽 페이지로 이동합니다.");
					System.out.println("");
					sleepThread();
					
					user.stamp.MembershipHub.plaitCustomersStamp();
				}
			}
		} catch (SQLException e) {
			System.out.println("SQL에서 예외 발생");
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}
		
		
		
		
		
		System.out.println("로그인에 실패하였습니다. 다시 시도하시길 바랍니다.");
		cnt++;
		if(cnt == 3) {bl = false;}
		
		}
		
		System.out.println("로그인에 3회 실패하였습니다.");
		System.out.println("회원가입 페이지로 이동합니다.");
		join();
		return false;
		
		
		
		
	}
	
	
	public static boolean logout() {
		
		
		/*로그아웃 */
		
		
		
		return false;
		
	}
	
	public static boolean quitMember() {
		
		
		
		/*로그아웃 */
		
		
		
		return false;
		
	}
	
	
	public static boolean join() {
		
		int cnt = 0;
		boolean bl = true;
		
		System.out.println("");
		
		System.out.println("2some 포인트맴버쉽 회원가입 페이지입니다.");
		sleepThread();
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
		System.out.println("뒤로가기를 원하시는 고객님께선 이름에 뒤로가기를 입력하여 주시길 바랍니다.");
		sleepThread();
		System.out.println("");
		
		while(bl) {
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
		
		//전화번호 유효성 검사 (db 접속 필요 x)
		
		
		if(phone.length() != 13) {
			System.out.println("전화번호는 대쉬(-) 포함 13자리를 입력하여야 합니다.");
			System.out.println("회원가입을 다시 진행하여 주시길 바랍니다.");
			return false;
		} 
		else if (validPhoneNumber(phone) == false ) {
			System.out.println("010-OOOO-OOOO와 같은 형태로 작성해주시길 바랍니다.");
			System.out.println("회원가입을 다시 진행하여 주시길 바랍니다.");
			return false;
		}
		if(name.length() > 4 || name.length() < 2) {
			System.out.println("고객님의 성함 2~4자리를 입력하여 주시길 바랍니다.");
			System.out.println("회원가입을 다시 진행하여 주시길 바랍니다.");
			return false;
		} else if (validName(name) == false ) {
			System.out.println("성함은 한글이름으로 적어주시길 바랍니다.");
			System.out.println("회원가입을 다시 진행하여 주시길 바랍니다.");
			return false;
		} 
		if(birthDay.length() != 10) {
			sleepThread();
			System.out.println("생년월일은 yyyy/mm/dd 형식으로만 기입할 수 있습니다.");
			System.out.println("회원가입을 다시 진행하여 주시길 바랍니다.");
			return false;
			
		} else if(validationDate(birthDay) == false) {
			sleepThread();
			System.out.println("생년월일은 yyyy/mm/dd 형식으로만 기입할 수 있습니다.");
			System.out.println("회원가입을 다시 진행하여 주시길 바랍니다.");
			return false;
		}

			
		//1. db접속
		
		Connection conn = common.db.OracleDB.getOracleConnection();
		
		//2. db에서 현재 전화번호와 일치하는 전화번호 조회
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
			String sql = "SELECT * FROM CUSTOMER WHERE PHONE = ? AND BIRTH = ?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, phone);
				pstmt.setString(2, birthDay);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					System.out.println("죄송합니다. 이미 사용중인 전화번호입니다.");
					return false;
				}
				
				String sqlInsert
				= "INSERT INTO CUSTOMER(NO,NAME,PHONE,BIRTH)"
					+ "VALUES(CUSTOMER_NO_SEQ.NEXTVAL,?,?,?)";
				PreparedStatement pstmt2 = conn.prepareStatement(sqlInsert);
				pstmt2.setString(1, name);
				pstmt2.setString(2, phone);
				pstmt2.setString(3, birthDay);
				int result = pstmt2.executeUpdate();
				
				
				if (result ==1 ) {
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
			System.out.println("1번 : 계속 시도, 2번 : 바로 결제");
			int i = InputUtil.inputInt();
			
			if(i==1) {
				System.out.println("회원가입 계속 시도를 선택하셨습니다.");
				cnt++;
				}
			else if (i==2) {
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
			
			
			return false;
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
	