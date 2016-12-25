<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>服务器资费管理系统</title>
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" />    
        <script type="text/javascript" src="../js/jquery-1.4.3.js"></script>
		<script type="text/javascript" src="../js/common.js"></script>     
		 <script language="javascript" type="text/javascript">
            //保存成功的提示信息
            function showResult() {
            var pw1 = document.getElementsByName("admin.newpassword")[0].value;
            var pw2 = document.getElementsByName("confirm")[0].value;
            
            if(pw1!=pw2||pw1==""||pw2==""){
	            $("#validate_msg_medium").css("display","block");
            	return;
            }else{
                $("#validate_msg_medium").css("display","none");
            }
            document.forms[0].submit();
                showResultDiv(true);
                window.setTimeout("showResultDiv(false);", 3000);
            }
            function showResultDiv(flag) {
                var divResult = document.getElementById("save_result_info");
                if (flag)
                    divResult.style.display = "block";
                else
                    divResult.style.display = "none";
            }
        </script>
    </head>
    <body>
        <!--Logo区域开始-->
        <div id="header">
         <img src="<%=basePath%>images/logo.png" alt="logo" class="left" />
			<a href="<%=basePath%>login/toLogin">[退出]</a>
			 <a onclick="show2Dcode();" > <img src="<%=basePath%>images/ZxingImage/a.png" alt="得到二维码" width="50" height="50" title="得到二维码"  />  </a>                  
        </div>
        <!--Logo区域结束-->
        <!--导航区域开始-->
        <div id="navi">
            <ul id="menu">
					<li><a href="toindex.action" class="index_off"></a></li>
					<li><a href="../role/toRole.action" class="role_off"></a></li>
					<li><a href="../admin/findAdmin.action" class="admin_off"></a></li>
					<li><a href="../cost/findCost.action" class="fee_off"></a></li>
					<li><a href="../account/findaccount.action"class="account_off"></a></li>
					<li><a href="../service/findService.action"class="service_off"></a></li>
					<li><a href="../serverconfig/toconfiginfo.action" class="bill_off"></a></li>
					<li><a href="../privilege/findPrivilege.action" class="report_off"></a></li>
					<li><a href="../admin/findAdminById.action" class="information_off"></a></li>
					<li><a href="../admin/tomodpasswd.action" class="password_on"></a></li>
            </ul>
        </div>
        <!--导航区域结束-->
        <div id="main">      
            <!--保存操作后的提示信息：成功或者失败-->      
            <div id="save_result_info" class="save_success">保存成功！</div><!--保存失败，旧密码错误！-->
            <form action="modpassword.action" method="post" class="main_form">
                <div class="text_info clearfix"><span>旧密码：</span></div>
                <div class="input_info">
                    <!-- <input type="password" class="width200"  /> -->
                    <s:password name="admin.password"></s:password>
                    <span class="required">*</span>
                    <div class="validate_msg_medium">30长度以内的字母、数字和下划线的组合</div>
                </div>
                <div class="text_info clearfix"><span>新密码：</span></div>
                <div class="input_info">
                    <!-- <input type="password"  class="width200" /> -->
                    <s:password name="admin.newpassword" ></s:password>
                    <span class="required">*</span>
                    <div class="validate_msg_medium">30长度以内的字母、数字和下划线的组合</div>
                </div>
                <div class="text_info clearfix"><span>重复新密码：</span></div>
                <div class="input_info">
                    <!-- <input type="password" class="width200"  /> -->
                    <s:password name="confirm"></s:password>
                    <span class="required">*</span>
                    <div id="validate_msg_medium" class="validate_msg_medium">两次新密码必须相同</div>
                </div>
                <div class="button_info clearfix">
                    <input type="button" value="保存" class="btn_save" onclick="showResult();" />
                    <input type="button" value="取消" class="btn_save" onclick="window.history.go(-1);"  />
                </div>
            </form>  
        </div>
        <!--主要区域结束-->
        <div id="footer">
  			<span>齐鲁工业大学。二零一四届毕业生课程设计。基于Java的电信行业计费系统的设计与实现</span>
        </div>
    </body>
</html>
