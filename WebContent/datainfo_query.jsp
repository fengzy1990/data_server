<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>

<title>綜合信息管理系统 -信息查询</title>

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
							<TD>当前位置：信息管理&gt;&gt;信息查询</TD>
							<TD width="20"></TD>
						</TR>
					</TABLE>

					<form name="form1" action="datainfo.do?method=query" method="post"
						onsubmit="return validDataEdit(this)">

						<TABLE border="0" width="100%">
							<TR>
								<TD><span style="color: #C10202">*</span>信息ID</TD>
								<TD><input type="text" name="dataid" maxlength="100"
								     value=" "></TD>
								     <td>
									精确查找<input type="radio" value="1" name="seekid">
									模糊查找<input type="radio" value="2" name="seekid" checked="true">
								    </td>
							</TR>
							<TR>
								<TD><span style="color: #C10202">*</span>信息名称</TD>
								<TD><input type="text" name="dataname" maxlength="50"
									value=" ">
								</TD>
								 <td>
									精确查找<input type="radio" value="1" name="seekname">
									模糊查找<input type="radio" value="2" name="seekname" checked="true">
								  </td>
							</TR>

								<TR>
									<TD colspan="2">
										<input type="submit" name="submit" value="查询">
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
