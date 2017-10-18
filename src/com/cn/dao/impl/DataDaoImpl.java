package com.cn.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.cn.dao.DataDao;
import com.cn.database.DBconn;
import com.cn.entity.DataBean;

//数据表操作的借口方法实现
public class DataDaoImpl implements DataDao {

	@Override
	public List<DataBean> selectData() {
		// TODO Auto-generated method stub
		List<DataBean> list = new ArrayList<DataBean>();
		DBconn dBconn = new DBconn();
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			String sql = "select data_id,data_name,data_mark from data_info";
			conn = dBconn.getConnection();
			preparedStatement = conn.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				DataBean dataBean = new DataBean();
				dataBean.setData_id(rs.getInt(1));
				dataBean.setData_name(rs.getString(2));
				dataBean.setData_mark(rs.getString(3));
				list.add(dataBean);
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBconn.close(rs);
			DBconn.close(preparedStatement);
			DBconn.close(conn);
		}
		return list;
	}

	/**
	 * 分页显示数据信息
	 */
	@Override
	public boolean listData(HttpServletRequest request, String strPageSize, String strPageNo) {
		// TODO Auto-generated method stub
		// 创建数据库连接
		int rowCount = 0;
		DBconn dbconn = new DBconn();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 查询总的记录数，计算跳页参数
		int pageSize = Integer.parseInt(strPageSize);
		int pageNo = Integer.parseInt(strPageNo);
		int start = pageSize * (pageNo - 1);
		try {
			conn = dbconn.getConnection();
			String sql = "select count(*) as countall from data_info";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rowCount = rs.getInt(1);
			}
			request.setAttribute("rowCount", new Integer(rowCount));
			// 计算总页数并保存
			int pageCount = rowCount % pageSize == 0 ? rowCount / pageSize : rowCount / pageSize + 1;
			request.setAttribute("pageCount", new Integer(pageCount));
			// 计算跳页参数并保存
			int pageFirstNo = 1;// 首页
			int pageLastNo = pageCount;// 尾页
			int pagePreNo = pageNo > 1 ? pageNo - 1 : 1;// 前一页
			int pageNextNo = pageNo < pageCount ? pageNo + 1 : pageCount;// 后一页
			request.setAttribute("pageFirstNo", new Integer(pageFirstNo));
			request.setAttribute("pageLastNo", new Integer(pageLastNo));
			request.setAttribute("pagePreNo", new Integer(pagePreNo));
			request.setAttribute("pageNextNo", new Integer(pageNextNo));
			// 取得当前页数据SQL
			String sql2 = "select data_id,data_name,data_mark from data_info order by data_id limit ?,?";
			List<Hashtable<String, String>> list = new ArrayList<Hashtable<String, String>>();
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, start);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// 查询每行数据的各个字段数据
				Hashtable<String, String> hash = new Hashtable<String, String>();
				hash.put("dataid", rs.getInt(1) + "");
				hash.put("dataname", rs.getString(2));
				hash.put("datamark", rs.getString(3));
				list.add(hash);
			}
			// 保存所有行数据列表传递给下一个页面
			request.setAttribute("list", list);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBconn.close(rs);
			DBconn.close(pstmt);
			DBconn.close(conn);
		}
		return true;
	}

	@Override
	public DataBean getDataBean(int dataid) {
		// TODO Auto-generated method stub
		DBconn dBconn = new DBconn();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		DataBean dataBean = new DataBean();
		try {
			connection = dBconn.getConnection();
			String sql = "select * from data_info where data_id=?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, dataid);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				dataBean.setData_id(resultSet.getInt(1));
				dataBean.setData_name(resultSet.getString(2));
				dataBean.setData_mark(resultSet.getString(3));
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBconn.close(resultSet);
			DBconn.close(pstmt);
			DBconn.close(connection);
		}
		return dataBean;
	}

	@Override
	public boolean updateData(DataBean mb) {
		// TODO Auto-generated method stub
		boolean b = false;
		DBconn dBconn = new DBconn();
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = dBconn.getConnection();
			String sql = "update  data_info set data_name=?,data_mark=? where data_id=?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, mb.getData_name());
			pstmt.setString(2, mb.getData_mark());
			int a = pstmt.executeUpdate();
			if (a > 0) {
				b = true;
			} else {
				b = false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBconn.close(pstmt);
			DBconn.close(connection);
		}
		return b;
	}

	@Override
	public boolean insertData(DataBean mb) {
		// TODO Auto-generated method stub
		boolean b = false;
		DBconn dBconn = new DBconn();
		Connection connection = dBconn.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into data_info(data_id, data_name,data_mark)values(?,?,?) ";
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, mb.getData_id());
			pstmt.setString(2, mb.getData_name());
			pstmt.setString(3, mb.getData_mark());
			int a = pstmt.executeUpdate();
			if (a > 0) {
				b = true;
			} else {
				b = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBconn.close(pstmt);
			DBconn.close(connection);
		}
		return b;
	}

	/**
	 * 根据类型查找，该方法暂不适用。 typeid数据库中未定义
	 */
	@Override
	public Map<String, String> getDatasByTypeid(int typeid) {
		// TODO Auto-generated method stub
		DBconn db = new DBconn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		Map map = new HashMap();
		try {
			conn = db.getConnection();
			String sql = "select data_id, data_name, data_mark from data_info where type_id=? order by data_id";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, typeid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				map.put(rs.getInt(1) + "", rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBconn.close(rs);
			DBconn.close(pstmt);
			DBconn.close(conn);
		}
		return map;
	}

	@Override
	public boolean deleteData(int dataid) {
		// TODO Auto-generated method stub
		boolean b = false;
		DBconn dBconn = new DBconn();
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete  from data_info where data_id=?";
		try {
			conn = dBconn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dataid);
			int a = pstmt.executeUpdate();
			if (a > 0) {
				b = true;
			} else {
				b = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBconn.close(pstmt);
			DBconn.close(conn);
		}
		return b;
	}

	@Override
	public boolean addData(DataBean dataBean) {
		// TODO Auto-generated method stub
		boolean b = false;
		DBconn dBconn = new DBconn();
		Connection connection = null;
		PreparedStatement pstmt = null;
		String sql = "insert into data_info values(?,?,?) ";
		try {
			connection = dBconn.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, dataBean.getData_id());
			pstmt.setString(2, dataBean.getData_name());
			pstmt.setString(3, dataBean.getData_mark());
			int a = pstmt.executeUpdate();
			if (a > 0) {
				b = true;
			} else {
				b = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBconn.close(pstmt);
			DBconn.close(connection);
		}
		return b;
	}

	@Override
	public boolean updateDatabyDataid(DataBean mb, int dataid) {
		// TODO Auto-generated method stub
		boolean b = false;
		DBconn dBconn = new DBconn();
		Connection connection = null;
		PreparedStatement pstmt = null;
		String sql = "update data_info set data_name=?,data_mark=? where data_id=?";
		try {
			connection = dBconn.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, mb.getData_name());
			pstmt.setString(2, mb.getData_mark());
			pstmt.setInt(3, dataid);
			int a = pstmt.executeUpdate();
			if (a > 0) {
				b = true;
			} else {
				b = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBconn.close(pstmt);
			DBconn.close(connection);
		}
		return b;
	}

	// 查询 ，可变参数
	@Override
	public boolean queryData(String... args) {
		// TODO Auto-generated method stub
		int dataid = Integer.valueOf(args[0].trim());
		String seekid = args[1];
		String dataname = args[2].trim();
		String seekname = args[3];
		return false;
	}

	@Override
	public boolean listDataQuery(HttpServletRequest request, String strPageSize, String strPageNo, String... args) {
		// TODO Auto-generated method stub
		int rowCount = 0;
		boolean flag_seekid = false; // used for flag exact search or fuzzy search
		boolean flag_seekname = false; // used for flag exact search or fuzzy search
		String sql = null;// calculate the total quantity according to the condition
		String sql2 = null;// search data based on conditions
		DBconn dbconn = new DBconn();
		Connection conn = null;
		Connection conn2 = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		// 查询总的记录数，计算跳页参数
		// int dataid;
		// System.out.println("args0==="+args[0]);
		/*
		 * if (!args[0].trim().isEmpty()) { dataid =
		 * Integer.valueOf(args[0].trim()); } else { dataid = 0; }
		 */
		String dataid = args[0].trim();
		String seekid = args[1]; // 参数2为模糊查找， 参数1为精确查找
		String datanme = args[2].trim();
		String seekname = args[3]; // 参数2为模糊查找， 参数1为精确查找
		if (seekid.equals("2")) {
			flag_seekid = false;
			System.out.println("seekid is false");
		} else {
			System.out.println("seekid is true");
			flag_seekid = true;
		}
		if (seekname.equals("2")) {
			flag_seekname = false;
			System.out.println("seekname is false");
		} else {
			System.out.println("seekname is true");
			flag_seekname = true;
		}
		System.out.println(dataid + " " + seekid + " " + datanme + "  " + seekname);
		int pageSize = Integer.parseInt(strPageSize);
		int pageNo = Integer.parseInt(strPageNo);
		int start = pageSize * (pageNo - 1);
		try {
			conn = dbconn.getConnection();
			conn2 = dbconn.getConnection();
			if (flag_seekname && flag_seekid) {
				sql = "select count(*) as countall from data_info where data_id=? and data_name=?";
				sql2 = "select data_id,data_name,data_mark from data_info where data_id=? and data_name=?  order by data_id limit ?,?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dataid);
				pstmt.setString(2, datanme);
				pstmt2 = conn2.prepareStatement(sql2);
				pstmt2.setString(1, dataid);
				pstmt2.setString(2, datanme);
			} else if (!flag_seekname && flag_seekid) {
				sql = "select count(*) as countall from data_info where data_id=? and data_name like ?";
				sql2 = "select data_id,data_name,data_mark from data_info where data_id=? and data_name like ?  order by data_id limit ?,?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dataid);
				pstmt.setString(2, "%" + datanme + "%");
				pstmt2 = conn2.prepareStatement(sql2);
				pstmt2.setString(1, dataid);
				pstmt2.setString(2, "%" + datanme + "%");
			} else if (flag_seekname && !flag_seekid) {
				sql = "select count(*) as countall from data_info where data_id like ? and data_name=?";
				sql2 = "select data_id,data_name,data_mark from data_info where data_id like ? and data_name=?  order by data_id limit ?,?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + dataid + "%");
				pstmt.setString(2, datanme);
				pstmt2 = conn2.prepareStatement(sql2);
				pstmt2.setString(1, "%" + dataid + "%");
				pstmt2.setString(2, datanme);
			} else if (!flag_seekid && !flag_seekname) {
				sql = "select count(*) as countall from data_info where data_id like ? and data_name like ?";
				sql2 = "select data_id,data_name,data_mark from data_info where data_id like ? and data_name like ?  order by data_id limit ?,?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + dataid + "%");
				pstmt.setString(2, "%" + datanme + "%");
				pstmt2 = conn2.prepareStatement(sql2); 
				pstmt2.setString(1, "%" + dataid + "%");
				pstmt2.setString(2, "%" + datanme + "%");
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				rowCount = rs.getInt(1);
				System.out.println("rowcount===="+rowCount);
			}
			request.setAttribute("rowCount", new Integer(rowCount));
			// 计算总页数并保存
			int pageCount = rowCount % pageSize == 0 ? rowCount / pageSize : rowCount / pageSize + 1;
			request.setAttribute("pageCount", new Integer(pageCount));
			// 计算跳页参数并保存
			int pageFirstNo = 1;// 首页
			int pageLastNo = pageCount;// 尾页
			int pagePreNo = pageNo > 1 ? pageNo - 1 : 1;// 前一页
			int pageNextNo = pageNo < pageCount ? pageNo + 1 : pageCount;// 后一页
			request.setAttribute("pageFirstNo", new Integer(pageFirstNo));
			request.setAttribute("pageLastNo", new Integer(pageLastNo));
			request.setAttribute("pagePreNo", new Integer(pagePreNo));
			request.setAttribute("pageNextNo", new Integer(pageNextNo));
			// 取得当前页数据SQL
			//sql2 = "select data_id,data_name,data_mark from data_info order by data_id limit ?,?";
			List<Hashtable<String, String>> list = new ArrayList<Hashtable<String, String>>();
			//pstmt = conn.prepareStatement(sql2);
			pstmt2.setInt(3, start);
			pstmt2.setInt(4, pageSize);
			rs2 = pstmt2.executeQuery();
			while (rs2.next()) {
				// 查询每行数据的各个字段数据
				Hashtable<String, String> hash = new Hashtable<String, String>();
				hash.put("dataid", rs2.getInt(1) + "");
				hash.put("dataname", rs2.getString(2));
				hash.put("datamark", rs2.getString(3));
				list.add(hash);
			}
			// 保存所有行数据列表传递给下一个页面
			request.setAttribute("list", list);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBconn.close(rs);
			DBconn.close(rs2);
			DBconn.close(pstmt);
			DBconn.close(pstmt2);
			DBconn.close(conn);
			DBconn.close(conn2);
		}
		return true;
	}

}
