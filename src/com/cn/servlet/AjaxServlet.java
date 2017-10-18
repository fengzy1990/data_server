package com.cn.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.dao.UserDao;
import com.cn.dao.impl.UserDaoImpl;

/**
 * Servlet implementation class Ajax
 */
@WebServlet("/ajax.do")
public class AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjaxServlet() {
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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserDao ud = new UserDaoImpl();
		String username = request.getParameter("username");
		String result;
		if (ud.isExistUerName(username)) {
			result = "no";
		} else {
			result = "ok";
		}
		String content = "<content>" + result + "</content>";
		response.setContentType("text/Xml"); 
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(content);
	}

}
