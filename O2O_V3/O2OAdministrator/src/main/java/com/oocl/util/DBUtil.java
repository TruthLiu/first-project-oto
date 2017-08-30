package com.oocl.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * 
 * @author MAIKE
 *
 */
public class DBUtil {
	private static String url;
	private static String driverName;
	private static String username;
	private static String password;
	private static int maxActive;
	private static BasicDataSource ds;
	private static ThreadLocal<Connection> tl = new ThreadLocal<>();
	
	static {
		initParameters();
		ds = new BasicDataSource();
		ds.setUrl(url);
		ds.setDriverClassName(driverName);
		ds.setUsername(username);
		ds.setPassword(password);
		ds.setMaxActive(maxActive);
	}
	
	/**
	 * 
	 * @return
	 */
	public static Connection getConnection(){
		Connection connection = tl.get();
		if(null==connection){
			try {
				connection = ds.getConnection();
				tl.set(connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}
	
	public static void remove(Connection connection){
		tl.remove();
		release(null, null, connection);
	}
	
	/**
	 * 
	 * @param rs
	 * @param ps
	 * @param conn
	 */
	public static void release(ResultSet rs,PreparedStatement ps,Connection conn){
		try {
			if(rs!=null){
				rs.close();
			}
			if(ps!=null){
				ps.close();
			}
			if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void initParameters() {
		Properties pro=null;
		try {
			pro = new Properties();
			pro.load(DBUtil.class.getClassLoader().getResourceAsStream("db.properties"));
			url = pro.getProperty("jdbc.url");
			driverName = pro.getProperty("jdbc.driver");
			username = pro.getProperty("jdbc.username");
			password = pro.getProperty("jdbc.password");
			maxActive = Integer.parseInt(pro.getProperty("jdbc.maxActive"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
