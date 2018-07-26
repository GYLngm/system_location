package org.ls.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.ls.entity.Admin;
import org.ls.tools.JDBCUtils;

/**
 * 操作管理员信息                         
 */
public class AdminDao {                                       //ajouter les infos des admistrateur

	private static AdminDao dao = new AdminDao();

	private AdminDao() {

	}

	public static AdminDao getInstance() {
		return dao;
	}

	private Connection conn;
	private PreparedStatement prep;
	private ResultSet rs;

	/**
	 * 管理员登录
	 * 
	 * @param domain
	 * @return
	 */
	public Admin login(Admin domain) {
		try {
			conn = JDBCUtils.connDb();
			String sql = "select * from t_admins where id = ?  and password = ?";
			prep = conn.prepareStatement(sql);
			prep.setString(1, domain.getId());
			prep.setString(2, domain.getPassword());
			rs = prep.executeQuery();
			if (rs.next()) {
				return new Admin(rs.getString("id"), rs.getString("password"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeDb(conn, prep, rs);
		}
		return null;
	}

}
