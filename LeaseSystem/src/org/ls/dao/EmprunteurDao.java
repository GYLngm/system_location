package org.ls.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.ls.entity.Emprunteur;
import org.ls.tools.JDBCUtils;

/**
 * 操作租客信息           modifier les infos d'emprunteur
 */
public class EmprunteurDao {

	private static EmprunteurDao dao = new EmprunteurDao();

	private EmprunteurDao() {

	}

	public static EmprunteurDao getInstance() {
		return dao;
	}

	private Connection conn;
	private PreparedStatement prep;
	private ResultSet rs;

	/**
	 * 租客登录
	 * 
	 * @param domain
	 * @return
	 */
	public Emprunteur login(Emprunteur domain) {
		try {
			conn = JDBCUtils.connDb();
			String sql = "select * from t_renters where id = ?  and password = ?";
			prep = conn.prepareStatement(sql);
			prep.setString(1, domain.getId());
			prep.setString(2, domain.getPassword());
			rs = prep.executeQuery();
			if (rs.next()) {
				return new Emprunteur(rs.getString("id"), rs.getString("password"),
						rs.getString("familyName"), rs.getString("lastName"),
						rs.getString("houseNum"), rs.getString("street"), rs
								.getString("postcode"), rs.getString("city"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeDb(conn, prep, rs);
		}
		return null;
	}

	/**
	 * 修改租客信息
	 * 
	 * @param domain
	 * @return
	 */
	public boolean update(Emprunteur domain, String oldId) {

		try {
			conn = JDBCUtils.connDb();
			String sql = "update t_renters set id=?, password = ?  , familyName = ?, lastName=?,houseNum=?,street=?,postcode=?,city=?   where id = ? ";
			prep = conn.prepareStatement(sql);
			prep.setString(1, domain.getId());
			prep.setString(2, domain.getPassword());
			prep.setString(3, domain.getFamilyName());
			prep.setString(4, domain.getLastName());
			prep.setString(5, domain.getAddress().getHouseNum());
			prep.setString(6, domain.getAddress().getStreet());
			prep.setString(7, domain.getAddress().getPostcode());
			prep.setString(8, domain.getAddress().getCity());
			prep.setString(9, oldId);
			int executeUpdate = prep.executeUpdate();
			if (executeUpdate == 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeDb(conn, prep, rs);
		}
		return false;
	}

	/**
	 * 添加租客
	 */
	public boolean add(Emprunteur domain) {

		try {
			conn = JDBCUtils.connDb();
			String sql = "insert into t_renters VALUES(?,?,?,?,?,?,?,?)";
			prep = conn.prepareStatement(sql);
			prep.setString(1, domain.getId());
			prep.setString(2, domain.getPassword());
			prep.setString(3, domain.getFamilyName());
			prep.setString(4, domain.getLastName());
			prep.setString(5, domain.getAddress().getHouseNum());
			prep.setString(6, domain.getAddress().getStreet());
			prep.setString(7, domain.getAddress().getPostcode());
			prep.setString(8, domain.getAddress().getCity());
			int count = prep.executeUpdate();
			if (count == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeDb(conn, prep, rs);
		}
		return false;

	}

	/**
	 * 根据id删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id) {
		try {
			conn = JDBCUtils.connDb();
			String sql = "delete from t_renters where id = ? ";
			prep = conn.prepareStatement(sql);
			prep.setString(1, id);
			int executeUpdate = prep.executeUpdate();
			if (executeUpdate == 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeDb(conn, prep, rs);
		}
		return false;
	}

	/**
	 * 查询所有租客的信息
	 * 
	 * @return
	 */
	public List<Emprunteur> getAll() {
		List<Emprunteur> list = new ArrayList<Emprunteur>();
		try {
			conn = JDBCUtils.connDb();
			String sql = "select * from t_renters ";
			prep = conn.prepareStatement(sql);
			rs = prep.executeQuery();
			while (rs.next()) {
				list.add(new Emprunteur(rs.getString("id"), rs
						.getString("password"), rs.getString("familyName"), rs
						.getString("lastName"), rs.getString("houseNum"), rs
						.getString("street"), rs.getString("postcode"), rs
						.getString("city")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeDb(conn, prep, rs);
		}

		return list;

	}

	public static void main(String[] args) {

	}
}
