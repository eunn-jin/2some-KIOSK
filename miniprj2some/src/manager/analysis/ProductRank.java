package manager.analysis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.db.OracleDB;
import common.util.InputUtil;

public class ProductRank {	// 누적 상품 판매 순위

	public void start() {
		getList();
		
		boolean loopflag = true;
		while(loopflag) {
			int n = InputUtil.inputInt();
			
			switch(n) {
				case 1: getDayRank(); getList(); break;
				case 2: getMonthRank(); getList(); break;
				case 3: getPeriodRank(); getList(); break;
				case 0: loopflag = false; break;
				default: System.out.println("잘못입력하셨습니다. 정확한 번호를 입력하세요.\n");
			}
		}
	}
	
	public void getDayRank() {
		System.out.println("\n원하시는 일을 입력하세요. (ex. YY/MM/DD)");
		String day = InputUtil.inputStr();
		
		Connection conn = OracleDB.getOracleConnection();
		
		String sql = "SELECT MN_NAME, NUM "
				+ "FROM (SELECT MN_IDX, SUM(ITEM_NUM) NUM "
				+ "FROM ORDER_ITEM "
				+ "WHERE ORDER_ITEM.GROUP_NO IN (SELECT GROUP_NO FROM ORDER_GROUP WHERE ORDER_DATE = TO_DATE(?)) "
				+ "GROUP BY MN_IDX) T JOIN MENU "
				+ "ON T.MN_IDX = MENU.MN_IDX "
				+ "ORDER BY NUM DESC";
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
	
	public void getMonthRank() {
		System.out.println("\n원하시는 월을 입력하세요. (ex. MM)");
		int month = InputUtil.inputInt();
		
		Connection conn = OracleDB.getOracleConnection();
		
		String sql = "SELECT MN_NAME, NUM "
				+ "FROM (SELECT MN_IDX, SUM(ITEM_NUM) NUM "
				+ "FROM ORDER_ITEM "
				+ "WHERE ORDER_ITEM.GROUP_NO IN (SELECT GROUP_NO FROM ORDER_GROUP WHERE EXTRACT(MONTH FROM ORDER_DATE) = ?) "
				+ "GROUP BY MN_IDX) T JOIN MENU "
				+ "ON T.MN_IDX = MENU.MN_IDX "
				+ "ORDER BY NUM DESC";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
        try {
        	pstmt = conn.prepareStatement(sql);
        	pstmt.setInt(1, month);
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
	
	public void getPeriodRank() {
		System.out.println("\n원하시는 기간을 입력하세요. (ex. 시작일:YY/MM/DD, 마지막일:YY/MM/DD)");
		System.out.print("시작일: ");
		String start = InputUtil.inputStr();
		System.out.print("마지막일: ");
		String end = InputUtil.inputStr();
		
		Connection conn = OracleDB.getOracleConnection();
		
		String sql = "SELECT MN_NAME, NUM "
				+ "FROM (SELECT MN_IDX, SUM(ITEM_NUM) NUM "
				+ "FROM ORDER_ITEM "
				+ "WHERE ORDER_ITEM.GROUP_NO IN (SELECT GROUP_NO FROM ORDER_GROUP WHERE ORDER_DATE BETWEEN TO_DATE(?) AND TO_DATE(?)) "
				+ "GROUP BY MN_IDX) T JOIN MENU "
				+ "ON T.MN_IDX = MENU.MN_IDX "
				+ "ORDER BY NUM DESC";
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
		int rank = 1;
		try {
			if(rs.next()) {
				System.out.println("\n메뉴 별 판매갯수");
				System.out.println(rank++ + "순위) "+rs.getString("MN_NAME")+" : "+rs.getInt("NUM"));
				while(rs.next()) {
					System.out.println(rank++ + "순위) "+rs.getString("MN_NAME")+" : "+rs.getInt("NUM"));
				}
				System.out.println();
			} else {
				System.out.println("판매된 상품이 없습니다.\n");
			}
		} catch (SQLException e) {
			System.out.println("Outputform함수에서 에러발생!");
		}
	}
	
	public void getList() {
		System.out.println("===== 확인하고 싶은 기간을 선택하시오 =====");
		System.out.println("1. 일간");
		System.out.println("2. 월간");
		System.out.println("3. 기간 지정");
		System.out.println("0. 이전 화면으로 돌아가기");
	}
}
