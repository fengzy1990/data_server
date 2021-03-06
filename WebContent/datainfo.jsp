<%@ page language="java" pageEncoding="UTF-8"%>

<%@page import="java.util.List"%>
<%@page import="java.util.Hashtable"%>
<%@page import="java.util.Iterator"%>

<%
	String pageSize = (String) request.getAttribute("pageSize");
	String pageNo = (String) request.getAttribute("pageNo");
%>
<html>
<head>

<title>综合信息系统 - 数据信息</title>

<link rel="stylesheet" type="text/css" href="css/styles.css">

</head>
<body>
	<div align="center">

		<table height="100%" width="100%" border="0" cellspacing="0"
			cellpadding="0">
			<tr>
				<td colspan="2" height="108">
					<table height="108" background="images/banner.gif" border="0"
						cellspacing="0" cellpadding="0" width="100%">
						<tr>
							<td></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td width="160" bgcolor="#AEEEEE" valign="top" height="100%"><%@ include
						file="inc/menu.jsp"%></td>
				<td align="left" valign="top">

					<TABLE width="100%" class="position">
						<TR>
							<TD>当前位置：信息管理</TD>
							<TD align="right"><a
								href="datainfo.do?method=addpage&pageSize=<%=pageSize%>&pageNo=<%=pageNo%>">新增一条信息</a>
							</TD>
							<TD width="20"></TD>
						</TR>
					</TABLE> <b></b>

					<TABLE border="0" width="100%">

						<TR class="tableheader">
							<TD>信息ID</TD>
							<TD>信息名称</TD>
							<TD>备注信息</TD>
							<TD>操作</TD>
						</TR>

						<%
							List<Hashtable<String, String>> list = (List<Hashtable<String, String>>) request.getAttribute("list");
							Iterator it = list.iterator();
							while (it.hasNext()) {
								Hashtable<String, String> hash = (Hashtable<String, String>) it.next();
								String dataid = (String) hash.get("dataid");
						%>
						<TR>
						<TD><%=hash.get("dataid")%></TD>
							<TD><%=hash.get("dataname")%></TD>
							<TD><%=hash.get("datamark")%></TD>
							<TD><a
								href='datainfo.do?method=updatepage&dataid=<%=dataid%>&pageSize=<%=pageSize%>&pageNo=<%=pageNo%>'>修改</a>
<a
								href='datainfo.do?method=delete&dataid=<%=dataid%>&pageSize=<%=pageSize%>&pageNo=<%=pageNo%>'>删除</a>

							</TD>
						</TR>
						<%
							}
						%>
					</TABLE>


					<form name="form1" action="datainfo.do?method=list" method="post">
						<TABLE border="0" width="100%" class="pager">
							<TR>
								<TD align="left">每页记录数： <select name="pageSize"
									onchange="document.all.pageNo.value='1';document.all.form1.submit();">
										<option value="10" <%if (pageSize.equals("10")) {%>
											selected="selected" <%}%>>10</option>
										<option value="25" <%if (pageSize.equals("25")) {%>
											selected="selected" <%}%>>25</option>
										<option value="50" <%if (pageSize.equals("50")) {%>
											selected="selected" <%}%>>50</option>
										<option value="100" <%if (pageSize.equals("100")) {%>
											selected="selected" <%}%>>100</option>
										<option value="200" <%if (pageSize.equals("200")) {%>
											selected="selected" <%}%>>200</option>
										<option value="300" <%if (pageSize.equals("300")) {%>
											selected="selected" <%}%>>300</option>
										<option value="500" <%if (pageSize.equals("500")) {%>
											selected="selected" <%}%>>500</option>
								</select>
								</TD>
								<TD align="center">总记录数：<%=request.getAttribute("rowCount")%></TD>
								<TD align="right"><a
									href="javascript:document.all.pageNo.value='<%=request.getAttribute("pageFirstNo")%>';document.all.form1.submit();">首页</a>
									<a
									href="javascript:document.all.pageNo.value='<%=request.getAttribute("pagePreNo")%>';document.all.form1.submit();">前一页</a>
									<a
									href="javascript:document.all.pageNo.value='<%=request.getAttribute("pageNextNo")%>';document.all.form1.submit();">后一页</a>
									<a
									href="javascript:document.all.pageNo.value='<%=request.getAttribute("pageLastNo")%>';document.all.form1.submit();">尾页</a>
									<select name="pageNo" onchange="document.all.form1.submit();">
										<%
											int pageCount = ((Integer) request.getAttribute("pageCount")).intValue();
											for (int i = 1; i <= pageCount; i++) {
										%>
										<option value="<%=i%>" <%if (pageNo.equals(i + "")) {%>
											selected="selected" <%}%>><%=i%></option>
										<%
											}
										%>
								</select></TD>
								<TD width="20"></TD>
							</TR>
						</TABLE>
					</form>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><%@ include file="inc/foot.jsp"%>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
