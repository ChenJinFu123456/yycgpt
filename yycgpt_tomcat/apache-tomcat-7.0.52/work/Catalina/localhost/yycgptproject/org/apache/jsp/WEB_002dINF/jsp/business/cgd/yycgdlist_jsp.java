/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.52
 * Generated at: 2018-01-06 15:36:17 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp.business.cgd;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class yycgdlist_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<html> \r\n");
      out.write("<head>\r\n");
      out.write("<title>医院采购单维护</title>\r\n");
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
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("function yycgdedit(bm){\r\n");
      out.write("\tvar sendUrl = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${baseurl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("cgd/editcgd.action?id=\"+bm;\r\n");
      out.write("\tparent.opentabwindow(bm+'采购单修改',sendUrl);//打开一个新标签\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("//工具栏\r\n");
      out.write("\r\n");
      out.write("var toolbar = [];\r\n");
      out.write("\r\n");
      out.write("var frozenColumns;\r\n");
      out.write("\r\n");
      out.write("var columns = [ [\r\n");
      out.write(" \r\n");
      out.write(" {\r\n");
      out.write("\tfield : 'useryymc',\r\n");
      out.write("\ttitle : '医院名称',\r\n");
      out.write("\twidth : 100\r\n");
      out.write("},{\r\n");
      out.write("\tfield : 'bm',\r\n");
      out.write("\ttitle : '采购单编号',\r\n");
      out.write("\twidth : 80\r\n");
      out.write("},{\r\n");
      out.write("\tfield : 'mc',\r\n");
      out.write("\ttitle : '采购单名称',\r\n");
      out.write("\twidth : 150\r\n");
      out.write("},{\r\n");
      out.write("\tfield : 'cjtime',\r\n");
      out.write("\ttitle : '建单时间',\r\n");
      out.write("\twidth : 80,\r\n");
      out.write("\tformatter: function(value,row,index){\r\n");
      out.write("\t\tif(value){\r\n");
      out.write("\t\t\ttry{\r\n");
      out.write("\t\t\t//通过js日期格式化\r\n");
      out.write("\t\t\tvar date = new Date(value);\r\n");
      out.write("\t\t\tvar y = date.getFullYear();//获取年\r\n");
      out.write("\t\t\tvar m = date.getMonth()+1;//获取月\r\n");
      out.write("\t\t\tvar d = date.getDate();\r\n");
      out.write("\t\t\treturn y+\"-\"+m+\"-\"+d;\r\n");
      out.write("\t\t\t}catch(e){\r\n");
      out.write("\t\t\t\talert(e);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t}\r\n");
      out.write("},{\r\n");
      out.write("\tfield : 'xgtime',\r\n");
      out.write("\ttitle : '修改时间',\r\n");
      out.write("\twidth : 80,\r\n");
      out.write("\tformatter: function(value,row,index){\r\n");
      out.write("\t\tif(value){\r\n");
      out.write("\t\t\ttry{\r\n");
      out.write("\t\t\tvar date = new Date(value);\r\n");
      out.write("\t\t\tvar y = date.getFullYear();\r\n");
      out.write("\t\t\tvar m = date.getMonth()+1;\r\n");
      out.write("\t\t\tvar d = date.getDate();\r\n");
      out.write("\t\t\treturn y+\"-\"+m+\"-\"+d;\r\n");
      out.write("\t\t\t}catch(e){\r\n");
      out.write("\t\t\t\talert(e);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t}\r\n");
      out.write("},{\r\n");
      out.write("\tfield : 'tjtime',\r\n");
      out.write("\ttitle : '提交时间',\r\n");
      out.write("\twidth : 80,\r\n");
      out.write("\tformatter: function(value,row,index){\r\n");
      out.write("\t\tif(value){\r\n");
      out.write("\t\t\ttry{\r\n");
      out.write("\t\t\tvar date = new Date(value);\r\n");
      out.write("\t\t\tvar y = date.getFullYear();\r\n");
      out.write("\t\t\tvar m = date.getMonth()+1;\r\n");
      out.write("\t\t\tvar d = date.getDate();\r\n");
      out.write("\t\t\treturn y+\"-\"+m+\"-\"+d;\r\n");
      out.write("\t\t\t}catch(e){\r\n");
      out.write("\t\t\t\talert(e);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t}\r\n");
      out.write("},{\r\n");
      out.write("\tfield : 'shtime',\r\n");
      out.write("\ttitle : '审核时间',\r\n");
      out.write("\twidth : 80,\r\n");
      out.write("\tformatter: function(value,row,index){\r\n");
      out.write("\t\tif(value){\r\n");
      out.write("\t\t\ttry{\r\n");
      out.write("\t\t\tvar date = new Date(value);\r\n");
      out.write("\t\t\tvar y = date.getFullYear();\r\n");
      out.write("\t\t\tvar m = date.getMonth()+1;\r\n");
      out.write("\t\t\tvar d = date.getDate();\r\n");
      out.write("\t\t\treturn y+\"-\"+m+\"-\"+d;\r\n");
      out.write("\t\t\t}catch(e){\r\n");
      out.write("\t\t\t\talert(e);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t}\r\n");
      out.write("},{\r\n");
      out.write("\tfield : 'yycgdztmc',\r\n");
      out.write("\ttitle : '采购单<br>状态', \r\n");
      out.write("\twidth : 60\r\n");
      out.write("},{\r\n");
      out.write("\tfield : 'opt3',\r\n");
      out.write("\ttitle : '修改',\r\n");
      out.write("\twidth : 60,\r\n");
      out.write("\tformatter:function(value, row, index){\r\n");
      out.write("\t\treturn '<a href=javascript:yycgdedit(\"'+row.bm+'\")>修改</a>';\r\n");
      out.write("\t}\r\n");
      out.write("}]];\r\n");
      out.write("\r\n");
      out.write("function initGrid(){\r\n");
      out.write("\t$('#yycgdlist').datagrid({\r\n");
      out.write("\t\ttitle : '采购单列表',\r\n");
      out.write("\t\t//nowrap : false,\r\n");
      out.write("\t\tstriped : true,\r\n");
      out.write("\t\t//collapsible : true,\r\n");
      out.write("\t\turl : '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${baseurl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("cgd/yycgdlist_result.action',\r\n");
      out.write("\t\tqueryParams:{//查询参数，只在加载时使用，点击查询使用load重新加载不使用此参数\r\n");
      out.write("\t\t\tyear:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${year}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("'\r\n");
      out.write("\t\t}, \r\n");
      out.write("\t\t//sortName : 'code',\r\n");
      out.write("\t\t//sortOrder : 'desc',\r\n");
      out.write("\t\t//remoteSort : false, \r\n");
      out.write("\t\tidField : 'id',//查询结果集主键采购单id\r\n");
      out.write("\t\t//frozenColumns : frozenColumns,\r\n");
      out.write("\t\tcolumns : columns,\r\n");
      out.write("\t\tautoRowHeight:true,\r\n");
      out.write("\t\tpagination : true,\r\n");
      out.write("\t\trownumbers : true,\r\n");
      out.write("\t\ttoolbar : toolbar,\r\n");
      out.write("\t\tloadMsg:\"\",\r\n");
      out.write("\t\tpageList:[15,30,50,100],\r\n");
      out.write("\t\tonClickRow : function(index, field, value) {\r\n");
      out.write("\t\t\t\t\t$('#yycgdlist').datagrid('unselectRow', index);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\r\n");
      out.write("\t}\r\n");
      out.write("\t$(function() {\r\n");
      out.write("\t\tinitGrid();\r\n");
      out.write("\t});\r\n");
      out.write("\r\n");
      out.write("\tfunction yycgdquery() {\r\n");
      out.write("\t\tvar formdata = $(\"#yycgdqueryForm\").serializeJson();//将form中的input数据取出来\r\n");
      out.write("\t\t//alert(formdata);\r\n");
      out.write("\t\t$('#yycgdlist').datagrid('load', formdata);\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("\t$(function(){\r\n");
      out.write("\t\t//加载采购单状态\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t/* getDictinfoCodelist('010','yycgdCustom.zt');\r\n");
      out.write("\t\t//加载年\r\n");
      out.write("\t\tbusinessyearlist('year'); */\r\n");
      out.write("\t\r\n");
      out.write("\r\n");
      out.write("\t});\r\n");
      out.write("\t//调用的是dwrService中的testdwr方法\r\n");
      out.write("\t/* dwrService.testdwr({\r\n");
      out.write("\t     callback:function(data) {\r\n");
      out.write("\t     \talert(data);\r\n");
      out.write("\t     }}); */\r\n");
      out.write("\t//调用的是dwrService中的testdwr2方法，传一个参数\r\n");
      out.write("\t/* dwrService.testdwr2('张三',{\r\n");
      out.write("\t     callback:function(data) {\r\n");
      out.write("\t     \talert(data);\r\n");
      out.write("\t     }}); */\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("</HEAD>\r\n");
      out.write("<BODY>\r\n");
      out.write("    <form id=\"yycgdqueryForm\" name=\"yycgdqueryForm\" method=\"post\" >\r\n");
      out.write("\t\t\t<TABLE  class=\"table_search\">\r\n");
      out.write("\t\t\t\t<TBODY>\r\n");
      out.write("\t\t\t\t\t<TR>\r\n");
      out.write("\t\t\t\t\t\t<TD class=\"left\">年份(如2017)：</TD>\r\n");
      out.write("\t\t\t\t\t\t<td >\r\n");
      out.write("\t\t\t\t\t\t<select name=\"year\" id=\"year\">\r\n");
      out.write("\t\t\t\t\t\t  <option value=\"2017\">2017</option>\r\n");
      out.write("\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t<TD class=\"left\">采购(建单)时间：</TD>\r\n");
      out.write("\t\t\t\t\t\t<td >\r\n");
      out.write("\t\t\t\t\t\t <INPUT id=\"yycgdCustom.cjtime_start\"\r\n");
      out.write("\t\t\t\t\t\t\tname=\"yycgdCustom.cjtime_start\" \r\n");
      out.write("\t\t\t\t\t\t\t onfocus=\"WdatePicker({isShowWeek:false,skin:'whyGreen',dateFmt:'yyyy-MM-dd'})\" style=\"width:80px\"/>--\r\n");
      out.write("\t\t\t\t\t <INPUT id=\"yycgdCustom.cjtime_end\" \r\n");
      out.write("\t\t\t\t\t\t\tname=\"yycgdCustom.cjtime_end\"\r\n");
      out.write("\t\t\t\t\t\t\t onfocus=\"WdatePicker({isShowWeek:false,skin:'whyGreen',dateFmt:'yyyy-MM-dd'})\" style=\"width:80px\"/>\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t<TD class=\"left\">医院名称：</TD>\r\n");
      out.write("\t\t\t\t\t\t<td ><INPUT type=\"text\" name=\"yycgdCustom.useryymc\" /></td>\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t</TR>\r\n");
      out.write("\t\t\t\t\t<TR> \r\n");
      out.write("\t\t\t\t\t<TD class=\"left\">采购单编号：</td>\r\n");
      out.write("\t\t\t\t\t\t<td><INPUT type=\"text\"  name=\"yycgdCustom.bm\" /></TD>\r\n");
      out.write("\t\t\t\t\t<TD class=\"left\">采购单名称：</TD>\r\n");
      out.write("\t\t\t\t\t\t<td ><INPUT type=\"text\" name=\"yycgdCustom.mc\" /></td>\r\n");
      out.write("\t\t\t\t\t  <TD class=\"left\">采购单状态：</TD>\r\n");
      out.write("\t\t\t\t\t\t<td >\r\n");
      out.write("\t\t\t\t\t\t\t<select id=\"yycgdCustom.zt\" name=\"yycgdCustom.zt\" style=\"width:150px\">\r\n");
      out.write("\t\t\t\t\t\t\t    <option value=\"\">全部</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t\t\t<a id=\"btn\" href=\"#\" onclick=\"yycgdquery()\" class=\"easyui-linkbutton\" iconCls='icon-search'>查询</a>\t\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t</TBODY>\r\n");
      out.write("\t\t\t</TABLE>\r\n");
      out.write("\t   \r\n");
      out.write("\t\t<TABLE border=0 cellSpacing=0 cellPadding=0 width=\"99%\" align=center>\r\n");
      out.write("\t\t\t<TBODY>\r\n");
      out.write("\t\t\t\t<TR>\r\n");
      out.write("\t\t\t\t\t<TD>\r\n");
      out.write("\t\t\t\t\t\t<table id=\"yycgdlist\"></table>\r\n");
      out.write("\t\t\t\t\t</TD>\r\n");
      out.write("\t\t\t\t</TR>\r\n");
      out.write("\t\t\t</TBODY>\r\n");
      out.write("\t\t</TABLE>\r\n");
      out.write("\t\t </form>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</BODY>\r\n");
      out.write("</HTML>\r\n");
      out.write("\r\n");
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
    // /WEB-INF/jsp/business/cgd/yycgdlist.jsp(228,8) name = items type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cgdztlist}", java.lang.Object.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
    // /WEB-INF/jsp/business/cgd/yycgdlist.jsp(228,8) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVar("value");
    int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
      if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t\t\t<option value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${value.dictcode}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write('"');
          out.write('>');
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${value.info}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</option>\r\n");
          out.write("\t\t\t\t\t\t\t\t");
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
}