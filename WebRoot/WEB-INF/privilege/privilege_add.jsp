<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>服务器资费管理系统</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link type="text/css" rel="stylesheet" media="all" href="<%=basePath%>styles/global.css" />
<link type="text/css" rel="stylesheet" media="all" href="<%=basePath%>styles/global_color.css" />
<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.3.js"></script>
<script type="text/javascript" src="<%=basePath%>js/common.js"></script>
<script type="text/javascript">
	$(function(){
		   var count =0;
	$("#addbtn").click(function(){
	var input = $("<input type='text' name='privilege.strurls' style='margin-left: 64px' />");
	var button = $("<input type='button' value='remove"+ (++count) +"'/>");
	var br = $("<br>");
	$("#div1").append(input).append(button).append(br);
	button.click(function(){
					input.remove();
					button.remove();
					br.remove();
						 });
	});
});

function submitPrivilege(){
	document.privilegeform.submit();
	//window.opener.location.reload();
}

function checkIdExist(val){
if(""==val||null==val||"null"==val){
$("#idnotifyinfo").text("id不能为空！");
return;
}
$.post("privilege/checkIsExistId.action",{"id":val},function(data){
	   if(data=="0"){
		   $("#idnotifyinfo").text("Id重复！");
		}else{
		   $("#idnotifyinfo").text("可是使用");
		  }
	    }
     );
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
                 <li><a href="login.action" class="index_off"></a></li>
                <li><a href="role/toRole.action" class="role_off"></a></li>
                <li><a href="admin/findAdmin.action" class="admin_off"></a></li>
                <li><a href="cost/findCost.action" class="fee_off"></a></li>
                <li><a href="account/findaccount.action" class="account_off"></a></li>
                <li><a href="service/findService.action" class="service_off"></a></li>
               <li><a href="serverconfig/toconfiginfo.action" class="bill_off"></a>
               	<li><a href="privilege/findPrivilege.action" class="report_on"></a></li>
				<li><a href="admin/findAdminById.action" class="information_off"></a></li>
				<li><a href="admin/tomodpasswd.action" class="password_off"></a></li>
            </ul>            
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->        
        <div id="report_main">
        	<div class="tabs">
            </div>            
            <div class="report_box">
                <!--数据区域：用表格展示数据-->
                <div id="report_data" align="left" >
                    <form id="privilegeform" name="privilegeform" action="privilege/addPrivilege.action" method="post">
					<input type="button" value="增加一条" id="addbtn" class="btn_add" /><br>
					模块名称：
					<input name="privilege.name" type="text" />
					<br> 
					模块ID：&nbsp;&nbsp;&nbsp;&nbsp;<input name="privilege.id" onblur="checkIdExist(this.value);" /><span id="idnotifyinfo"></span><br>
					允许Url：
					<div id="div1" style="background-color: silver">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="privilege.strurls" type="text" /><br>
					</div>
					<input type="submit" name="tijiao" value="提交" onclick="submitPrivilege();" style='margin-left:120px'  />
					<input type="button" name="cancle" value="取消" onclick="window.history.go(-1);" style='margin-left:20px'  />
					</form>
                </div>
            </div>
        </div>
        <!--主要区域结束-->
               <div id="footer">
  			<span>齐鲁工业大学。二零一四届毕业生课程设计。基于Java的电信行业计费系统的设计与实现</span>
        </div>        

</body>
</html>
