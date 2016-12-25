<%@page pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
﻿
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>服务器资费管理系统</title>
		<link type="text/css" rel="stylesheet" media="all"
			href="../styles/global.css" />
		<link type="text/css" rel="stylesheet" media="all"
			href="../styles/global_color.css" />
		<script type="text/javascript" src="../js/jquery-1.4.3.js"></script>
			<script type="text/javascript" src="../js/common.js"></script>
		<script language="javascript" type="text/javascript">
            //保存成功的提示信息
            function showResult() {
			            if($("#recommender_id").val()==null|$("#recommender_id").val()==""){
			           		 $("#recommender_id").val("0");
          								  }
            			document.getElementById("addform").submit();
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
            //显示选填的信息项
            function showOptionalInfo(imgObj) {
                var div = document.getElementById("optionalInfo");
                if (div.className == "hide") {
                    div.className = "show";
                    imgObj.src = "../images/hide.png";
                							}
                else {
                    div.className = "hide";
                    imgObj.src = "../images/show.png";
                						}
           					 }
           					 //验证推荐人是否存在
           	function validaterecommender(obj){
	           	var idcard = obj.value;
									$.post("validateRecommender.action",{"reidcard":idcard},function(data){
										if(data>0){
										$("#idcardinfo").text("ok");
										$("#recommender_id").val(data);
										}else{
										$("#idcardinfo").text("错误，推荐人不存在");
										}
									});
           					}
           					//验证身份证号码并生成生日
           					function validateIDCard(obj){
           					var idcard = obj.value;
           					var reg = /^[0-9]{17}[\d|\w]{1}$/;
           					if(reg.test(idcard)){
           									$(obj).next("span").next("div").text("ok");
													   	$("#birthdate").val(idcard.slice(6,10)+"-"+idcard.slice(10,12)+"-"+idcard.slice(12,14));
           										}else{
           										$(obj).next("span").next("div").text("身份证号码格式错误（18位）");
           										$(obj).next("span").next("div").css("error_msg");
           										}
           										
           					}
           					//验证修改密码时，2次密码输入是否正确
           					function validatepassword(obj){
           					var p = $("#password").val();
           					if(p==obj.value){
           											$("#pwdinfo").text("两次密码必须相同");
           									}else{
           									$("#pwdinfo").css("error_msg");
           									$("#pwdinfo").text("两次密码不相同！！");
           									}
           					}
           					//真实姓名验证
           					function checkAccountRealName(obj){
           					var reg = /[0-9A-Za-z\u0391-\uFFE5]/;
           					if(obj==null||obj==""){$("#realnamemsg").text("输入不能为空");return ;}
           					$.post("findAccountByRealName.action",{"realname":obj},function(data){
           							if(data&&reg.test(obj)){
           													$("#realnamemsg").text("输入正确");
           												}else{
           												$("#realnamemsg").css("error_msg");
           												$("#realnamemsg").text("输入错误");
           												}
           										});
           					}
           				function	checktelephone(obj){
           				var reg = /[0-9]{11}/;
           				if(reg.test(obj.value)){
           									$("#telephonemsg").text("输入正确");
           									}else{$("#telephonemsg").text("输入错误");}
           				}
        </script>
	</head>
	<body>
		<!--Logo区域开始-->
		<div id="header">
			<img src="../images/logo.png" alt="logo" class="left" />
			<a href="../login/toLogin">[退出]</a>
			 <a onclick="show2Dcode();" > <img src="../images/ZxingImage/a.png" alt="得到二维码" width="50" height="50" title="得到二维码"  />  </a> 
		</div>
		<!--Logo区域结束-->
		<!--导航区域开始-->
		<div id="navi">
			<ul id="menu">
				<li><a href="login.action" class="index_off"></a></li>
                <li><a href="../role/toRole.action" class="role_off"></a></li>
                <li><a href="../admin/findAdmin.action" class="admin_off"></a></li>
                <li><a href="../cost/findCost.action" class="fee_off"></a></li>
                <li><a href="../account/findaccount.action" class="account_on"></a></li>
                <li><a href="../service/findService.action" class="service_off"></a></li>
               <li><a href="../serverconfig/toconfiginfo.action" class="bill_off"></a>
               	<li><a href="../privilege/findPrivilege.action" class="report_off"></a></li>
				<li><a href="../admin/findAdminById.action" class="information_off"></a></li>
				<li><a href="../admin/tomodpasswd.action" class="password_off"></a></li>
			</ul>
		</div>
		<!--导航区域结束-->
		<!--主要区域开始-->
		<div id="main">
			<!--保存成功或者失败的提示消息-->
			<div id="save_result_info" class="save_fail">
				保存失败，该身份证已经开通过账务账号！
			</div>
			<form action="addaccount.action" method="post" class="main_form"
				id="addform">
				<!--必填项-->
				<div class="text_info clearfix">
					<span>姓名：</span>
				</div>
				<div class="input_info">
					<input type="text"   name="account.real_name" onblur="checkAccountRealName(this.value);"/>
					<span class="required">*</span>
					<div class="validate_msg_long" id="realnamemsg">
						20长度以内的汉字、字母和数字的组合
					</div>
				</div>
				<div class="text_info clearfix">
					<span>身份证：</span>
				</div>
				<div class="input_info">
					<input type="text" 
						name="account.idcard_no" onblur="validateIDCard(this);" />
					<span class="required">*</span>
					<div class="validate_msg_long">
						正确的身份证号码格式
					</div>
				</div>
				<div class="text_info clearfix">
					<span>登录账号：</span>
				</div>
				<div class="input_info">
					<input type="text" value="创建即启用，状态为开通" name="account.login_name" />
					<span class="required">*</span>
					<div class="validate_msg_long">
						30长度以内的字母、数字和下划线的组合
					</div>
				</div>
				<div class="text_info clearfix">
					<span>密码：</span>
				</div>
				<div class="input_info">
					<input type="password" name="account.login_passwd" id="password" />
					<span class="required">*</span>
					<div class="validate_msg_long">
						30长度以内的字母、数字和下划线的组合
					</div>
				</div>
				<div class="text_info clearfix">
					<span>重复密码：</span>
				</div>
				<div class="input_info">
					<input type="password" onblur="validatepassword(this);" />
					<span class="required">*</span>
					<div class="validate_msg_long" id="pwdinfo">
						两次密码必须相同
					</div>
				</div>
				<div class="text_info clearfix">
					<span>电话：</span>
				</div>
				<div class="input_info">
					<input type="text" class="width200" name="account.telephone" onblur="checktelephone(this);" />
					<span class="required">*</span>
					<div class="validate_msg_medium" id="telephonemsg">
						正确的电话号码格式：手机或固话
					</div>
				</div>
				<!--可选项-->
				<div class="text_info clearfix">
					<span>可选项：</span>
				</div>
				<div class="input_info">
					<img src="../images/show.png" alt="展开"
						onclick="showOptionalInfo(this);" />
				</div>
				<div id="optionalInfo" class="hide">
					<div class="text_info clearfix">
						<span>推荐人身份证号码：</span>
					</div>
					<div class="input_info">
						<input type="text" name="recommender_idcard"
							onblur="validaterecommender(this);" />
							<input type="hidden" name="account.recommender_id" id="recommender_id"/>
						<div class="validate_msg_long" id="idcardinfo">
							正确的身份证号码格式
						</div>
					</div>
					<div class="text_info clearfix">
						<span>生日：</span>
					</div>
					<div class="input_info">
						<input type="text"  readonly class="readonly"
							name="account.birthdate" id="birthdate"  />
					</div>
					<div class="text_info clearfix">
						<span>Email：</span>
					</div>
					<div class="input_info">
						<input type="text" class="width350" name="account.email" />
						<div class="validate_msg_tiny">
							50长度以内，合法的 Email 格式
						</div>
					</div>
					<div class="text_info clearfix">
						<span>职业：</span>
					</div>
					<div class="input_info">
						<select name="account.occupation">
							<option>
								干部
							</option>
							<option>
								学生
							</option>
							<option>
								技术人员
							</option>
							<option>
								其他
							</option>
						</select>
					</div>
					<div class="text_info clearfix">
						<span>性别：</span>
					</div>
					<div class="input_info fee_type">
						<input type="radio" name="account.gender" value="0"
							checked="checked" id="female" />
						<label for="female">
							女
						</label>
						<input type="radio" name="account.gender" value="1" id="male" />
						<label for="male">
							男
						</label>
					</div>
					<div class="text_info clearfix">
						<span>通信地址：</span>
					</div>
					<div class="input_info">
						<input type="text" class="width350" name="account.mailaddress" />
						<div class="validate_msg_tiny">
							50长度以内
						</div>
					</div>
					<div class="text_info clearfix">
						<span>邮编：</span>
					</div>
					<div class="input_info">
						<input type="text" name="account.zipcode" />
						<div class="validate_msg_long">
							6位数字
						</div>
					</div>
					<div class="text_info clearfix">
						<span>QQ：</span>
					</div>
					<div class="input_info">
						<input type="text" name="account.qq" />
						<div class="validate_msg_long">
							5到13位数字
						</div>
					</div>
				</div>
				<!--操作按钮-->
				<div class="button_info clearfix">
					<input type="button" value="保存" class="btn_save"
						onclick="showResult();" />
					<input type="button" value="取消" class="btn_save" onclick="history.go(-1);" />
				</div>
			</form>
		</div>
		<!--主要区域结束-->
	        <div id="footer">
  			<span>齐鲁工业大学。二零一四届毕业生课程设计。基于Java的电信行业计费系统的设计与实现</span>
        </div>
	</body>
</html>
