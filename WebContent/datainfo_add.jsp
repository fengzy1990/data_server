<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.cn.entity.DataBean"%>
<%@ page import="java.util.*"%>
<html>
<head>

<title>綜合信息管理系统 - 增加信息</title>

<link rel="stylesheet" type="text/css" href="css/styles.css">
<script language="javascript">
	function validDataEdit(theform) {
		var dataid = theform.dataid.value;
		var dataname = theform.dataname.value;
		var datamark = theform.datamark.value;

		if (dataid == "") {
			alert("ID不能为空！");
			document.form1.dataid.focus();
			return false;
		}
		if (dataname == "") {
			alert("名称不能为空！");
			document.form1.dataname.focus();
			return false;
		}
		if (datamark == "") {
			alert("备注不能为空！");
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
							<TD>当前位置：用户管理&gt;&gt;增加信息</TD>
							<TD align="right">
							<a href="datainfo.do?method=list">返回信息列表</a>
							</TD>
							<TD width="20"></TD>
						</TR>
					</TABLE>

					<form name="form1" action="datainfo.do?method=add" method="post"
						onsubmit="return validDataEdit(this)">

						<TABLE border="0" width="100%">
							<TR>
								<TD><span style="color: #C10202">*</span>信息ID:</TD>
								<TD><input type="text" name="dataid" maxlength="50"
									value=""></TD>
							</TR>
							<TR>
								<TD><span style="color: #C10202">*</span>信息名称:</TD>
								<TD>
								<input type="text" name="dataname" maxlength="50"
									value="">
								</TD>
							</TR>
							<TR>
								<TD><span style="color: #C10202">*</span>信息备注:</TD>
								<TD>
								<input type="text" name="datamark" maxlength="50"
									value="">
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
