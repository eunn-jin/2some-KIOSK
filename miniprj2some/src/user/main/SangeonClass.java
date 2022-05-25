package user.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class SangeonClass {
	
public static Scanner sc = new Scanner(System.in);
	
	public static boolean login() {
		
		int cnt = 0;
		boolean bl = true;
		
		System.out.println("로그인을 선택하셨습니다.");
		
		sleepThread();
		
		System.out.println("============");
		System.out.println("====로그인====");
		System.out.println("============");
		
		sleepThread();
		
		System.out.println("로그인 페이지입니다.");
		System.out.println("고객님의 성함과 대쉬(-)를 포함한 전화번호 11자리를 차례대로 입력해주시길 바랍니다.");
		
		sleepThread();
		
		while(bl) {
		
		System.out.print("이름 : ");
		String name = sc.nextLine();
		System.out.print("전화번호 : ");
		String phone = sc.nextLine();
		
		//DB 연결 얻기 
		
		Connection conn = OracleDB.getOracleConnection();
		
		//해당 이름에 맞는 전화번호 디비에서 조회하기
		String sql = "SELECT PHONE FROM CUSTOMER WHERE NAME = ? ";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String dbPhone = rs.getString(1);
				
				if(dbPhone.equals(phone)) {
					System.out.println("로그인 성공하였습니다.");
					System.out.println("포인트 사용/적립 페이지로 이동합니다.");
//					sleepThread();
				}
			}
		} catch (SQLException e) {
			System.out.println("SQL에서 예외 발생");
		} finally {
			close(conn);
			close(pstmt);
			close(rs);
		}
		
		
		
		
		
		System.out.println("로그인에 실패하였습니다. 다시 시도하시길 바랍니다.");
		cnt++;
		if(cnt == 3) {bl = false;}
		
		}
		
		System.out.println("로그인에 3회 실패하였습니다.");
		join();
		return false;
		
		
		
		
	}
	
	public static boolean join() {
		
		System.out.println("2some 포인트맴버쉽 회원가입 페이지입니다.");
		sleepThread();
		System.out.println("고객님을 환영합니다.");
		sleepThread();
		System.out.println("====회원가입====");
		System.out.println("고객님의 성함과 대쉬(-)를 포함한 전화번호 11자리를 차례대로 입력해주시길 바랍니다.");
		sleepThread();
		System.out.print("이름 : ");
		String name = sc.nextLine();
		System.out.print("전화번호 : ");
		String phone = sc.nextLine();
		
		//전화번호 유효성 검사 (db 접속 필요 x)
		
		if(phone.length() < 13 || phone.length() > 13) {
			System.out.println("전화번호는 대쉬(-) 포함 13자리를 입력하여야 합니다.");
			return false;
		} else if (phone.substring(3)!="-" || phone.substring(8)!="-") 
		{
		System.out.println("OOO-OOOO-OOOO와 같은 형태로 작성해주시길 바랍니다.");
		return false;
		}
			
		//1. db접속
		
		Connection conn = OracleDB.getOracleConnection();
		
		//2. db에서 현재 전화번호와 일치하는 전화번호 조회
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
			String sql = "SELECT * FROM CUSTOMER WHERE PHONE = ?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, phone);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					System.out.println("전화번호 중복!!!");
					return false;
				}
				
				String sqlInsert
				= "INSERT INTO CUSTOMER(NUM,NAME,PHONE)"
					+ "VALUES(?,?,?)";
				PreparedStatement pstmt2 = conn.prepareCall(sqlInsert);
				pstmt2.setInt(1, 6);
				pstmt2.setString(2, name);
				pstmt2.setString(3, phone);
				int result = pstmt2.executeUpdate();
				
				if (result ==1 ) {
					System.out.println("회원가입에 성공하였습니다.");
					return true;
				}
				
			} catch (SQLException e) {
				System.out.println("SQL 예외 발생");
			} finally {
				close(conn);
				close(pstmt);
				close(rs);
			}
			
			System.out.println("회원가입에 실패하였습니다.");
			return false;
		}


	
	
	
	public static int scInt() {
		
		int n = sc.nextInt();
		sc.nextLine(); //엔터키 제거 목적
		return n;
	}
	
	public static void sleepThread() {
		try {Thread.currentThread().sleep(1000);} catch (InterruptedException e) {System.out.println("sleep 중 예외 발생!!");}
	}
	
	public static Connection getOracleConnection() {
		
		//사전 준비
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "C##KH";
		String pwd = "KH";
		
		//드라이버 등록
		
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pwd);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("커넥션 가져오기 실패");
		}
		return conn;
	}
	
	public static void close(Connection conn) {
		if(conn != null) try {conn.close();} catch (SQLException e) {e.printStackTrace();}
	}
	
	public static void close(Statement stmt) {
		if (stmt != null) try {stmt.close();} catch (SQLException e) {e.printStackTrace();}
	} //PreparedStatement는 Statement를 상속하고 있으므로 Statement만 해도 괜찮다.
	
	public static void close(ResultSet rs) {
		if (rs != null) try {rs.close();} catch (SQLException e) {e.printStackTrace();}
	}
}

