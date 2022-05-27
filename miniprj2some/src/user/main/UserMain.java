package user.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.db.OracleDB;
import common.util.InputUtil;
import user.menu.CategoryMenu;

public class UserMain {
	public static void userMain() {
		
		System.out.println("\n====== 사용자 프로그램에 접속하셨습니다 ======");
		
		CategoryMenu cm = new CategoryMenu();
		cm.showCategory();
		
	}

}//class
