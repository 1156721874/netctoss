<%@page import="org.apache.struts2.components.Include"%>
<%@page pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@page import="java.util.*"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>服务器资费管理系统</title>
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" /> 
        	<script type="text/javascript" src="../js/common.js"></script><%--
        	<script type="text/javascript" src="../js/jquery.showLoading.js"></script>
    --%></head>
    <body>
        <!--Logo区域开始-->
        <div id="header">
            <img src="../images/logo.png" alt="logo" class="left"/>
            <a href="../login/toLogin">[退出]</a>   
             <a onclick="show2Dcode();" > <img src="../images/ZxingImage/a.png" alt="得到二维码" width="50" height="50" title="得到二维码"  />  </a>         
        </div>
        <!--Logo区域结束-->
        <!--导航区域开始-->
        <div id="navi">                        
            <ul id="menu">
                <li><a href="toindex.action" class="index_off"></a></li>
					<li><a href="../role/toRole.action" class="role_off"></a></li>
					<li><a href="../admin/findAdmin.action" class="admin_off"></a></li>
					<li><a href="../cost/findCost.action" class="fee_off"></a></li>
					<li><a href="../account/findaccount.action" class="account_off"></a></li>
					<li><a href="../service/findService.action" class="service_off"></a></li>
					<li><a href="../serverconfig/toconfiginfo.action" class="bill_on"></a></li>
	               	<li><a href="../privilege/findPrivilege.action" class="report_off"></a></li>
				    <li><a href="../admin/findAdminById.action" class="information_off"></a></li>
				    <li><a href="../admin/tomodpasswd.action" class="password_off"></a></li>
            </ul>            
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
           <iframe src="${pageContext.servletContext.contextPath }/serverconfig/toServerconfig.action" width="100%" height="500" scrolling="no">
           </iframe>
        <!--主要区域结束-->
          <div id="footer">
  			<span>齐鲁工业大学。二零一四届毕业生课程设计。基于Java的电信行业计费系统的设计与实现</span>
        </div>
    </body>
</html>
