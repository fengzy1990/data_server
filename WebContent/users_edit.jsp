<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.cn.entity.UserBean"%>
<%@ page import="java.util.*"%>
<%
	String pageSize = (String) request.getAttribute("pageSize");
	String pageNo = (String) request.getAttribute("pageNo");
	UserBean mb = (UserBean) request.getAttribute("userBean");
%>
<html>
<head>

<title>綜合信息管理系统 - 用戶管理</title>

<link rel="stylesheet" type="text/css" href="css/styles.css">
<script language="javascript">
	function validUserEdit(theform) {
		var password = theform.password.value;
		var name = theform.name.value;
		var sex = theform.sex.value;
		var permission = theform.permission.value;

		if (password == "") {
			alert("密码不能为空！");
			document.form1.password.focus();
			return false;
		}
		if (name == "") {
			alert("姓名不能为空！");
			document.form1.name.focus();
			return false;
		}
		if (!checkSex()) {
			alert('你选择你的性别!');
			//document.form1.shenfen.focus();
			return false;
		}
		if (!checkPermission()) {
			alert("请选择你的权限！");
			//document.form1.permission.focus();
			return false;
		}
		return true;
	}
	function checkSex() {
		var sex = document.getElementsByName("sex");
		var count = 0;
		for (var i = 0; i < sex.length; i++) {
			if (sex[i].checked) {
				count++;
				break;
			}
		}
		if (count == 0) {
			return false;
		}
		return true;
	}
	function checkPermission() {
		var sex = document.getElementsByName("permission");
		var count = 0;
		for (var i = 0; i < sex.length; i++) {
			if (sex[i].checked) {
				count++;
				break;
			}
		}
		if (count == 0) {
			return false;
		}
		return true;
	}
</script>
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
							<TD>当前位置：用户管理&gt;&gt;修改用户</TD>
							<TD align="right">
							<a href="users.do?method=list&pageSize=<%=pageSize%>&pageNo=<%=pageNo%>">返回用户信息列表</a>
							</TD>
							<TD width="20"></TD>
						</TR>
					</TABLE>

					<form name="form1" action="users.do?method=update&userid=<%=mb.getUser_id()%>" method="post"
						onsubmit="return validUserEdit(this)">

						<TABLE border="0" width="100%">
							<TR>
								<TD>用户ID</TD>
								<TD><%=mb.getUser_id()%></TD>
							</TR>
							<TR>
								<TD><span style="color: #C10202">*</span>用户密码:</TD>
								<TD><input type="text" name="password" maxlength="50"
									value="<%=mb.getPassword()%>"></TD>
							</TR>
							<TR>
								<TD><span style="color: #C10202">*</span>姓名:</TD>
								<TD>
								<input type="text" name="name" maxlength="50"
									value="<%=mb.getName()%>">
								</TD>
							</TR>

							<tr>
								<td><span style="color: #C10202">*</span>性别:</td>
								<td>男 <input type="radio" value="男" name="sex"> 
								             女 <input	 type="radio" value="女" name="sex">
								</td>
							</tr>
							<TR>
								<TD><span style="color: #C10202">*</span>用户权限:</TD>
								<td>管理员 <input type="radio" value="0" name="permission">
									普通用户<input type="radio" value="1" name="permission">
									未审核用户<input type="radio" value="2" name="permission">
								</td>
								
							</TR>
								
								<TR>
									<TD colspan="2">
										<input type="submit" name="submit" value="提交">
									</TD>
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
