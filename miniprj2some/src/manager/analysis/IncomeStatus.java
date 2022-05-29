package manager.analysis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.db.OracleDB;
import common.util.InputUtil;

public class IncomeStatus {	//기간 별 매출 현황 조회
	
	public void start() {
		getList();
		
		boolean loopflag = true;
		while(loopflag) {
			int n = InputUtil.inputInt();
			
			switch(n) {
				case 1: getDayIncome(); getList(); break;
				case 2: getMonthIncome(); getList(); break;
				case 3: getYearIncome(); getList(); break;
				case 4: getPeriodIncome(); getList(); break;
				case 0: loopflag = false; break;
				default: System.out.println("잘못입력하셨습니다. 정확한 번호를 입력하세요.\n");
			}
		}
	}
	
	public void getDayIncome() {
		System.out.println("\n원하시는 일을 입력하세요. (ex. YY/MM/DD)");
		String day = InputUtil.inputStr();
		
		Connection conn = OracleDB.getOracleConnection();
		
		String sql = "SELECT TOTAL_PRICE + USE_POINT + DISCOUNT_PRICE PRICE "
				+ "FROM ORDER_GROUP "
				+ "WHERE TO_CHAR(ORDER_DATE, 'YY/MM/DD') = TO_CHAR(?, 'YY/MM/DD')";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
        try {
        	pstmt = conn.prepareStatement(sql);
        	pstmt.setString(1, day);
			rs = pstmt.executeQuery();
			Outputform(rs);
		} catch (SQLException e) {
			System.out.println("입력이 잘못되었습니다.\n");
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}
	}
	
	public void getMonthIncome() {
		System.out.println("\n원하시는 월을 입력하세요. (ex. YYYY/MM)");
		String str = InputUtil.inputStr();
		
		String[] day = str.split("/");
		
		int year = Integer.parseInt(day[0]);
		int month = Integer.parseInt(day[1]);
		
		Connection conn = OracleDB.getOracleConnection();
		
		String sql = "SELECT TOTAL_PRICE + USE_POINT + DISCOUNT_PRICE PRICE "
				+ "FROM ORDER_GROUP "
				+ "WHERE EXTRACT(YEAR FROM ORDER_DATE) = ? AND EXTRACT(MONTH FROM ORDER_DATE) = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
        try {
        	pstmt = conn.prepareStatement(sql);
        	pstmt.setInt(1, year);
        	pstmt.setInt(2, month);
			rs = pstmt.executeQuery();
			Outputform(rs);
		} catch (SQLException e) {
			System.out.println("입력이 잘못되었습니다.\n");
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}
	}
	
	public void getYearIncome() {
		System.out.println("\n원하시는 년을 입력하세요. (ex. YYYY)");
		int year = InputUtil.inputInt();
		
		Connection conn = OracleDB.getOracleConnection();
		
		String sql = "SELECT TOTAL_PRICE + USE_POINT + DISCOUNT_PRICE PRICE "
				+ "FROM ORDER_GROUP "
				+ "WHERE EXTRACT(YEAR FROM ORDER_DATE) = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
        try {
        	pstmt = conn.prepareStatement(sql);
        	pstmt.setInt(1, year);
			rs = pstmt.executeQuery();
			Outputform(rs);
		} catch (SQLException e) {
			System.out.println("입력이 잘못되었습니다.\n");
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}
	}
	
	public void getPeriodIncome() {
		System.out.println("\n원하시는 기간을 입력하세요. (ex. 시작일:YY/MM/DD, 마지막일:YY/MM/DD)");
		System.out.print("시작일: ");
		String start = InputUtil.inputStr();
		System.out.print("마지막일: ");
		String end = InputUtil.inputStr();
		
		Connection conn = OracleDB.getOracleConnection();
		
		String sql = "SELECT TOTAL_PRICE + USE_POINT + DISCOUNT_PRICE PRICE "
				+ "FROM ORDER_GROUP "
				+ "WHERE TO_CHAR(ORDER_DATE, 'YY/MM/DD') BETWEEN TO_CHAR(? ,'YY/MM/DD') AND TO_CHAR(? ,'YY/MM/DD')";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
        try {
        	pstmt = conn.prepareStatement(sql);
        	pstmt.setString(1, start);
        	pstmt.setString(2, end);
			rs = pstmt.executeQuery();
			Outputform(rs);
		} catch (SQLException e) {
			System.out.println("입력이 잘못되었습니다.\n");
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}
	}
	
	private void Outputform(ResultSet rs) {
		int sum_price = 0;
		try {
			while(rs.next()) {
				sum_price += Integer.parseInt(rs.getString("PRICE"));
			}
			System.out.println("총 매출 :"+ sum_price + " 원\n");
		} catch (NumberFormatException | SQLException e) {
			System.out.println("Outputform함수에서 에러발생!");
		}
	}
	
	public void getList() {
		System.out.println("===== 확인하고 싶은 기간을 선택하시오 =====");
		System.out.println("1. 일간");
		System.out.println("2. 월간");
		System.out.println("3. 년간");
		System.out.println("4. 기간 지정");
		System.out.println("0. 이전 화면으로 돌아가기");
	}
	
}
