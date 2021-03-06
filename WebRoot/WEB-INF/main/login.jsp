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
<SCRIPT src="${pageContext.servletContext.contextPath }/js/jquery-1.3.2.min.js" type=text/javascript></SCRIPT>
<SCRIPT src="${pageContext.servletContext.contextPath }/js/jquery.md5.js" type=text/javascript></SCRIPT>
<script language="javascript" type="text/javascript">
	function changeimg(imgObj) {
		imgObj.src = "validateCode.action?data=" + new Date().getTime();
	}
	function parsemd5() {
		var admincode = document.getElementsByName("admin.adminCode")[0].value;
		if(admincode==""){
			document.getElementsByTagName("span")[0].innerHTML="用户名不能为空";
			return;
		}else if(admincode.length>30){
		    document.getElementsByTagName("span")[0].innerHTML="30长度的字母、数字和下划线";
			return;
		}
		var adminpwd = document.getElementsByName("admin.password")[0].value;
		if(adminpwd==""){
			document.getElementsByTagName("span")[1].innerHTML="密码不能为空";
			return;
		}else if(adminpwd.length>30){
		    document.getElementsByTagName("span")[1].innerHTML="30长度的字母、数字和下划线";
			return;
		}
		
		var password = document.getElementsByName("admin.password")[0].value;
		var md5 = $.md5(password);
		var passwordArray = new Array();
		var p = 0;
		var m = 0;
		var passwordlen = password.length;
		for(var i=0;i<(md5.length)+(password.length);i++){
			if(i==0){
				passwordArray[i] = md5.charAt(m);
				m++;
				continue;
			}
			if(i%2==0&&m<md5.length){
			 	 passwordArray[i] = md5.charAt(m);
			     m++;	
			}else if(p<password.length){
			    passwordArray[i] = password.charAt(p);
			    p++;
			}else{
			     passwordArray[i] = md5.charAt(m);
			     m++;
			}
		}
		//alert("password is: "+password+" "+password.length+"\n"+"md5 password is : "+md5+" "+md5.length+"\n"+"merge password is : "+passwordArray.toString()+" "+passwordArray.length);
		password = passwordArray.toString().replace(/,/g, "");
		//alert(password);
		document.getElementsByName("admin.password")[0].value = password+"@"+passwordlen;
		document.getElementById('loginForm').submit();
	}
</script>
</head>
<body class="index">
	<div class="login_box">
		<form action="login.action" method="post" id="loginForm">
			<table>
				<tr>
					<td class="login_info">账号：</td>
					<td colspan="2"><input name="admin.adminCode" type="text"
						class="width150" /></td>
					<td class="login_error_info"><span class="required"></span>
					</td>
				</tr>
				<tr>
					<td class="login_info">密码：</td>
					<td colspan="2"><input name="admin.password" type="password"
						class="width150" /></td>
					<td><span class="required"></span></td>
				</tr>
				<tr>
					<td class="login_info">验证码：</td>
					<td class="width70"><input name="validateCode" type="text"
						class="width70" /></td>
					<td><img src="validateCode.action" alt="验证码" title="点击更换"
						onclick="changeimg(this);" /></td>
					<td><span class="required">单击切换</span></td>
				</tr>
				<tr>
					<td></td>
					<td class="login_button" colspan="2"><a
						href="javascript:parsemd5();"><img
							src="../images/login_btn.png" /> </a>
					</td>
					<td><span class="required">${errorMsg}</span></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
