package Util;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {
	public static void begin() {
		
		Connection con;
		try {
			con = DaoUtil.getConnection();
			con.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("start transaction fail");
		}
		
	}
	public static void commit(){
		try {
		Connection con= DaoUtil.getConnection();
			con.commit();
			DaoUtil.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("commit transaction fail");
		}
	}
	public static void rollback(){
		try {
			Connection con = DaoUtil.getConnection();
			con.rollback();
			DaoUtil.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("rollback transaction fail");
		}
		
	}
}
