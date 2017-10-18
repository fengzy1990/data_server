<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.cn.entity.DataBean"%>
<%@ page import="java.util.*"%>
<%
	String pageSize = (String) request.getAttribute("pageSize");
	String pageNo = (String) request.getAttribute("pageNo");
	DataBean dBean = (DataBean) request.getAttribute("dataBean");
%>
<html>
<head>

<title>綜合信息管理系统 -信息更新</title>

<link rel="stylesheet" type="text/css" href="css/styles.css">
<script language="javascript">
	function validDataEdit(theform) {
		var dataid = theform.dataid.value;
		var dataname = theform.dataname.value;
		var sex = theform.datamark.value;

		if (dataid == "") {
			alert("密码不能为空！");
			document.form1.dataid.focus();
			return false;
		}
		if (dataname == "") {
			alert("姓名不能为空！");
			document.form1.dataname.focus();
			return false;
		}
		if (datamark == "") {
			alert("姓名不能为空！");
			document.form1.datamark.focus();
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
							<TD>当前位置：信息管理&gt;&gt;更新信息</TD>
							<TD align="right">
							<a href="datainfo.do?method=list&pageSize=<%=pageSize%>&pageNo=<%=pageNo%>">返回信息列表</a>
							</TD>
							<TD width="20"></TD>
						</TR>
					</TABLE>

					<form name="form1" action="datainfo.do?method=update&dataid=<%=dBean.getData_id()%>" method="post"
						onsubmit="return validDataEdit(this)">

						<TABLE border="0" width="100%">
							<TR>
								<TD><span style="color: #C10202">*</span>信息ID</TD>
								<TD><input type="text" name="dataid" maxlength="100"
								     value="<%=dBean.getData_id()%>"></TD>
							</TR>
							<TR>
								<TD><span style="color: #C10202">*</span>信息名称</TD>
								<TD><input type="text" name="dataname" maxlength="50"
									value="<%=dBean.getData_name()%>">
									</TD>
							</TR>
							<TR>
								<TD><span style="color: #C10202">*</span>信息备注</TD>
								<TD>
								<input type="text" name="datamark" maxlength="50"
									value="<%=dBean.getData_mark()%>">
								</TD>
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
