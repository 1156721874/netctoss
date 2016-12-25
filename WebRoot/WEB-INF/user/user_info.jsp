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
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>服务器资费管理系统</title>
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" />
        <script type="text/javascript" src="../js/jquery-1.4.3.js"></script>
		<script type="text/javascript" src="../js/common.js"></script> 
        <script language="javascript" type="text/javascript">
            //保存成功的提示信息
            function showResult() {
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
            $(function(){
	            $("#name").blur(function(){
	            	var reg = new RegExp("^[a-zA-Z0-9]{6,20}$");
	            	if(!reg.test(this.value)){
	            	 $("#nameinfo").css("display","block");
	            	}else{
	            	 $("#nameinfo").css("display","none");
	            	}
	            });
	            
	            $("#telephone").blur(function(){
	            	var reg = new RegExp("^[0-9]{11}$");
	            	if(!reg.test(this.value)){
	            	 $("#telephoneinfo").css("display","block");
	            	}else{
	            	 $("#telephoneinfo").css("display","none");
	            	}
	            });
	            
	            $("#email").blur(function(){
	            	var reg = new RegExp("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$");
	            	if(!reg.test(this.value)){
	            	 $("#emailinfo").css("display","block");
	            	}else{
	            	 $("#emailinfo").css("display","none");
	            	}
	            });
	            
	            
            });
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
				    <li><a href="../admin/findAdminById.action" class="information_on"></a></li>
				    <li><a href="../admin/tomodpasswd.action" class="password_off"></a></li>
            </ul>            
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">            
            <!--保存操作后的提示信息：成功或者失败-->
            <div id="save_result_info" class="save_success">保存成功！</div><!--保存失败，数据并发错误！-->
            <form action="updateuser.action" method="post" class="main_form">
            <s:hidden name="admin.id"></s:hidden>
                <div class="text_info clearfix"><span>管理员账号：</span></div>
                <div class="input_info">
                <%-- <input type="text" name="admin.adminCode" readonly="readonly" class="readonly" value="<s:property value='admin.adminCode'/> " /> --%>
                <s:textfield name="admin.adminCode" readonly="true" cssClass="readonly"></s:textfield>
                </div>
                <div class="text_info clearfix"><span>角色：</span></div>
                <div class="input_info">
                    <%-- <input type="text" readonly="readonly" class="readonly width400" value="<s:property value='admin.rolenames'/> " /> --%>
                    <s:textfield name="admin.rolenames" readonly="true" cssClass="readonly width400" ></s:textfield>
                </div>
                <div class="text_info clearfix"><span>姓名：</span></div>
                <div class="input_info">
                    <%-- <input type="text" value="<s:property value='admin.name'/>" /> --%>
                    <s:textfield name="admin.name" id="name"></s:textfield>
                    <span class="required">*</span>
                    <div class="validate_msg_long error_msg" id="nameinfo">20长度以内的汉字、字母的组合</div>
                </div>
                <div class="text_info clearfix"><span>电话：</span></div>
                <div class="input_info">
                    <%-- <input type="text" class="width200" value="<s:property value='admin.telephone'/>" /> --%>
                    <s:textfield name="admin.telephone" cssClass="width200" id="telephone"></s:textfield>
                    <div class="validate_msg_medium" id="telephoneinfo">电话号码格式：手机或固话</div>
                </div>
                <div class="text_info clearfix"><span>Email：</span></div>
                <div class="input_info">
                    <%-- <input type="text" class="width200" value="<s:property value='admin.email'/>" /> --%>
                    <s:textfield name="admin.email" cssClass="width200" id="email"></s:textfield>
                    <div class="validate_msg_medium" id="emailinfo">50长度以内，符合 email 格式</div>
                </div>
                <div class="text_info clearfix"><span>创建时间：</span></div>
                <div class="input_info">
                <%-- <input type="text" readonly="readonly" class="readonly" value="<s:date name='admin.enrollDate' format='yyyy-MM-dd'/>"/> --%>
                <s:textfield name="admin.enrollDate" readonly="true" cssClass="readonly"></s:textfield>
                </div>
                <div class="button_info clearfix">
                    <input type="button" value="保存" class="btn_save" onclick="showResult();" />
                    <input type="button" value="取消" class="btn_save" onclick="window.history.go(-1);" />
                </div>
            </form>  
        </div>
        <!--主要区域结束-->
          <div id="footer">
  			<span>齐鲁工业大学。二零一四届毕业生课程设计。基于Java的电信行业计费系统的设计与实现</span>
        </div>
    </body>
</html>
