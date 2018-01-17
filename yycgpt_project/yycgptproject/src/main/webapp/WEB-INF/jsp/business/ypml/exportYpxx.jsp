<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/base/tag.jsp"%>
<html>
<head>
<title>产品目录导出</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<%@ include file="/WEB-INF/jsp/base/common_css.jsp"%>
<%@ include file="/WEB-INF/jsp/base/common_js.jsp"%>

<script type="text/javascript">
	//产品信息导出
	//function ypxxexport() {
	//调用ajax Form提交
	//jquerySubByFId('ypxxlistFrom', ypxxExprot_callback, null, "json");
	//gysypmlexport
	//}
	function ypxxExprot_callback(data) {

		//在这里提示信息中有文件下载链接
		message_alert(data);

	}

	function ypxxexport() {
		//_confirm询问是否继续操作？
		//_confirm('您确定要导出产品吗?', null,
		//执行添加函数
		//function() {
		//定义一个数组，准备存放选中行的序号
		var indexs = [];
		//获取数据列表中所有选中的行(数组)
		var rows = dataGrid_obj.datagrid('getSelections');
		//便利所有选中的行
		for (var i = 0; i < rows.length; i++) {

			//alert(dataGrid_obj.datagrid('getRowIndex',rows[i]));
			//将返回的选中行的序号加到indexs数组中
			var index = dataGrid_obj.datagrid('getRowIndex', rows[i]);//选中行的下标
			//将选中行的序号设置到数组indexs中
			indexs.push(index);
			//alert(dataGrid_obj.datagrid('getRowIndex',rows[i]));
		}
		//判断如果存在选中的行，indexs数组里边有选中行的序号
		//if (rows.length > 0) {//如果存在选中的行则将indexs数组中的序号格式化为逗号分隔的并赋给indexs控件

		$("#indexs").val(indexs.join(","));//将indexs数组的元素在中间加逗号拼接成一个字符串
		//提交form，提交数据包括产品信息id(每条记录都 有)，indexs（hidden）
		jquerySubByFId('ypxxlistFrom', ypxxExprot_callback, null, "json");
		//} else {
		//如果没有选中行则提示
		//	alert_warn("请选择要添加的产品");
		//}

		//}

		//)
	};

	//删除
	function delYpxx(ypxxId) {
		//第一个参数是提示信息，第二个参数，取消执行的函数指针，第三个参是，确定执行的函数指针
		_confirm('您确认删除吗？', null, function() {

			//将要删除的id赋值给deleteid，deleteid在sysuserdeleteform中
			$("#delete_id").val(ypxxId);
			//使用ajax的from提交执行删除
			//sysuserdeleteform：form的id，userdel_callback：删除回调函数，
			//第三个参数是url的参数
			//第四个参数是datatype，表示服务器返回的类型
			jquerySubByFId('ypxxdeleteform', ypxxdel_callback, null, "json");

		});
	}
	//删除产品信息回调函数
	function ypxxdel_callback(data) {

		message_alert(data);

		//删除陈功，重新加载页面
		var type = data.resultInfo.type;
		if (type == 1) {
			ypxxmlquery();
		}
	}

	var columns = [ [
			{
				//显示一个复选框
				field : 'check',
				title : '选择',
				checkbox : true
			},
			{
				field : 'id',
				hidden : true,//隐藏
				formatter : function(value, row, index) {
					//gysypmls对应action接收对象中list的名称，[]括号中是从0开始序号,id是list中对象属性
					return '<input type="hidden" name="ypxxs['+index+'].id" value="'+value+'" /><input type="hidden" name="ypxxs['+index+'].mc" value="'+row.mc+'" />';
				}
			},
			{
				field : 'mc',
				title : '通用名',
				align : 'center',
				width : 130
			},
			{
				field : 'bm',
				title : '流水号',
				align : 'center',
				width : 80
			},
			{
				field : 'zbjg',
				title : '中标价格',
				align : 'center',
				width : 80
			},
			{
				field : 'gg',
				title : '规格',
				align : 'center',
				width : 80
			},
			{
				field : 'scqymc',
				title : '生产企业名称',
				align : 'center',
				width : 180
			},
			{
				field : 'jyztmc',
				title : '交易状态',
				align : 'center',
				width : 80
			},
			{
				field : 'lbmc',
				title : '管理类别',
				align : 'center',
				width : 80
			},
			{
				field : 'editBtn',
				title : '修改',
				align : 'center',
				width : 60,
				formatter : function(value, row, index) {
					return "<a href=javaScript:editYpxx('" + row.id
							+ "')>修改</a>";
				}
			},
			{
				field : 'delBtn',
				title : '删除',
				align : 'center',
				width : 60,
				formatter : function(value, row, index) {
					return "<a href=javaScript:delYpxx('" + row.id
							+ "')>删除</a>";
				}
			} ] ];

	var dataGrid_obj;
	//datagrid加载
	function initGrid() {
		dataGrid_obj = $('#ypxxlist');
		dataGrid_obj.datagrid({
			title : '产品列表',
			//nowrap : false,
			columns : columns,
			striped : true,
			//collapsible : true,
			url : '${baseurl}ypml/queryypxxsubmit.action',
			idField : 'id',//json数据集的主键
			//frozenColumns : frozenColumns,
			pagination : true,
			rownumbers : true,
			toolbar : toolbar,
			loadMsg : "",
			pageList : [ 15, 30, 50, 100 ],//设置每页显示个数
			onClickRow : function(index, field, value) {
				dataGrid_obj.datagrid('unselectRow', index);
			},
			//将加载成功后执行：清除选中的行
			onLoadSuccess : function() {
				$('#ypxxlist').datagrid('clearSelections');
			}
		});

	}
	$(function() {
		initGrid();

		/* //加载产品类型
		getDictinfoIdlist('001','ypxxCustom_lb','00101');

		//加载交易状态
		getDictinfoCodelist('003','ypxxCustom.jyzt'); */
	});

	//列表查询
	function ypxxmlquery() {
		//将form中的数据组成json
		var formdata = $("#ypxxlistFrom").serializeJson();
		//alert(formdata);
		//取消所有datagrid中的选择
		$('#ypxxlist').datagrid('unselectAll');
		//json是datagrid需要格式数据，向服务器发送的是key/value
		$('#ypxxlist').datagrid('load', formdata);
	}

	//修改
	function editYpxx(id) {
		//打开页面的添加页面
		createmodalwindow("修改产品信息", 800, 250,
				'${baseurl}ypml/editYpxx.action?id=' + id);
	}
</script>

</head>
<body>
	<!-- 导出条件 -->

	<form id="ypxxlistFrom" name="ypxxlistFrom"
		action="${baseurl}ypml/exportYpxxSumbit.action" method="post">
		<!-- indexs中存储就是选中的行的序号中间以逗号分隔 -->
		<input type="hidden" name="indexs" id="indexs" />
		<TABLE class="table_search">
			<TBODY>
				<TR>
					<TD class="left">生产企业名称：</TD>
					<td><INPUT type="text" name="ypxxCustom.scqymc" /></td>

					<TD class="left">流水号：</TD>
					<td><INPUT type="text" name="ypxxCustom.bm" /></td>
					<TD class="left">通用名：</td>
					<td><INPUT type="text" name="ypxxCustom.mc" /></TD>


				</TR>

				<tr>
					<TD class="left">产品类别：</TD>
					<td><select id="ypxxCustom.lb" name="ypxxCustom.lb"
						style="width: 150px">
							<option value="">全部</option>
							<c:forEach items="${yplblist}" var="value">
								<option value="${value.id}">${value.info}</option>
							</c:forEach>
					</select></td>


					<TD class="left">交易状态：</TD>
					<td><select id="ypxxCustom.jyzt" name="ypxxCustom.jyzt"
						style="width: 100px">
							<option value="">全部</option>
							<c:forEach items="${jyztlist}" var="value">
								<option value="${value.dictcode}">${value.info}</option>
							</c:forEach>
					</select></td>
					<td class="left">价格范围：</td>
					<td><INPUT id="ypxxCustom.zbjglower"
						name="ypxxCustom.price_start" style="width: 70px" /> 至 <INPUT
						id="ypxxCustom.zbjgupper" name="ypxxCustom.price_end"
						style="width: 70px" /></td>

				</tr>

				<tr>
					<td colspan="12" style="text-align: center"><a id="btn"
						href="#" onclick="ypxxexport()" class="easyui-linkbutton"
						iconCls='icon-search'>导出</a> <a id="btn" href="#"
						onclick="ypxxmlquery()" class="easyui-linkbutton"
						iconCls='icon-search'>查询</a></td>
				</tr>
			</TBODY>
		</TABLE>


		<TABLE border=0 cellSpacing=0 cellPadding=0 width="99%" align=center>
			<TBODY>
				<TR>
					<TD>
						<table id="ypxxlist"></table>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
	</form>

	<form action="${baseurl}ypml/delypxxsubmit.action" id="ypxxdeleteform"
		method="post">
		<input type="hidden" id="delete_id" name="ypxxid"></input>

	</form>

</body>
</html>