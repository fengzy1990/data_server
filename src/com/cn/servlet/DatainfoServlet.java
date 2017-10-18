package com.cn.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cn.dao.DataDao;
import com.cn.dao.impl.DataDaoImpl;
import com.cn.entity.DataBean;

/**
 * Servlet implementation class DatainfoServlet
 */
@WebServlet("/datainfo.do")
public class DatainfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DatainfoServlet() {
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
		String topage = "/datainfo.jsp";
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
			DataDao dd = new DataDaoImpl();
			if (method.equals("list")) {// 列表操作
				// 查询数据
				dd.listData(request, pageSize, pageNo);
				topage = "/datainfo.jsp";// 跳到列表页
			} else if (method.equals("delete")) {// 删除操作
				// 执行删除
				String dataid = request.getParameter("dataid");
				dd.deleteData(Integer.valueOf(dataid));
				dd.listData(request, pageSize, pageNo);
				topage = "/datainfo.jsp";
			} else if (method.equals("updatepage")) {
				String dataid = request.getParameter("dataid");
				DataBean dataBean = dd.getDataBean(Integer.valueOf(dataid));
				request.setAttribute("dataBean", dataBean);
				topage = "/datainfo_update.jsp";

			} else if (method.equals("update")) {
				// 修改操作
				DataBean dataBean = new DataBean();
				String dataid = request.getParameter("dataid");
				String dataname = request.getParameter("dataname");
				String datamark = request.getParameter("datamark");
				dataBean.setData_id(Integer.valueOf(dataid));
				dataBean.setData_name(dataname);
				dataBean.setData_mark(datamark);
				dd.deleteData(Integer.valueOf(dataid));
				dd.addData(dataBean);
				dd.listData(request, pageSize, pageNo);
				topage = "/datainfo.jsp";
			} else if (method.equals("addpage")) {// 新增操作
				topage = "/datainfo_add.jsp";// 跳到新增页
			} else if (method.equals("add")) {// 新增操作
				DataBean dBean = new DataBean();
				String dataid = request.getParameter("dataid");
				String dataname = new String(request.getParameter("dataname").getBytes("iso-8859-1"), "utf-8");
				String datamark = new String(request.getParameter("datamark").getBytes("iso-8859-1"), "utf-8");
				dBean.setData_id(Integer.valueOf(dataid));
				System.out.println(dataid + dataname + datamark);
				dBean.setData_name(dataname);
				dBean.setData_mark(datamark);
				boolean b = dd.addData(dBean);
				// System.out.println(b);
				dd.listData(request, pageSize, pageNo);
				topage = "/datainfo.jsp";// 跳到新增页
			} else if (method.equals("query")) {
				String dataid = request.getParameter("dataid");
				String seekid = request.getParameter("seekid");
				String dataname = new String(request.getParameter("dataname").getBytes("iso-8859-1"),"utf-8");
				String seekname = new String(request.getParameter("seekname").getBytes("iso-8859-1"),"utf-8");
				//dd.queryData(dataid,seekid,dataname,seekname);
				dd.listDataQuery(request, pageSize, pageNo, dataid,seekid,dataname,seekname);
				topage = "/datainfo_query_result.jsp";
			}else if (method.equals("pagequery")) {
				//dd.queryData(dataid,seekid,dataname,seekname);
				topage = "/datainfo_query.jsp";
			}
		}

		// 转发
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher(topage);
		rd.forward(request, response);
	}

}
