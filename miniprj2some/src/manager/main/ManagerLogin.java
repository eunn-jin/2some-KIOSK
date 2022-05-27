package manager.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import common.db.OracleDB;
import common.util.InputUtil;

public class ManagerLogin {
	
	public boolean login() {
		System.out.println("=====로그인=====");
		System.out.print("아이디 :");
		String id = InputUtil.sc.nextLine();
		System.out.print("패스워드 :");
		String pwd = InputUtil.sc.nextLine();
		
		Connection conn = OracleDB.getOracleConnection();
		
		String sql = "SELECT PWD FROM LOGIN WHERE ID = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String dbpwd = rs.getString(1);
				if(dbpwd.equalsIgnoreCase(pwd)) {
					System.out.println("로그인 성공 !!!");
					return true;
				}
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 예외 발생 !!!");
		}finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}
		
		System.out.println("아이디/ 비밀번호 오류가 발생했습니다. 다시 입력하세요!");
		login();
		return false;
		//로그인 실패시 다시 로그인창으로 되돌아가도록 하기
		
	}
	
	
	public boolean join() {
		System.out.println("=====회원가입=====");
		System.out.print("아이디 :");
		String id = InputUtil.sc.nextLine();
		System.out.print("비밀번호 :");
		String pwd = InputUtil.sc.nextLine();
		System.out.print("이름 :");
		String name = InputUtil.sc.nextLine();
		System.out.print("성별 :");
		String gender = InputUtil.sc.nextLine();
		System.out.print("생년월일 :");
		String birth = InputUtil.sc.nextLine();
		System.out.print("전화번호 :");
		String phone = InputUtil.sc.nextLine();
		System.out.print("이메일 :");
		String email = InputUtil.sc.nextLine();
		
		if(pwd.length() < 4) {
			System.out.println("회원가입 실패... 비밀번호는 4글자 이상이어야 됩니다.");
			return false;
		}
		
		Connection conn = OracleDB.getOracleConnection();
		
		try {
			String sql = "SELECT * FROM LOGIN WHERE ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				System.out.println("중복된 아이디가 있습니다!");
				return false;
			}
			
			String sqlInsert
			= "INSERT INTO LOGIN(NO,ID,PWD,NAME,GENDER,BIRTH,PHONE,EMAIL)"
					+ "VALUES(LOGIN_NO_SEQ.NEXTVAL,?,?,?,?,?,?,?)";
			
			PreparedStatement pstmt2 = conn.prepareStatement(sqlInsert);
			pstmt2.setString(1, id);
			pstmt2.setString(2, pwd);
			pstmt2.setString(3, name);
			pstmt2.setString(4, gender);
			pstmt2.setString(5, birth);
			pstmt2.setString(6, phone);
			pstmt2.setString(7, email);
			int result = pstmt2.executeUpdate();
			
			if(result == 1) {
				System.out.println("회원가입 성공!!!");
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("회원가입 실패...");
		return false;
		
	}
	
	public boolean logout() {
		System.out.println("=====로그아웃=====");
		System.out.print("이름 : ");
		String name = InputUtil.sc.nextLine();
		
		Connection conn = OracleDB.getOracleConnection();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM LOGIN WHERE NAME = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String dbname = rs.getString("name");
				if(dbname.equalsIgnoreCase(name)) {
					System.out.println("로그아웃 되었습니다!");
					return true;
				//로그아웃 했을때 메인에서 회원가입,로그인 화면뜨게하기
				}
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 예외 발생!");
		}finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}
		
		return false;
		
	}
	

}
