package com.cn.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.cn.dao.UserDao;
import com.cn.dao.impl.UserDaoImpl;
import com.cn.entity.UserBean;

/**
 * Servlet implementation class UsersServlet
 */
@WebServlet("/users.do")
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsersServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String method = request.getParameter("method");
		String topage = "/users.jsp";
		// 未登录时跳转到登录页面
		if (session.getAttribute("username") == null) {
			topage = "/index.jsp";
		} else {
			// 取得分页参数
			String pageSize = request.getParameter("pageSize");// 每页显示行数
			String pageNo = request.getParameter("pageNo");// 当前显示页次
			if (pageSize == null) {// 为空时设置默认页大小为25
				pageSize = "25";
			}
			if (pageNo == null) {// 为空时设置默认为第1页
				pageNo = "1";
			}
			// 保存分页参数，传递给下一个页面
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageNo", pageNo);

			// 根据method参数执行各种操作
			UserDao ud = new UserDaoImpl();
			if (method.equals("list")) {// 列表操作
				// 查询数据
				ud.listUsers(request, pageSize, pageNo);
				topage = "/users.jsp";// 跳到列表页
			} else if (method.equals("delete")) {// 删除操作
				// 执行删除
				String userid = request.getParameter("userid");
				ud.deleteUser(userid);
				// 查询数据
				ud.listUsers(request, pageSize, pageNo);
				topage = "/users.jsp";// 跳到列表页
			} else if (method.equals("edit")) {// 修改操作
				// 执行查询
				String userid = request.getParameter("userid");
				UserBean userBean = ud.getUser(userid);
				request.setAttribute("userBean", userBean);
				topage = "/users_edit.jsp";
			} else if (method.equals("update")) {
				// request.setCharacterEncoding("UTF-8");
				UserBean uBean = new UserBean();
				String userid = request.getParameter("userid");
				String password = request.getParameter("password");
				String name = new String(request.getParameter("name").getBytes("iso-8859-1"), "utf-8");
				String sex = new String(request.getParameter("sex").getBytes("iso-8859-1"), "utf-8");
				int permission = Integer.valueOf(request.getParameter("permission"));
				uBean.setName(name);
				uBean.setPassword(password);
				uBean.setSex(sex);
				uBean.setPermission(permission);
				if (ud.updateUser(uBean, userid)) {
				//PrintWriter pw=response.getWriter();
				//pw.print("<script language='javascript'>alert('the name doesnot exit')</script>");
				//	JOptionPane.showMessageDialog(null, "name doesnot exits");
				};
				ud.listUsers(request, pageSize, pageNo);
				topage = "/users.jsp";

			} else if (method.equals("add")) {// 新增操作
				topage = "/users_add.jsp";// 跳到新增页
			} else if (method.equals("verify")) {
				String userid = request.getParameter("userid");
				ud.updateUserPermission(userid);
				ud.listUsersPermisson(request, 2, pageSize, pageNo);
				topage = "/users_verify.jsp";
			}else if (method.equals("noVerify")) {
				ud.listUsersPermisson(request, 2, pageSize, pageNo);
				topage = "/users_verify.jsp";
			}else if (method.equals("adduser")) {
				UserBean uBean = new UserBean();
				String userid = request.getParameter("username");
				String password = request.getParameter("password");
				String name = new String(request.getParameter("name").getBytes("iso-8859-1"), "utf-8");
				String sex = new String(request.getParameter("sex").getBytes("iso-8859-1"), "utf-8");
				int permission = 1;
				uBean.setName(name);
				uBean.setPassword(password);
				uBean.setSex(sex);
				uBean.setPermission(permission);
				if (ud.updateUser(uBean, userid)) {
				};
				ud.listUsers(request, pageSize, pageNo);
				topage = "/index.jsp";
			}
		}

		// 转发
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher(topage);
		rd.forward(request, response);
	}

}
