package com.cn.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cn.dao.UserDao;
import com.cn.database.DBconn;
import com.cn.entity.UserBean;

public class UserDaoImpl implements UserDao {

	/**
	 */
	public boolean login(String user_name, String user_password) {
		// TODO Auto-generated method stub
		boolean a = false;
		DBconn dbconn = new DBconn();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbconn.getConnection();
			String sql = "select name from users where user_id=? and user_password=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_name);
			pstmt.setString(2, user_password);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				a = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBconn.close(rs);
			DBconn.close(pstmt);
			DBconn.close(conn);
		}
		return a;
	}

	/**
	 * ��̨��½
	 * 
	 * @param user_name
	 * @param user_password
	 * @param shenfen
	 * @return
	 */
	public boolean login(String user_name, String user_password, String shenfen) {
		// TODO Auto-generated method stub
		boolean a = false;
		DBconn dbconn = new DBconn();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbconn.getConnection();
			String sql = "select user_name from users where user_id=? and user_password=? and user_permission=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_name);
			pstmt.setString(2, user_password);
			pstmt.setInt(3, Integer.parseInt(shenfen));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				a = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBconn.close(rs);
			DBconn.close(pstmt);
			DBconn.close(conn);
		}
		return a;
	}

	/**
	 * �������û�
	 * 
	 * @param u
	 * @return
	 */
	public boolean insertUser(UserBean u) {
		boolean a = false;
		DBconn dbconn = new DBconn();
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbconn.getConnection();
			String sql = "insert into users value(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u.getUser_id());
			pstmt.setString(2, u.getPassword());
			pstmt.setString(3, u.getName());
			pstmt.setInt(4, u.getPermission());
			pstmt.setString(5, u.getSex());
			int b = pstmt.executeUpdate();
			if (b > 0) {
				a = true;
			} else {
				a = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			DBconn.close(pstmt);
			DBconn.close(conn);
		}
		return a;
	}

	/**
	 * �õ��û���Ȩ��
	 * 
	 * @param userid
	 * @return
	 */
	public int getUserPemission(String userid) {
		int p = 0;
		DBconn dbconn = new DBconn();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbconn.getConnection();
			String sql = "select user_permission from users where user_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				p = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBconn.close(rs);
			DBconn.close(pstmt);
			DBconn.close(conn);
		}
		return p;

	}

	/**
	 * 
	 * @param userid
	 * @return
	 */
	public boolean isExist(String userid) {
		boolean isExist = false;
		DBconn dbconn = new DBconn();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbconn.getConnection();
			String sql = "select * from users where user_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				isExist = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBconn.close(rs);
			DBconn.close(pstmt);
			DBconn.close(conn);
		}
		return isExist;
	}

	/**
	 * 所有用户
	 * 
	 * @param request
	 * @param strPageSize
	 * @param strPageNo
	 * @return
	 */
	public boolean listUsers(HttpServletRequest request, String strPageSize, String strPageNo) {
		int rowCount = 0;
		DBconn dbconn = new DBconn();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int pageSize = Integer.parseInt(strPageSize);
		int pageNo = Integer.parseInt(strPageNo);
		int start = pageSize * (pageNo - 1);
		try {
			conn = dbconn.getConnection();
			String sql = "select count(*) as countall from users";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rowCount = rs.getInt(1);
			}
			request.setAttribute("rowCount", new Integer(rowCount));
			int pageCount = rowCount % pageSize == 0 ? rowCount / pageSize : rowCount / pageSize + 1;
			request.setAttribute("pageCount", new Integer(pageCount));
			int pageFirstNo = 1;// ��ҳ
			int pageLastNo = pageCount;// βҳ
			int pagePreNo = pageNo > 1 ? pageNo - 1 : 1;// ǰһҳ
			int pageNextNo = pageNo < pageCount ? pageNo + 1 : pageCount;// ��һҳ
			request.setAttribute("pageFirstNo", new Integer(pageFirstNo));
			request.setAttribute("pageLastNo", new Integer(pageLastNo));
			request.setAttribute("pagePreNo", new Integer(pagePreNo));
			request.setAttribute("pageNextNo", new Integer(pageNextNo));
			String sql2 = "select * from users order by user_name limit ?,?";
			List<Hashtable<String, String>> list = new ArrayList<Hashtable<String, String>>();
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, start);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Hashtable<String, String> hash = new Hashtable<String, String>();
				hash.put("userid", rs.getString(1));
				hash.put("password", rs.getString(2));
				hash.put("name", rs.getString(3));
				hash.put("permission", rs.getInt(4) + "");
				hash.put("sex", rs.getString(5) + "");
				list.add(hash);
			}
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

	public boolean listUsersPermisson(HttpServletRequest request, Integer permission, String strPageSize,
			String strPageNo) {
		int userPermission = permission;
		int rowCount = 0;
		DBconn dbconn = new DBconn();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int pageSize = Integer.parseInt(strPageSize);
		int pageNo = Integer.parseInt(strPageNo);
		int start = pageSize * (pageNo - 1);
		try {
			conn = dbconn.getConnection();
			String sql = "select count(*) as countall from users where user_permission=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userPermission);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rowCount = rs.getInt(1);
			}
			request.setAttribute("rowCount", new Integer(rowCount));
			int pageCount = rowCount % pageSize == 0 ? rowCount / pageSize : rowCount / pageSize + 1;
			request.setAttribute("pageCount", new Integer(pageCount));
			int pageFirstNo = 1;// ��ҳ
			int pageLastNo = pageCount;// βҳ
			int pagePreNo = pageNo > 1 ? pageNo - 1 : 1;// ǰһҳ
			int pageNextNo = pageNo < pageCount ? pageNo + 1 : pageCount;// ��һҳ
			request.setAttribute("pageFirstNo", new Integer(pageFirstNo));
			request.setAttribute("pageLastNo", new Integer(pageLastNo));
			request.setAttribute("pagePreNo", new Integer(pagePreNo));
			request.setAttribute("pageNextNo", new Integer(pageNextNo));
			String sql2 = "select * from users where user_permission=? order by user_name limit ?,? ";
			List<Hashtable<String, String>> list = new ArrayList<Hashtable<String, String>>();
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, userPermission);
			pstmt.setInt(2, start);
			pstmt.setInt(3, pageSize);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Hashtable<String, String> hash = new Hashtable<String, String>();
				hash.put("userid", rs.getString(1));
				hash.put("password", rs.getString(2));
				hash.put("name", rs.getString(3));
				hash.put("permission", rs.getInt(4) + "");
				hash.put("sex", rs.getString(5) + "");
				list.add(hash);
			}
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

	/**
	 * 
	 * @param request
	 * @param userid
	 * @return
	 */
	public boolean deleteUser(String userid) {
		boolean a = false;
		DBconn dbconn = new DBconn();
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbconn.getConnection();
			String sql = "delete from users where user_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			int b = pstmt.executeUpdate();
			if (b > 0) {
				a = true;
			} else {
				a = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			DBconn.close(pstmt);
			DBconn.close(conn);
		}
		return a;
	}

	/**
	 * 
	 * @param request
	 * @param userid
	 * @return
	 */
	/*
	 * public UserBean selectUser(HttpServletRequest request, String userid) {
	 * // �������ݿ����� DBconn dbconn = new DBconn(); Connection conn = null;
	 * PreparedStatement pstmt = null; ResultSet rs = null; UserBean ub = new
	 * UserBean(); try { conn = dbconn.getConnection(); String sql =
	 * "select * from users where user_id=?"; pstmt =
	 * conn.prepareStatement(sql); pstmt.setString(1, userid); rs =
	 * pstmt.executeQuery(); while (rs.next()) { ub.setUser_id(rs.getString(1));
	 * ub.setName(rs.getString(3)); ub.setPermission(rs.getInt(4));
	 * ub.setSex(rs.getInt(5)); } } catch (SQLException e) {
	 * e.printStackTrace(); } finally { DBconn.close(rs); DBconn.close(pstmt);
	 * DBconn.close(conn); } return ub;
	 * 
	 * }
	 */

	/**
	 */
	public boolean updateUserPermission(String userid) {
		boolean a = false;
		DBconn dbconn = new DBconn();
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbconn.getConnection();
			String sql = "update users set user_permission=? where user_id=?";
			pstmt = conn.prepareStatement(sql);
			int p = getUserPemission(userid);
			if (p == 2)
				pstmt.setInt(1, 1);
			pstmt.setString(2, userid);
			int b = pstmt.executeUpdate();
			if (b > 0) {
				a = true;
			} else {
				a = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			DBconn.close(pstmt);
			DBconn.close(conn);
		}
		return a;
	}

	@Override
	public UserBean getUser(String userid) {
		// TODO Auto-generated method stub
		UserBean userBean = new UserBean();
		DBconn dBconn = new DBconn();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from users where user_id =?";
		try {
			connection = dBconn.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				userBean.setUser_id(rs.getString(1));
				userBean.setPassword(rs.getString(2));
				userBean.setName(rs.getString(3));
				userBean.setPermission(rs.getInt(4));
				userBean.setSex(rs.getString(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBconn.close(rs);
			DBconn.close(pstmt);
			DBconn.close(connection);
		}
		return userBean;
	}

	@Override
	public boolean updateUser(UserBean userBean, String userid) {
		// TODO Auto-generated method stub
		boolean b = false;
		DBconn dBconn = new DBconn();
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update users set user_password=?,user_name=?,user_sex=?," + " user_permission=? where user_id=? ";
		try {
			conn = dBconn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userBean.getPassword());
			pstmt.setString(2, userBean.getName());
			pstmt.setString(3, userBean.getSex());
			pstmt.setInt(4, userBean.getPermission());
			pstmt.setString(5, userid);
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
	public boolean isExistUerName(String username) {
		// TODO Auto-generated method stub
		boolean isExist = false;
		DBconn dbconn = new DBconn();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbconn.getConnection();
			String sql = "select * from users where user_name=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				isExist = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBconn.close(rs);
			DBconn.close(pstmt);
			DBconn.close(conn);
		}
		return isExist;
	}

}
