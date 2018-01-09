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
<title>修改产品信息</title>
<script type="text/javascript">
	function ypxxsave() {

		jquerySubByFId('ypxxform', ypxxsave_callback, null, "json");

	}
	//ajax调用的回调函数，ajax请求完成调用此函数，传入的参数是action返回的结果
	function ypxxsave_callback(data) {
		message_alert(data);
		//修改成功自动关闭窗口
		if (data.resultInfo.type == '1') {
			//window.setInterval("parent.closemodalwindow()", 1000);
			parent.ypxxmlquery();
			//延时一秒执行
			setTimeout("parent.closemodalwindow()", 1000);
		}
	}
</script>
</head>
<body>


	<form id="ypxxform" action="${baseurl}ypml/editYpxxSubmit.action"
		method="post">
		<!-- 更新用户的id -->
		<input type="hidden" name="id" value="${ypxxCustom.id}" />
		<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%"
			bgColor=#c4d8ed>

			<TBODY>
				<TR>
					<TD background=images/r_0.gif width="100%">
						<TABLE cellSpacing=0 cellPadding=0 width="100%">
							<TBODY>
								<TR>
									<TD>&nbsp;产品信息</TD>
									<TD align=right>&nbsp;</TD>
								</TR>
							</TBODY>
						</TABLE>
					</TD>
				</TR>

				<TR>
					<TD>
						<TABLE class="toptable grid" border=1 cellSpacing=1 cellPadding=4
							align=center>
							<TBODY>

								<TR>
									<TD height=30 width="15%" align=right>生产企业：</TD>
									<TD class=category width="40%">
										<div>
											<input type="text" id="ypxx_scqymc"
												name="ypxxCustom.scqymc" value="${ypxxCustom.scqymc}" />
										</div> <!-- sysuser_useridTip用于显示提示信息，提示div的id等于校验input的id+Tip -->
									</TD>
									<TD height=30 width="15%" align=right>产品名称：</TD>
									<TD class=category width="40%">
										<div>
											<input type="text" id="ypxxCustom_mc"
												name="ypxxCustom.mc"
												value="${ypxxCustom.mc}" />
										</div>
									</TD>
								</TR>
								<TR>
									<TD height=30 width="15%" align=right>规格：
									</TD>
									<TD class=category width="35%">
										<div>
											<input type="text" id="ypxxCustom_gg"
												name="ypxxCustom.gg"  value="${ypxxCustom.gg }"/> 
										</div>
									</TD>
									<TD height=30 width="15%" align=right>中标价格：</TD>
									<TD class=category width="35%">
										<div>
											<input type="text" id="ypxxCustom_zbjg"
												name="ypxxCustom.zbjg"  value="${ypxxCustom.zbjg }"/> 
										</div>
									</TD>

								</TR>
								<TR>
									<TD height=30 width="15%" align=right>交易状态：</TD>
									<TD class=category width="35%">
										<div>
											<select name="ypxxCustom.jyzt" id="ypxxCustom_jyzt">

												<option value="">请选择</option>
												<option value="1"
													<c:if test="${ypxxCustom.jyzt=='1'}">selected</c:if>>正常</option>
												<option value="0"
													<c:if test="${ypxxCustom.jyzt=='0'}">selected</c:if>>暂停</option>
											</select>
										</div>
									</TD>
								
									<TD height=30 width="15%" align=right>管理类别：</TD>
									
									<TD class=category width="35%">
										<div>
											<select name="ypxxCustom.lb" id="ypxxCustom_lb">

												<option value="">请选择</option>
												<option value="00101"
													<c:if test="${ypxxCustom.lb=='00101'}">selected</c:if>>I类</option>
												<option value="00102"
													<c:if test="${ypxxCustom.lb=='00102'}">selected</c:if>>II类</option>
												<option value="00103"
													<c:if test="${ypxxCustom.lb=='00103'}">selected</c:if>>III类</option>
											</select>
										</div>
									</TD>

								</TR>

								

								<tr>
									<td colspan=4 align=center class=category><a
										id="submitbtn" class="easyui-linkbutton" iconCls="icon-ok"
										href="#" onclick="ypxxsave()">提交</a> <a id="closebtn"
										class="easyui-linkbutton" iconCls="icon-cancel" href="#"
										onclick="parent.closemodalwindow()">关闭</a></td>
								</tr>

							</TBODY>
						</TABLE>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
	</form>
</body>
</html>