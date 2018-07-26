package org.ls.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.ls.entity.Auto;
import org.ls.tools.JDBCUtils;

/**
 * 汽车信息
 */                               //insert les infos des vehucile
public class AutoDao {

	private static AutoDao dao = new AutoDao();

	private AutoDao() {

	}

	public static AutoDao getInstance() {
		return dao;
	}

	private Connection conn;
	private PreparedStatement prep;
	private ResultSet rs;

	public boolean update(Auto domain) {

		try {
			conn = JDBCUtils.connDb();
			String sql = "update t_autos set bianHao=?, km = ?  , price = ?, storeNum=?,xingHao=?   where id = ? ";
			prep = conn.prepareStatement(sql);
			prep.setString(1, domain.getBianHao());
			prep.setInt(2, domain.getKm());
			prep.setFloat(3, domain.getPrice());
			prep.setInt(4, domain.getStoreNum());
			prep.setString(5, domain.getXingHao());
			prep.setInt(6, domain.getId());
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

	public boolean add(Auto domain) {

		try {
			conn = JDBCUtils.connDb();
			String sql = "insert into t_autos VALUES(null,?,?,?,?,?)";
			prep = conn.prepareStatement(sql);
			prep.setString(1, domain.getBianHao());
			prep.setInt(2, domain.getKm());
			prep.setFloat(3, domain.getPrice());
			prep.setInt(4, domain.getStoreNum());
			prep.setString(5, domain.getXingHao());
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
	public boolean deleteById(int id) {
		try {
			conn = JDBCUtils.connDb();
			String sql = "delete from t_autos where id = ? ";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, id);
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

	public List<Auto> getAll() {
		List<Auto> list = new ArrayList<Auto>();
		try {
			conn = JDBCUtils.connDb();
			String sql = "select * from t_autos order by id desc";
			prep = conn.prepareStatement(sql);
			rs = prep.executeQuery();
			while (rs.next()) {
				list.add(new Auto(rs.getInt("id"), rs.getString("bianHao"), rs
						.getInt("km"), rs.getFloat("price"), rs
						.getInt("storeNum"), rs.getString("xingHao")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeDb(conn, prep, rs);
		}

		return list;

	}

}
