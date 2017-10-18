package com.cn.entity;
//用户模型
public class UserBean {
	private String user_id;
	private String password;
	private String name;
	private int permission;
	private String sex;

	public UserBean() {
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPermission() {
		return permission;
	}

	public void setPermission(int permission) {
		this.permission = permission;
	}

	public String  getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	};

}
