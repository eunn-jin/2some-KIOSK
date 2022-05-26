package user.menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.db.OracleDB;
import common.util.InputUtil;

public class CategoryMenu {

	public int categoryNum;

	// 카테고리 보여주는 메소드
	public void showCategory() {
	      Connection conn = OracleDB.getOracleConnection();
	        
	        String sql = "SELECT CATEGORY_NAME FROM CATEGORY";
	        ResultSet rs = null;
	        
	        try {
	        	PreparedStatement  pstmt = conn.prepareStatement(sql);
	           rs = pstmt.executeQuery();

	            while(rs.next()) {
	              System.out.println(rs.getString(1));
//	            	int rw= rs.getInt("CATEGORY_IDX");
//	            	String name = rs.getString("CATEGORY_NAME");
//					System.out.println(rw + ". " + name);
	            }
	            
	            System.out.println("카테고리를 선택하세요.");
		        System.out.print("카테고리 번호 : ");
		        int categoryNum = InputUtil.inputInt();
		        
		        showMenu(categoryNum);
	        } 
	        catch (SQLException e) {
	           e.printStackTrace();
	        }
	        
	        
	          
	    }

	   // 카테고리 불러와서 메뉴 보여주는 메소드
	   public void showMenu(int categoryNum) {
		   
	          Connection conn = OracleDB.getOracleConnection();
	          
//	          String sql2 = "SELECT MN_NAME FROM MENU WHERE CATEGORY_IDX = ? ";
	          String sql2 = "SELECT MN_NAME, PRICE FROM MENU WHERE CATEGORY_IDX = ? ";
//	          String sql2 = "SELECT MN_NAME, PRICE"
//	        		  + "FROM MENU"
//	        		  + "WHERE CATEGORY_IDX = ?";
	         
	        		  
	          PreparedStatement pstmt2 = null;
	          ResultSet rs2 = null;
	          
	          
	          try {
	          pstmt2 = conn.prepareStatement(sql2);
	          pstmt2.setInt(1, categoryNum);
	          
	             rs2 = pstmt2.executeQuery();

	             while(rs2.next()) {
	            	 System.out.println(rs2.getString(1));
//	            	 String name = rs2.getString("MN_NAME");
//					   int price = rs2.getInt("PRICE");
//					   System.out.println(name + ". " + price);	                
	              }
	             System.out.println();
	             System.out.println("메뉴를 선택하세요.");
			        System.out.print("메뉴 번호 : ");
			        
			        int menuNum = InputUtil.inputInt();
			        if(menuNum==1) {
			        	// 각 디테일 메소드로 이동
			        	detail(categoryNum);
			        	
			        }
			        
	       } catch (SQLException e) {
	          e.printStackTrace();
	       }finally {
				OracleDB.close(conn);
				OracleDB.close(pstmt2);
				OracleDB.close(rs2);
			}
	   }
	   
	   // 상세정보 불러와서 보여주는 메소드
	   public void detail(int categoryNum) {
		   
	          
	          System.out.println("디테일");
	          
	          
	   }
		  
	   
	   
	   
	   // 선택하면, 옵션 고르게 하는 메소드

}
