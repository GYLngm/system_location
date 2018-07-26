package org.ls.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据库工具类(获取数据库连接，关闭数据库连接)
 */
public class JDBCUtils {

	/**
	 * 获取数据库连接
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static Connection connDb() {
		try {
			return getConnection("127.0.0.1:3306", "my_db", "root", "");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 释放数据库连接
	 * 
	 * @param conn
	 * @param stat
	 * @param rs
	 */
	public static void closeDb(Connection conn, Statement stat, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (stat != null) {
				stat.close();
				stat = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 通过jdbc获取相应的数据库链接connection
	 * 
	 * @param ipport
	 *            数据库服务器的ip
	 * @param dbName
	 *            数据库名称
	 * @param username
	 *            数据库连接用户名
	 * @param password
	 *            数据库连接密码
	 * @param type
	 *            数据库类型
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getConnection(String ipport, String dbName,
			String username, String password) throws ClassNotFoundException,
			SQLException {
		String jdbcString = null;
		/**
		 * 加载数据库驱动类
		 */
		jdbcString = "jdbc:mysql://" + ipport + "/" + dbName;
		Class.forName("org.gjt.mm.mysql.Driver");
		Connection connection = null;
		connection = DriverManager
				.getConnection(jdbcString, username, password);
		return connection;
	}

}