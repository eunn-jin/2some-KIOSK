package manager.menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import common.db.OracleDB;
import common.util.InputUtil;


// MenuManager 객체 생성하고 showCategory 메소드만 호출하면 메뉴관리 돌아감

public class MenuManager {
	
	
	//카테고리 보여줌
	public void showCategory() {
		System.out.println("=============================");
		System.out.println("           메뉴 관리 ");
		System.out.println("=============================");	
		System.out.println("1. DRINK");
		System.out.println("2. FOODS");
		System.out.println("3. GOODS");
		System.out.println("0. 뒤로 가기");
		System.out.println();
		System.out.println("카테고리를 선택하시오");
		System.out.print("카테고리 번호 : ");
		int categoryNum = InputUtil.inputInt();
		System.out.println();
		System.out.println("----------------------------");
		System.out.println();
		
		if(categoryNum > 0 && categoryNum < 4) {
			showMenu(categoryNum);
		} else if(categoryNum == 0) {
			return;
		} else if(categoryNum != 1 && categoryNum != 2 && categoryNum != 3) {
			System.out.println("올바른 번호를 입력하시오");
			showCategory();
		} 
		
		
	}
	

	//메뉴 보여줌
	public void showMenu(int categoryNum) {
		
		Connection conn = OracleDB.getOracleConnection();
		
		String sql = "SELECT MN_IDX, MN_NAME "
				+ "FROM MENU "
				+ "INNER JOIN CATEGORY C ON C.CATEGORY_IDX = MENU.CATEGORY_IDX "
				+ "WHERE C.CATEGORY_IDX = ?"
				+ "ORDER BY MENU.MN_IDX"; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Set<Integer> mnIdxSet = new HashSet<>(); 
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categoryNum);
			rs = pstmt.executeQuery();
			
			System.out.println("----------------------------");
			while(rs.next()) {
				   int mIdx = rs.getInt("MN_IDX");
				   mnIdxSet.add(mIdx);
				   String mName = rs.getString("MN_NAME");
				   System.out.println(mIdx + ". " + mName);
		   }
			System.out.println();
			System.out.println("----------------------------");
			System.out.println("[메뉴추가] 00 을 입력하시오");
			System.out.println("[메뉴수정] 메뉴 번호를 입력하시오");
			System.out.println("[뒤로가기] 1111 을 입력하시오");
			System.out.print("입력 : ");		
			int menuNum = InputUtil.sc.nextInt();
			System.out.println();
			System.out.println();
			
			if(menuNum == 00) {
				plus(categoryNum, menuNum); //메뉴추가 페이지로 이동
			} else if(menuNum == 1111) {
				showCategory();
			} else if(mnIdxSet.contains(menuNum)){
				showDetail(menuNum, categoryNum);//해당 메뉴 수정 페이지로 이동
			}  else {
				System.out.print("메뉴 번호를 다시 입력하세요");
				showMenu(categoryNum);
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 예외 발생 - showMenu");
			e.printStackTrace();
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}
		
	}
	
	
	//메뉴 추가
	public void plus(int categoryNum, int menuNum) {
		
		System.out.println("---------- 메뉴 추가 ----------");		
		System.out.println();
		System.out.print("1. 상품 명 : ");
		InputUtil.sc.nextLine();
		String mName = InputUtil.inputStr();
		System.out.print("2. 가격 : ");
		int mPrice = InputUtil.inputInt();
		System.out.print("3. 상품 소개 : ");
		String mDetail = InputUtil.inputStr();
		
		Connection conn = OracleDB.getOracleConnection();
		PreparedStatement pstmt = null;
		
		//1, 2번 카테고리는 아래 상세 포함 insert
		if(categoryNum == 3) {
			String sql = "INSERT INTO MENU(MN_IDX, CATEGORY_IDX, MN_NAME, PRICE, DETAIL) "
					+ "VALUES(MENU_IDX_SEQ.NEXTVAL,?,?,?,?)";
		
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, categoryNum);
				pstmt.setString(2, mName);
				pstmt.setInt(3, mPrice);
				pstmt.setString(4, mDetail);
				int r = pstmt.executeUpdate();
				
				if(r == 1) {
					System.out.println();
					System.out.println("   [ 메뉴 등록이 완료되었습니다 ]   ");
					System.out.println();
				} else {
					System.out.println();
					System.out.println("   [ 메뉴 등록이 실패하였습니다 ]   ");
					System.out.println();
				}
				showMenu(categoryNum);
				
			} catch (SQLException e) {
				System.out.println("SQL 예외 발생 - plus cate 3");
				e.printStackTrace();
			} finally {
				OracleDB.close(conn);
				OracleDB.close(pstmt);
			}
			
		} else if(categoryNum == 1 || categoryNum == 2) {
			
			System.out.print("4. 칼로리 : ");
			int cal = InputUtil.inputInt();
			System.out.print("5. 카페인 : ");
			int caff = InputUtil.inputInt();
			System.out.print("6. 탄수화물 : ");
			int carb = InputUtil.inputInt();
			System.out.print("7. 당류 : ");
			int sugar = InputUtil.inputInt();
			System.out.print("8. 지방 : ");
			int fat = InputUtil.inputInt();
			System.out.print("9. 단백질 : ");
			int protein = InputUtil.inputInt();
			System.out.print("10. 나트륨 : ");
			int nat = InputUtil.inputInt();
			
			String sql = "INSERT INTO MENU(MN_IDX, CATEGORY_IDX, MN_NAME, PRICE, DETAIL, CAL, CAFFEINE, CARBOHYDRATE, "
					+ "SUGAR, FAT, PROTEIN, NATRIUM) "
					+ "VALUES(MENU_IDX_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?)";
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, categoryNum);
				pstmt.setString(2, mName);
				pstmt.setInt(3, mPrice);
				pstmt.setString(4, mDetail);
				pstmt.setInt(5, cal);
				pstmt.setInt(6, caff);
				pstmt.setInt(7, carb);
				pstmt.setInt(8, sugar);
				pstmt.setInt(9, fat);
				pstmt.setInt(10, protein);
				pstmt.setInt(11, nat);
				int r = pstmt.executeUpdate();
				
				if(r == 1) {
					System.out.println();
					System.out.println("   [ 메뉴 등록 완료되었습니다 ]   ");
					System.out.println();
				} else {
					System.out.println();
					System.out.println("   [ 메뉴 등록 실패하였습니다 ]   ");
					System.out.println();
				}
				showMenu(categoryNum);
				
			} catch (SQLException e) {
				System.out.println("SQL 예외 발생 - plus cate 1,2");
				e.printStackTrace();
			} finally {
				OracleDB.close(conn);
				OracleDB.close(pstmt);
			}
			
		} else {
				System.out.println("올바른 번호를 입력하시오");
				plus(categoryNum, menuNum);
		}
		
	}
	
	
	//메뉴 상세 보여줌 (수정, 삭제 시 이동하는 곳)
	public void showDetail(int menuNum, int categoryNum) {
		
		System.out.println("-----------메뉴 수정-----------");
		System.out.println();
		
		Connection conn = OracleDB.getOracleConnection();
		
		String sql = "SELECT MN_IDX, MN_NAME, PRICE, DETAIL, CAL, CAFFEINE, CARBOHYDRATE, "
				+ "SUGAR, FAT, PROTEIN, NATRIUM "
				+ "FROM MENU "
				+ "WHERE MN_IDX = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, menuNum);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
			   int mIdx = rs.getInt("MN_IDX");
			   String mName = rs.getString("MN_NAME");
			   int mPrice = rs.getInt("PRICE");
			   String mDetail = rs.getString("DETAIL");
			   int cal = rs.getInt("CAL");
			   int caff = rs.getInt("CAFFEINE");
			   int carb = rs.getInt("CARBOHYDRATE");
			   int sugar = rs.getInt("SUGAR");
			   int fat = rs.getInt("FAT");
			   int protein = rs.getInt("PROTEIN");
			   int nat = rs.getInt("NATRIUM");
			   System.out.println("1. 상품 명 : " + mName);
			   System.out.println("2. 가격 : " + mPrice);
			   System.out.println("3. 상품 소개 : " + mDetail);
			   if(categoryNum==1 || categoryNum==2) {
				   System.out.println("4. 칼로리 : " + cal);
				   System.out.println("5. 카페인 : " + caff);
				   System.out.println("6. 탄수화물 : " + carb);
				   System.out.println("7. 당류 : " + sugar);
				   System.out.println("8. 지방 : " + fat);
				   System.out.println("9. 단백질 : " + protein);
				   System.out.println("10. 나트륨 : " + nat);
			   }
			   System.out.println("9999. 상품 삭제");
			   System.out.println("[뒤로가기] 0 을 입력하시오");
			   
			   System.out.print("수정할 정보 번호 입력 : ");
			   int detailNum = InputUtil.inputInt();
			   
			   if(detailNum == 9999) {
					delete(categoryNum, menuNum, detailNum);
				} else if(detailNum > 0 && detailNum < 11) {
					update(categoryNum, menuNum, detailNum);
				} else if (detailNum == 0) {
					showMenu(categoryNum);
				} else {
					System.out.println("올바른 번호를 입력하시오");
					showDetail(menuNum, categoryNum);
				}
						
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 예외 발생 - showDetail");
			
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}
		
	}
	
	//메뉴 수정
	public void update(int categoryNum, int menuNum, int detailNum) {
		
		Connection conn = OracleDB.getOracleConnection();
		
		String sql = "";
		PreparedStatement pstmt = null;
		
		try {
			
			if(detailNum == 1 || detailNum == 3) {
				sql = updateSql(categoryNum, detailNum);
				pstmt = conn.prepareStatement(sql);
				System.out.println();
				System.out.println(" [ 수정 할 정보를 입력하시오 ] ");
				System.out.print("입력 : ");
				String str = InputUtil.inputStr();
				pstmt.setString(1, str);
				pstmt.setInt(2, menuNum);
				int r = pstmt.executeUpdate();
				System.out.println();
				System.out.println(" [ 메뉴 수정이 완료되었습니다 ]   ");
				System.out.println();
				showMenu(categoryNum);
			} else if (detailNum!=3 && detailNum>1 && detailNum<11) {
				sql = updateSql(categoryNum, detailNum);
				pstmt = conn.prepareStatement(sql);
				System.out.println();
				System.out.println(" [ 수정 할 정보를 입력하시오 ] ");
				System.out.print("입력 : ");
				String str = InputUtil.inputStr();
				pstmt.setString(1, str);
				pstmt.setInt(2, menuNum);
				int r = pstmt.executeUpdate();
				System.out.println();
				System.out.println(" [ 메뉴 수정이 완료되었습니다 ]   ");
				System.out.println();
				showMenu(categoryNum);
			} else {
				System.out.println("올바른 번호를 입력하시오");
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 예외 발생 - showMenu");
			e.printStackTrace();
			
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
		}

	}
	
	//삭제
	public void delete(int categoryNum, int menuNum, int detailNum) {
		
		System.out.println("정말 삭제하시겠습니까? 예:Y | 아니오:N");
		System.out.print("입력 : ");
		String del = InputUtil.inputStr();
		if("y".equalsIgnoreCase(del)) {
			Connection conn = OracleDB.getOracleConnection();
				
			String sql = "DELETE FROM MENU WHERE MN_IDX = ?";
			PreparedStatement pstmt = null;
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, menuNum);
				int r = pstmt.executeUpdate();
				System.out.println();
				System.out.println(" [ 메뉴 삭제 완료되었습니다 ]   ");
				System.out.println();
				showMenu(categoryNum);
				
			} catch (SQLException e) {
				System.out.println("SQL 예외 발생 - showMenu");
				e.printStackTrace();
				
			} finally {
				OracleDB.close(conn);
				OracleDB.close(pstmt);
			}
		} else if ("n".equalsIgnoreCase(del)) {
			System.out.println("삭제하지 않고 메뉴 페이지로 이동합니다.");
			showMenu(categoryNum);
		} else {
			System.out.println("유효한 값을 입력하시오");
		}
		
		
	}
	
	 public String updateSql (int categoryNum, int detailNum){
	        String sql = "UPDATE MENU SET ";
	        if(categoryNum == 1 || categoryNum == 2){
	            switch (detailNum){
	                case 1: //상품명 수정
	                    sql = sql + "MN_NAME = ?"; break;
	                case 2: //가격 수정
	                    sql = sql + "PRICE = ?"; break;
	                case 3: //상품 소개 수정
	                    sql = sql + "DETAIL = ?"; break;
	                case 4: //칼로리 수정
	                    sql = sql + "CAL = ?"; break;
	                case 5: //카페인 수정
	                    sql = sql + "CAFFEINE = ?"; break;
	                case 6: //탄수화물 수정
	                    sql = sql + "CARBOHYDRATE = ?"; break;
	                case 7: //당류 수정
	                    sql = sql + "SUGAR = ?"; break;
	                case 8: //지방 수정
	                    sql = sql + "FAT = ?"; break;
	                case 9: //단백질 수정
	                    sql = sql + "PROTEIN = ?"; break;
	                case 10: //나트륭 수정
	                    sql = sql + "NATRIUM = ?"; break;
	                default: 
	                    System.out.println("잘못된 입력값"); break;
	            }
	            sql = sql + "WHERE MN_IDX = ?";
	        } else if(categoryNum == 3){
	        	 switch (detailNum){
			        case 1: //상품명 수정
		                sql = sql + "MN_NAME = ?"; break;
		            case 2: //가격 수정
		                sql = sql + "PRICE = ?"; break;
		            case 3: //상품 소개 수정
		                sql = sql + "DETAIL = ?"; break;
		            default: 
	                    System.out.println("잘못된 입력값"); break;
	        	 }     
	        	 sql = sql + "WHERE MN_IDX = ?";
	        } else {
	        	System.out.println("올바른 번호를 입력하시오");
	        }
	        return sql;
	    }
 
	
	
	
	
	
	
	
	
	
}	
