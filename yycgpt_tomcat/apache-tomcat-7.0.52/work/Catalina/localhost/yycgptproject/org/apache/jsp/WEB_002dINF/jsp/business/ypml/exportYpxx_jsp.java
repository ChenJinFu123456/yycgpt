/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.52
 * Generated at: 2018-01-17 14:44:17 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp.business.ypml;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class exportYpxx_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(3);
    _jspx_dependants.put("/WEB-INF/jsp/base/common_js.jsp", Long.valueOf(1514967459311L));
    _jspx_dependants.put("/WEB-INF/jsp/base/tag.jsp", Long.valueOf(1510902506752L));
    _jspx_dependants.put("/WEB-INF/jsp/base/common_css.jsp", Long.valueOf(1511161915525L));
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005ffmt_005fsetBundle_0026_005fvar_005fbasename_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005ffmt_005fsetBundle_0026_005fvar_005fbasename_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005ffmt_005fsetBundle_0026_005fvar_005fbasename_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      if (_jspx_meth_fmt_005fsetBundle_005f0(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      if (_jspx_meth_c_005fset_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<title>产品目录导出</title>\r\n");
      out.write("<meta http-equiv=\"pragma\" content=\"no-cache\">\r\n");
      out.write("<meta http-equiv=\"cache-control\" content=\"no-cache\">\r\n");
      out.write("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${baseurl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("styles/style.css\"/> \r\n");
      out.write("<LINK rel=\"stylesheet\" type=\"text/css\"\thref=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${baseurl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("js/easyui/themes/default/easyui.css\" />\r\n");
      out.write("<LINK rel=\"stylesheet\" type=\"text/css\"\thref=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${baseurl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("js/easyui/themes/icon.css\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\"  href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${baseurl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("js/My97DatePicker/skin/WdatePicker.css\" />\r\n");
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${baseurl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("js/formvalidator/style/validator.css\"/> ");
      out.write('\r');
      out.write('\n');
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${baseurl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("js/jquery-1.4.4.min.js\"></script>\r\n");
      out.write("<SCRIPT type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${baseurl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("js/easyui/jquery.easyui.min.1.2.2.js\"></SCRIPT>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${baseurl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("js/easyui/locale/easyui-lang-zh_CN.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${baseurl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("js/jquery.form.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${baseurl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("js/custom.jquery.form.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${baseurl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("js/custom.box.main.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${baseurl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("js/jquery.ajax.custom.extend.js\"></script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<SCRIPT type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${baseurl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("js/menuload.js\"></SCRIPT>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${baseurl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("js/My97DatePicker/WdatePicker.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${baseurl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("js/jqueryvalidator/formValidator-4.1.3.js\" type=\"text/javascript\" charset=\"UTF-8\"></script>\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${baseurl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("js/jqueryvalidator/formValidatorRegex.js\" type=\"text/javascript\" charset=\"UTF-8\"></script>\r\n");
      out.write("\r\n");
      out.write("<script type='text/javascript' src='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${baseurl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("dwr/engine.js'></script>   \r\n");
      out.write("<script type='text/javascript' src='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${baseurl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("dwr/util.js'></script>  \r\n");
      out.write("<script type='text/javascript' src='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${baseurl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("dwr/interface/dwrService.js'></script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\t//产品信息导出\r\n");
      out.write("\t//function ypxxexport() {\r\n");
      out.write("\t//调用ajax Form提交\r\n");
      out.write("\t//jquerySubByFId('ypxxlistFrom', ypxxExprot_callback, null, \"json\");\r\n");
      out.write("\t//gysypmlexport\r\n");
      out.write("\t//}\r\n");
      out.write("\tfunction ypxxExprot_callback(data) {\r\n");
      out.write("\r\n");
      out.write("\t\t//在这里提示信息中有文件下载链接\r\n");
      out.write("\t\tmessage_alert(data);\r\n");
      out.write("\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\tfunction ypxxexport() {\r\n");
      out.write("\t\t//_confirm询问是否继续操作？\r\n");
      out.write("\t\t//_confirm('您确定要导出产品吗?', null,\r\n");
      out.write("\t\t//执行添加函数\r\n");
      out.write("\t\t//function() {\r\n");
      out.write("\t\t//定义一个数组，准备存放选中行的序号\r\n");
      out.write("\t\tvar indexs = [];\r\n");
      out.write("\t\t//获取数据列表中所有选中的行(数组)\r\n");
      out.write("\t\tvar rows = dataGrid_obj.datagrid('getSelections');\r\n");
      out.write("\t\t//便利所有选中的行\r\n");
      out.write("\t\tfor (var i = 0; i < rows.length; i++) {\r\n");
      out.write("\r\n");
      out.write("\t\t\t//alert(dataGrid_obj.datagrid('getRowIndex',rows[i]));\r\n");
      out.write("\t\t\t//将返回的选中行的序号加到indexs数组中\r\n");
      out.write("\t\t\tvar index = dataGrid_obj.datagrid('getRowIndex', rows[i]);//选中行的下标\r\n");
      out.write("\t\t\t//将选中行的序号设置到数组indexs中\r\n");
      out.write("\t\t\tindexs.push(index);\r\n");
      out.write("\t\t\t//alert(dataGrid_obj.datagrid('getRowIndex',rows[i]));\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t//判断如果存在选中的行，indexs数组里边有选中行的序号\r\n");
      out.write("\t\t//if (rows.length > 0) {//如果存在选中的行则将indexs数组中的序号格式化为逗号分隔的并赋给indexs控件\r\n");
      out.write("\r\n");
      out.write("\t\t$(\"#indexs\").val(indexs.join(\",\"));//将indexs数组的元素在中间加逗号拼接成一个字符串\r\n");
      out.write("\t\t//提交form，提交数据包括产品信息id(每条记录都 有)，indexs（hidden）\r\n");
      out.write("\t\tjquerySubByFId('ypxxlistFrom', ypxxExprot_callback, null, \"json\");\r\n");
      out.write("\t\t//} else {\r\n");
      out.write("\t\t//如果没有选中行则提示\r\n");
      out.write("\t\t//\talert_warn(\"请选择要添加的产品\");\r\n");
      out.write("\t\t//}\r\n");
      out.write("\r\n");
      out.write("\t\t//}\r\n");
      out.write("\r\n");
      out.write("\t\t//)\r\n");
      out.write("\t};\r\n");
      out.write("\r\n");
      out.write("\t//删除\r\n");
      out.write("\tfunction delYpxx(ypxxId) {\r\n");
      out.write("\t\t//第一个参数是提示信息，第二个参数，取消执行的函数指针，第三个参是，确定执行的函数指针\r\n");
      out.write("\t\t_confirm('您确认删除吗？', null, function() {\r\n");
      out.write("\r\n");
      out.write("\t\t\t//将要删除的id赋值给deleteid，deleteid在sysuserdeleteform中\r\n");
      out.write("\t\t\t$(\"#delete_id\").val(ypxxId);\r\n");
      out.write("\t\t\t//使用ajax的from提交执行删除\r\n");
      out.write("\t\t\t//sysuserdeleteform：form的id，userdel_callback：删除回调函数，\r\n");
      out.write("\t\t\t//第三个参数是url的参数\r\n");
      out.write("\t\t\t//第四个参数是datatype，表示服务器返回的类型\r\n");
      out.write("\t\t\tjquerySubByFId('ypxxdeleteform', ypxxdel_callback, null, \"json\");\r\n");
      out.write("\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\t//删除产品信息回调函数\r\n");
      out.write("\tfunction ypxxdel_callback(data) {\r\n");
      out.write("\r\n");
      out.write("\t\tmessage_alert(data);\r\n");
      out.write("\r\n");
      out.write("\t\t//删除陈功，重新加载页面\r\n");
      out.write("\t\tvar type = data.resultInfo.type;\r\n");
      out.write("\t\tif (type == 1) {\r\n");
      out.write("\t\t\typxxmlquery();\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\tvar columns = [ [\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\t//显示一个复选框\r\n");
      out.write("\t\t\t\tfield : 'check',\r\n");
      out.write("\t\t\t\ttitle : '选择',\r\n");
      out.write("\t\t\t\tcheckbox : true\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\tfield : 'id',\r\n");
      out.write("\t\t\t\thidden : true,//隐藏\r\n");
      out.write("\t\t\t\tformatter : function(value, row, index) {\r\n");
      out.write("\t\t\t\t\t//gysypmls对应action接收对象中list的名称，[]括号中是从0开始序号,id是list中对象属性\r\n");
      out.write("\t\t\t\t\treturn '<input type=\"hidden\" name=\"ypxxs['+index+'].id\" value=\"'+value+'\" /><input type=\"hidden\" name=\"ypxxs['+index+'].mc\" value=\"'+row.mc+'\" />';\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\tfield : 'mc',\r\n");
      out.write("\t\t\t\ttitle : '通用名',\r\n");
      out.write("\t\t\t\talign : 'center',\r\n");
      out.write("\t\t\t\twidth : 130\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\tfield : 'bm',\r\n");
      out.write("\t\t\t\ttitle : '流水号',\r\n");
      out.write("\t\t\t\talign : 'center',\r\n");
      out.write("\t\t\t\twidth : 80\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\tfield : 'zbjg',\r\n");
      out.write("\t\t\t\ttitle : '中标价格',\r\n");
      out.write("\t\t\t\talign : 'center',\r\n");
      out.write("\t\t\t\twidth : 80\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\tfield : 'gg',\r\n");
      out.write("\t\t\t\ttitle : '规格',\r\n");
      out.write("\t\t\t\talign : 'center',\r\n");
      out.write("\t\t\t\twidth : 80\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\tfield : 'scqymc',\r\n");
      out.write("\t\t\t\ttitle : '生产企业名称',\r\n");
      out.write("\t\t\t\talign : 'center',\r\n");
      out.write("\t\t\t\twidth : 180\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\tfield : 'jyztmc',\r\n");
      out.write("\t\t\t\ttitle : '交易状态',\r\n");
      out.write("\t\t\t\talign : 'center',\r\n");
      out.write("\t\t\t\twidth : 80\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\tfield : 'lbmc',\r\n");
      out.write("\t\t\t\ttitle : '管理类别',\r\n");
      out.write("\t\t\t\talign : 'center',\r\n");
      out.write("\t\t\t\twidth : 80\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\tfield : 'editBtn',\r\n");
      out.write("\t\t\t\ttitle : '修改',\r\n");
      out.write("\t\t\t\talign : 'center',\r\n");
      out.write("\t\t\t\twidth : 60,\r\n");
      out.write("\t\t\t\tformatter : function(value, row, index) {\r\n");
      out.write("\t\t\t\t\treturn \"<a href=javaScript:editYpxx('\" + row.id\r\n");
      out.write("\t\t\t\t\t\t\t+ \"')>修改</a>\";\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\tfield : 'delBtn',\r\n");
      out.write("\t\t\t\ttitle : '删除',\r\n");
      out.write("\t\t\t\talign : 'center',\r\n");
      out.write("\t\t\t\twidth : 60,\r\n");
      out.write("\t\t\t\tformatter : function(value, row, index) {\r\n");
      out.write("\t\t\t\t\treturn \"<a href=javaScript:delYpxx('\" + row.id\r\n");
      out.write("\t\t\t\t\t\t\t+ \"')>删除</a>\";\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t} ] ];\r\n");
      out.write("\r\n");
      out.write("\tvar dataGrid_obj;\r\n");
      out.write("\t//datagrid加载\r\n");
      out.write("\tfunction initGrid() {\r\n");
      out.write("\t\tdataGrid_obj = $('#ypxxlist');\r\n");
      out.write("\t\tdataGrid_obj.datagrid({\r\n");
      out.write("\t\t\ttitle : '产品列表',\r\n");
      out.write("\t\t\t//nowrap : false,\r\n");
      out.write("\t\t\tcolumns : columns,\r\n");
      out.write("\t\t\tstriped : true,\r\n");
      out.write("\t\t\t//collapsible : true,\r\n");
      out.write("\t\t\turl : '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${baseurl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("ypml/queryypxxsubmit.action',\r\n");
      out.write("\t\t\tidField : 'id',//json数据集的主键\r\n");
      out.write("\t\t\t//frozenColumns : frozenColumns,\r\n");
      out.write("\t\t\tpagination : true,\r\n");
      out.write("\t\t\trownumbers : true,\r\n");
      out.write("\t\t\ttoolbar : toolbar,\r\n");
      out.write("\t\t\tloadMsg : \"\",\r\n");
      out.write("\t\t\tpageList : [ 15, 30, 50, 100 ],//设置每页显示个数\r\n");
      out.write("\t\t\tonClickRow : function(index, field, value) {\r\n");
      out.write("\t\t\t\tdataGrid_obj.datagrid('unselectRow', index);\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\t//将加载成功后执行：清除选中的行\r\n");
      out.write("\t\t\tonLoadSuccess : function() {\r\n");
      out.write("\t\t\t\t$('#ypxxlist').datagrid('clearSelections');\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\r\n");
      out.write("\t}\r\n");
      out.write("\t$(function() {\r\n");
      out.write("\t\tinitGrid();\r\n");
      out.write("\r\n");
      out.write("\t\t/* //加载产品类型\r\n");
      out.write("\t\tgetDictinfoIdlist('001','ypxxCustom_lb','00101');\r\n");
      out.write("\r\n");
      out.write("\t\t//加载交易状态\r\n");
      out.write("\t\tgetDictinfoCodelist('003','ypxxCustom.jyzt'); */\r\n");
      out.write("\t});\r\n");
      out.write("\r\n");
      out.write("\t//列表查询\r\n");
      out.write("\tfunction ypxxmlquery() {\r\n");
      out.write("\t\t//将form中的数据组成json\r\n");
      out.write("\t\tvar formdata = $(\"#ypxxlistFrom\").serializeJson();\r\n");
      out.write("\t\t//alert(formdata);\r\n");
      out.write("\t\t//取消所有datagrid中的选择\r\n");
      out.write("\t\t$('#ypxxlist').datagrid('unselectAll');\r\n");
      out.write("\t\t//json是datagrid需要格式数据，向服务器发送的是key/value\r\n");
      out.write("\t\t$('#ypxxlist').datagrid('load', formdata);\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\t//修改\r\n");
      out.write("\tfunction editYpxx(id) {\r\n");
      out.write("\t\t//打开页面的添加页面\r\n");
      out.write("\t\tcreatemodalwindow(\"修改产品信息\", 800, 250,\r\n");
      out.write("\t\t\t\t'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${baseurl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("ypml/editYpxx.action?id=' + id);\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<!-- 导出条件 -->\r\n");
      out.write("\r\n");
      out.write("\t<form id=\"ypxxlistFrom\" name=\"ypxxlistFrom\"\r\n");
      out.write("\t\taction=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${baseurl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("ypml/exportYpxxSumbit.action\" method=\"post\">\r\n");
      out.write("\t\t<!-- indexs中存储就是选中的行的序号中间以逗号分隔 -->\r\n");
      out.write("\t\t<input type=\"hidden\" name=\"indexs\" id=\"indexs\" />\r\n");
      out.write("\t\t<TABLE class=\"table_search\">\r\n");
      out.write("\t\t\t<TBODY>\r\n");
      out.write("\t\t\t\t<TR>\r\n");
      out.write("\t\t\t\t\t<TD class=\"left\">生产企业名称：</TD>\r\n");
      out.write("\t\t\t\t\t<td><INPUT type=\"text\" name=\"ypxxCustom.scqymc\" /></td>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t<TD class=\"left\">流水号：</TD>\r\n");
      out.write("\t\t\t\t\t<td><INPUT type=\"text\" name=\"ypxxCustom.bm\" /></td>\r\n");
      out.write("\t\t\t\t\t<TD class=\"left\">通用名：</td>\r\n");
      out.write("\t\t\t\t\t<td><INPUT type=\"text\" name=\"ypxxCustom.mc\" /></TD>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t</TR>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<TD class=\"left\">产品类别：</TD>\r\n");
      out.write("\t\t\t\t\t<td><select id=\"ypxxCustom.lb\" name=\"ypxxCustom.lb\"\r\n");
      out.write("\t\t\t\t\t\tstyle=\"width: 150px\">\r\n");
      out.write("\t\t\t\t\t\t\t<option value=\"\">全部</option>\r\n");
      out.write("\t\t\t\t\t\t\t");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t</select></td>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t<TD class=\"left\">交易状态：</TD>\r\n");
      out.write("\t\t\t\t\t<td><select id=\"ypxxCustom.jyzt\" name=\"ypxxCustom.jyzt\"\r\n");
      out.write("\t\t\t\t\t\tstyle=\"width: 100px\">\r\n");
      out.write("\t\t\t\t\t\t\t<option value=\"\">全部</option>\r\n");
      out.write("\t\t\t\t\t\t\t");
      if (_jspx_meth_c_005fforEach_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t</select></td>\r\n");
      out.write("\t\t\t\t\t<td class=\"left\">价格范围：</td>\r\n");
      out.write("\t\t\t\t\t<td><INPUT id=\"ypxxCustom.zbjglower\"\r\n");
      out.write("\t\t\t\t\t\tname=\"ypxxCustom.price_start\" style=\"width: 70px\" /> 至 <INPUT\r\n");
      out.write("\t\t\t\t\t\tid=\"ypxxCustom.zbjgupper\" name=\"ypxxCustom.price_end\"\r\n");
      out.write("\t\t\t\t\t\tstyle=\"width: 70px\" /></td>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td colspan=\"12\" style=\"text-align: center\"><a id=\"btn\"\r\n");
      out.write("\t\t\t\t\t\thref=\"#\" onclick=\"ypxxexport()\" class=\"easyui-linkbutton\"\r\n");
      out.write("\t\t\t\t\t\ticonCls='icon-search'>导出</a> <a id=\"btn\" href=\"#\"\r\n");
      out.write("\t\t\t\t\t\tonclick=\"ypxxmlquery()\" class=\"easyui-linkbutton\"\r\n");
      out.write("\t\t\t\t\t\ticonCls='icon-search'>查询</a></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t</TBODY>\r\n");
      out.write("\t\t</TABLE>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t<TABLE border=0 cellSpacing=0 cellPadding=0 width=\"99%\" align=center>\r\n");
      out.write("\t\t\t<TBODY>\r\n");
      out.write("\t\t\t\t<TR>\r\n");
      out.write("\t\t\t\t\t<TD>\r\n");
      out.write("\t\t\t\t\t\t<table id=\"ypxxlist\"></table>\r\n");
      out.write("\t\t\t\t\t</TD>\r\n");
      out.write("\t\t\t\t</TR>\r\n");
      out.write("\t\t\t</TBODY>\r\n");
      out.write("\t\t</TABLE>\r\n");
      out.write("\t</form>\r\n");
      out.write("\r\n");
      out.write("\t<form action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${baseurl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("ypml/delypxxsubmit.action\" id=\"ypxxdeleteform\"\r\n");
      out.write("\t\tmethod=\"post\">\r\n");
      out.write("\t\t<input type=\"hidden\" id=\"delete_id\" name=\"ypxxid\"></input>\r\n");
      out.write("\r\n");
      out.write("\t</form>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_fmt_005fsetBundle_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  fmt:setBundle
    org.apache.taglibs.standard.tag.rt.fmt.SetBundleTag _jspx_th_fmt_005fsetBundle_005f0 = (org.apache.taglibs.standard.tag.rt.fmt.SetBundleTag) _005fjspx_005ftagPool_005ffmt_005fsetBundle_0026_005fvar_005fbasename_005fnobody.get(org.apache.taglibs.standard.tag.rt.fmt.SetBundleTag.class);
    _jspx_th_fmt_005fsetBundle_005f0.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fsetBundle_005f0.setParent(null);
    // /WEB-INF/jsp/base/tag.jsp(3,0) name = basename type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fsetBundle_005f0.setBasename("resources.messages");
    // /WEB-INF/jsp/base/tag.jsp(3,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fsetBundle_005f0.setVar("messagesBundle");
    int _jspx_eval_fmt_005fsetBundle_005f0 = _jspx_th_fmt_005fsetBundle_005f0.doStartTag();
    if (_jspx_th_fmt_005fsetBundle_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fsetBundle_0026_005fvar_005fbasename_005fnobody.reuse(_jspx_th_fmt_005fsetBundle_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fsetBundle_0026_005fvar_005fbasename_005fnobody.reuse(_jspx_th_fmt_005fsetBundle_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fset_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f0 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f0.setParent(null);
    // /WEB-INF/jsp/base/tag.jsp(4,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f0.setVar("baseurl");
    // /WEB-INF/jsp/base/tag.jsp(4,0) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}/", java.lang.Object.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fset_005f0 = _jspx_th_c_005fset_005f0.doStartTag();
    if (_jspx_th_c_005fset_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fforEach_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f0.setParent(null);
    // /WEB-INF/jsp/business/ypml/exportYpxx.jsp(252,7) name = items type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${yplblist}", java.lang.Object.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
    // /WEB-INF/jsp/business/ypml/exportYpxx.jsp(252,7) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVar("value");
    int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
      if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t\t<option value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${value.id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write('"');
          out.write('>');
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${value.info}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</option>\r\n");
          out.write("\t\t\t\t\t\t\t");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (java.lang.Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f0.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f0);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fforEach_005f1(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f1 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f1.setParent(null);
    // /WEB-INF/jsp/business/ypml/exportYpxx.jsp(262,7) name = items type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f1.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${jyztlist}", java.lang.Object.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
    // /WEB-INF/jsp/business/ypml/exportYpxx.jsp(262,7) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f1.setVar("value");
    int[] _jspx_push_body_count_c_005fforEach_005f1 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f1 = _jspx_th_c_005fforEach_005f1.doStartTag();
      if (_jspx_eval_c_005fforEach_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t\t<option value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${value.dictcode}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write('"');
          out.write('>');
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${value.info}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</option>\r\n");
          out.write("\t\t\t\t\t\t\t");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f1.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (java.lang.Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f1[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f1.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f1.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f1);
    }
    return false;
  }
}
