<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/base/tag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK rel="stylesheet" type="text/css"
	href="${baseurl}js/easyui/styles/default.css">
<%@ include file="/WEB-INF/jsp/base/common_css.jsp"%>
<%@ include file="/WEB-INF/jsp/base/common_js.jsp"%>
<title>修改用户密码</title>
</head>

<script type="text/javascript">
//修改密码提交
function repwd() {
	_confirm('您确定要修改密码吗?',null,
			function(){
		jquerySubByFId('userform', repwd_callback, null, "json");
			}
	);
}

function repwd_callback(data){
	
	message_alert(data);
	//修改成功自动关闭窗口
	 if (data.resultInfo.type == '1') {
		 location.href = '${baseurl}logout.action';
	} 
	
};
</script>
<body>
<form id="userform" action="${baseurl}repwdSumit.action"
		method="post">
		<!-- 更新用户的userId -->
		<input type="hidden" name="userId" value="${userId}" />
		<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%"
			bgColor=#c4d8ed>

			<TBODY>
				<TR>
					<TD background=images/r_0.gif width="100%">
						<TABLE cellSpacing=0 cellPadding=0 width="100%">
							<TBODY>
								<TR>
									<TD>&nbsp;系统用户信息</TD>
									<TD align=right>&nbsp;</TD>
								</TR>
							</TBODY>
						</TABLE>
					</TD>
				</TR>
				</TBODY>

				<TR>
					<TD>
						<TABLE class="toptable grid" border=1 cellSpacing=1 cellPadding=4
							align=center>
							<TBODY>
							<tr>
							<TD height=30 width="15%" align=right>原始密碼：</TD>
									<TD class=category width="35%">
									<input type="password" id="sysuser_oldpwd"
									name="oldpwd" />
									</TD>
									</tr>
									<tr>
									<TD height=30 width="15%" align=right>新密碼：</TD>
									<TD class=category width="35%">
									<input type="password" id="sysuser_newpwd"
									name="newpwd" />
									</TD>
									</tr>
									<tr>
									<TD height=30 width="15%" align=right>确认密碼：</TD>
									<TD class=category width="35%">
									<input type="password" id="sysuser_repwd"
									name="repwd"/>
									</TD>
									</tr>
									<tr>
									<tr>
									<td colspan=4 align=center class=category><a
										id="submitbtn" class="easyui-linkbutton" iconCls="icon-ok"
										href="#" onclick="repwd()">提交</a> <a id="closebtn"
										class="easyui-linkbutton" iconCls="icon-cancel" href="#"
										onclick="parent.closemodalwindow()">关闭</a></td>
								</tr>
									</tr>
							</TBODY>
							</TABLE>
							</TD>
							</TR>
							</TABLE>
							</form>
</body>
</html>