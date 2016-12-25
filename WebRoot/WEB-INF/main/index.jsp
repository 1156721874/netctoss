<%@page pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>服务器资费管理系统</title>
<link type="text/css" rel="stylesheet" media="all"
	href="../styles/global.css" />
<link type="text/css" rel="stylesheet" media="all"
	href="../styles/global_color.css" />
<SCRIPT src="${pageContext.servletContext.contextPath }/js/Clock.js" type=text/javascript></SCRIPT>
</head>
<body class="index">
<div>
<center style="color: white;font-weight: 800;font-size: 30px;"  >尊贵的：
	<span style="color: yellow;font-weight: 800;">
	${sessionScope.admin.adminCode}
	</span>　　您好， 欢迎进入后台管理系统
</center>
	
<dir style="color: white;font-size: 20px; text-align: center; ">
 　　现在的时间是:<span class="STYLE7"><b><span id=clock style="color:yellow;" ></span></b>
 <SCRIPT type=text/javascript>
    var clock = new Clock();
    clock.display(document.getElementById("clock"));
</SCRIPT>
</span> 
</dir>　
</div>
<div style="padding: 8px;background-color: ;" align="center">
<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0" width="690" height="200">
  <param name="movie" value="${pageContext.servletContext.contextPath }/js/20101262241501.swf" />
  <param name="quality" value="high" />
  <embed src="${pageContext.servletContext.contextPath }/js/20101262241501.swf" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash"width="690" height="200"></embed>
</object>

</div>
			<!--导航区域开始-->
			<div id="index_navi">
				<ul id="menu">
					<li><a href="toindex.action" class="index_on"></a></li>
					<li><a href="../role/toRole.action" class="role_off"></a></li>
					<li><a href="../admin/findAdmin.action" class="admin_off"></a></li>
					<li><a href="../cost/findCost.action" class="fee_off"></a></li>
					<li><a href="../account/findaccount.action"class="account_off"></a></li>
					<li><a href="../service/findService.action"class="service_off"></a></li>
					<li><a href="../serverconfig/toconfiginfo.action" class="bill_off"></a></li>
					<li><a href="../privilege/findPrivilege.action" class="report_off"></a></li>
					<li><a href="../admin/findAdminById.action" class="information_off"></a></li>
					<li><a href="../admin/tomodpasswd.action" class="password_off"></a></li>
				</ul>
			</div>
</body>
</html>