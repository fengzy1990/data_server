package com.cn.dao;

import javax.servlet.http.HttpServletRequest;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import com.cn.entity.UserBean;
//定于的关于USER的数据测相关借口操作方法
public interface UserDao {
	public boolean login(String username, String password);

	public boolean insertUser(UserBean u);

	public int getUserPemission(String userid);

	public UserBean getUser(String userid);

	public boolean login(String username, String password, String shenfen);

	public boolean isExist(String userid);
	
	public boolean isExistUerName(String username);

	public boolean listUsers(HttpServletRequest request, String strPageSize, String strPageNo);

	public boolean listUsersPermisson(HttpServletRequest request, Integer permission, String strPageSize,
			String strPageNo);

	public boolean deleteUser(String userid);

	// public UserBean selectUser(HttpServletRequest request, String userid);

	public boolean updateUserPermission(String userid);

	public boolean updateUser(UserBean userBean, String userid);
}
