package org.ls.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.ls.entity.Moto;
import org.ls.tools.JDBCUtils;

/**
 * 摩托车信息           
 */
public class MotoDao {              //   modifier les infos de moto

	private static MotoDao dao = new MotoDao();

	private MotoDao() {

	}

	public static MotoDao getInstance() {
		return dao;
	}

	private Connection conn;
	private PreparedStatement prep;
	private ResultSet rs;

	public boolean update(Moto domain) {

		try {
			conn = JDBCUtils.connDb();
			String sql = "update t_motos set bianHao=?, km = ?  , price = ?, storeNum=?,horsepower=?   where id = ? ";
			prep = conn.prepareStatement(sql);
			prep.setString(1, domain.getBianHao());
			prep.setInt(2, domain.getKm());
			prep.setFloat(3, domain.getPrice());
			prep.setInt(4, domain.getStoreNum());
			prep.setInt(5, domain.getHorsepower());
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

	public boolean add(Moto domain) {

		try {
			conn = JDBCUtils.connDb();
			String sql = "insert into t_motos VALUES(null,?,?,?,?,?)";
			prep = conn.prepareStatement(sql);
			prep.setString(1, domain.getBianHao());
			prep.setInt(2, domain.getKm());
			prep.setFloat(3, domain.getPrice());
			prep.setInt(4, domain.getStoreNum());
			prep.setInt(5, domain.getHorsepower());
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
			String sql = "delete from t_motos where id = ? ";
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

	public List<Moto> getAll() {
		List<Moto> list = new ArrayList<Moto>();
		try {
			conn = JDBCUtils.connDb();
			String sql = "select * from t_motos order by id desc";
			prep = conn.prepareStatement(sql);
			rs = prep.executeQuery();
			while (rs.next()) {
				list.add(new Moto(rs.getInt("id"), rs.getString("bianHao"), rs
						.getInt("km"), rs.getFloat("price"), rs
						.getInt("storeNum"), rs.getInt("horsepower")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeDb(conn, prep, rs);
		}

		return list;

	}

}
