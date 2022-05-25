package manager.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import manager.main.OracleDB;
import manager.main.MyUtil;

public class ManagerLogin {
	
	public boolean login() {
		System.out.println("=====로그인=====");
		System.out.print("아이디 :");
		String id = MyUtil.sc.nextLine();
		System.out.print("패스워드 :");
		String pwd = MyUtil.sc.nextLine();
		
		Connection conn = OracleDB.getOracleConnection();
		
		String sql = "SELECT PWD FROM LOGIN WHERE UPPER(ID) = UPPER(?)";
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
		
		System.out.println("로그인 실패!!!");
		return false;
		
	}
	
	
	public boolean join() {
		System.out.println("=====회원가입=====");
		System.out.print("아이디 :");
		String id = MyUtil.sc.nextLine();
		System.out.print("비밀번호 :");
		String pwd = MyUtil.sc.nextLine();
		System.out.print("이름 :");
		String name = MyUtil.sc.nextLine();
		System.out.println("성별 :");
		String gender = MyUtil.sc.nextLine();
		System.out.println("생년월일 :");
		String birth = MyUtil.sc.nextLine();
		System.out.println("전화번호 :");
		String phone = MyUtil.sc.nextLine();
		System.out.println("이메일 :");
		String email = MyUtil.sc.nextLine();
		
		if(pwd.length() < 4) {
			System.out.println("비밀번호는 4글자 이상이어야 됩니다.");
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
					+ "VALUES(?,?,?,?,?,?,?,?)";
			
			PreparedStatement pstmt2 = conn.prepareStatement(sqlInsert);
			pstmt2.setInt(1, 8);
			pstmt2.setString(2, id);
			pstmt2.setString(3, pwd);
			pstmt2.setString(4, name);
			pstmt2.setString(5, gender);
			pstmt2.setString(6, birth);
			pstmt2.setString(7, phone);
			pstmt2.setString(8, email);
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

}
