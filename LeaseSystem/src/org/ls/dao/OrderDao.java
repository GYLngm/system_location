package org.ls.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.ls.entity.Exemplaire;
import org.ls.tools.JDBCUtils;

/**
 * 订单信息
 */                               //   modifier les infos de la commande
public class OrderDao {

	private static OrderDao dao = new OrderDao();

	private OrderDao() {

	}

	public static OrderDao getInstance() {
		return dao;
	}

	private Connection conn;
	private PreparedStatement prep;
	private ResultSet rs;

	/**
	 * 下订单
	 * 
	 * @param domain
	 * @return
	 */
	public boolean louer(Exemplaire domain) {

		try {
			conn = JDBCUtils.connDb();
			String sql = "insert into t_orders VALUES(null,?,?,?,?,?,?,?,?,?)";
			prep = conn.prepareStatement(sql);
			prep.setString(1, domain.getBianHao());
			prep.setString(2, domain.getXingHao());
			prep.setInt(3, domain.getKm());
			prep.setFloat(4, domain.getPrice());
			prep.setString(5, domain.getStartTime());
			prep.setString(6, domain.getEndTime());
			prep.setString(7, domain.getRenterName());
			prep.setInt(8, domain.getHourseNunber());
			prep.setInt(9, domain.getType());
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

	public List<Exemplaire> getAll() {
		List<Exemplaire> list = new ArrayList<Exemplaire>();
		try {
			conn = JDBCUtils.connDb();
			String sql = "select * from t_orders  order by id desc";
			prep = conn.prepareStatement(sql);
			rs = prep.executeQuery();
			while (rs.next()) {
				list.add(new Exemplaire(rs.getInt("id"), rs
						.getString("bianHao"), rs.getString("xingHao"), rs
						.getInt("km"), rs.getFloat("price"), rs
						.getString("startTime"), rs.getString("endTime"), rs
						.getString("renterName"), rs.getInt("hourseNunber"), rs
						.getInt("type")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeDb(conn, prep, rs);
		}

		return list;

	}

	/**
	 * 查询某租客的全部订单
	 * 
	 * @param id
	 * @return
	 */
	public List<Exemplaire> getAllByRenterId(String id) {
		List<Exemplaire> list = new ArrayList<Exemplaire>();
		try {
			conn = JDBCUtils.connDb();
			String sql = "select * from t_orders where id = ?  order by id desc";
			prep = conn.prepareStatement(sql);
			prep.setString(1, id);
			rs = prep.executeQuery();
			while (rs.next()) {
				list.add(new Exemplaire(rs.getInt("id"), rs
						.getString("bianHao"), rs.getString("xingHao"), rs
						.getInt("km"), rs.getFloat("price"), rs
						.getString("startTime"), rs.getString("endTime"), rs
						.getString("renterName"), rs.getInt("hourseNunber"), rs
						.getInt("type")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeDb(conn, prep, rs);
		}

		return list;

	}

}
