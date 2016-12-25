package Util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DaoUtil {
	private static ThreadLocal<Connection> connectionHolders = new ThreadLocal<Connection>();

	private static Properties props = new Properties();
	static {
		try {
			InputStream pis = DaoUtil.class.getClassLoader()
					.getResourceAsStream("Util/db.properties");
			props.load(pis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Connection getCon() throws SQLException {
		Connection con = null;
		try {
			Class.forName(props.getProperty("driverName"));

			con = DriverManager.getConnection(props.getProperty("url"), props
					.getProperty("userName"), props.getProperty("password"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return con;
	}

	public static void close(ResultSet rst, Statement stat, Connection con) {
		try {
			if (rst != null) {
				rst.close();
			}
			if (stat != null) {
				stat.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public synchronized static Connection getConnection() throws SQLException {
		Connection con = connectionHolders.get();// map.get(thread.getcurrentThread),第一次执行时con为null,即取不到值
		//以执行当前代码的线程为KEY，得到一个value,
		if (con == null) {//当第一次取不到时给它赋值
			con = getCon();//赋值
			connectionHolders.set(con);//以当前线程为key把connection加到map集合当中去，以备第二此取走，第二次取走的还是同一个connection
		}
		return con;
	}

	public static void main(String[] args) {
		try {
			System.out.println(getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public synchronized static void close() throws SQLException{
		Connection con = connectionHolders.get();
		if(con!=null){
			con.close();
			connectionHolders.set(null);
		}
	}
}
