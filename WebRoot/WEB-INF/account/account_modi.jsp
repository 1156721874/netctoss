<%@page pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
            var $reidcard = $("#reidcard").val();
            validaterecommender($reidcard);
            if($("#recommender_id").val()==null|$("#recommender_id").val()==""){
            $("#recommender_id").val("0");
          						  }
          					document.getElementById("updateform").submit();
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

            //显示修改密码的信息项
            function showPwd(chkObj) {
                if (chkObj.checked){
                    document.getElementById("divPwds").style.display = "block";
                }else{
                    document.getElementById("divPwds").style.display = "none";
            								}
            							}	
            
            function validatepassword(obj,id){
           					var p = $("#newpassword1").val();
           					if(p==obj){
           											$("#pwdinfo").text("两次密码相同");
           											$.post("updatepassword.action",{"id":id,"pwd":obj},function(data){
																				if(data){
																				$("#pwdinfo").text("---修改成功---");
																				window.setTimeout(function(){document.getElementById("divPwds").style.display = "none";},2000);
																				}else{
																				$("#pwdinfo").text("---修改失败---");
																				}           											
           											});
           											
           									}else{
           									$("#pwdinfo").text("两次密码不相同");
           									}
           									}
           									
           	function	 checkpwd(obj,id) {
           	
           								$.post("checkaccountpwd.action",{"id":id},function(data){
           													if(data==obj){
           														$("#oldpwdinfo").text("密码正确");
           																		}else{
           														$("#oldpwdinfo").text("密码不正确");
           																		}
           								});
           								
           						}
           						
           		function validaterecommender(idcard){
	           	// var idcard = obj.value;
									$.post("validateRecommender.action",{"reidcard":idcard},function(data){
										if(data>0){
										$("#idcardinfo").text("ok");
										$("#recommender_id").val(data);
										}else{
										$("#idcardinfo").text("error");
										}
									});
									}
									
									function checkname(name){
										var reg = /^[0-9A-Za-z\u0391-\uFFE5]{1,20}$/;
										if(reg.test(name)){
											$("#nameinfo").text("填写正确");
										}else{
											$("#nameinfo").text("填写错误");
										}
									}
									
									function checktelnum(telnum){
									var reg = /^[0-9]{11}|([0-9]{1,n})$/g;
										if(reg.test(telnum)){
											$("#telinfo").text("填写正确");
										}else{
											$("#telinfo").text("填写错误");
										}
									}
        </script>
    </head>
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
            <div id="save_result_info" class="save_fail">保存失败，旧密码错误！</div>
            <form action="updateAccount.action" method="post" class="main_form" id="updateform">
                    <!--必填项-->
                    <div class="text_info clearfix"><span>账务账号ID：</span></div>
                    <div class="input_info">
                        <%--<input type="text" value="10" readonly class="readonly" />
                        --%>
                        <s:hidden name="account.login_passwd"></s:hidden>
                        <s:hidden name="account.status"></s:hidden>
                        <s:textfield name="account.id" readonly="true" cssClass="readonly" ></s:textfield>
                    </div>
                    <div class="text_info clearfix"><span>姓名：</span></div>
                    <div class="input_info">
                        <%--<input type="text" value="张三" />
                        --%>
                        <s:textfield name="account.real_name" onblur="checkname(this.value);" ></s:textfield>
                        <span class="required">*</span>
                        <div class="validate_msg_long error_msg" id="nameinfo">20长度以内的汉字、字母和数字的组合</div>
                    </div>
                    <div class="text_info clearfix"><span>身份证：</span></div>
                    <div class="input_info"><%--
                        <input type="text" value="230198765432123456" readonly class="readonly" />
                    --%>
                     <s:textfield name="account.idcard_no" readonly="true" cssClass="readonly"></s:textfield>
                    </div>
                    <div class="text_info clearfix"><span>登录账号：</span></div>
                    <div class="input_info">
                        <%--<input type="text" value="user1" readonly class="readonly"  />                        
                        --%>
                         <s:textfield name="account.login_name" readonly="true" cssClass="readonly"></s:textfield>
                        <div class="change_pwd">
                            <input id="chkModiPwd" type="checkbox" onclick="showPwd(this);" />
                            <label for="chkModiPwd">修改密码</label>
                        </div>
                    </div>
                    <!--修改密码部分-->
                    <div id="divPwds">
                        <div class="text_info clearfix"><span>旧密码：</span></div>
                        <div class="input_info">
                            <input type="password" name="password" onblur="checkpwd(this.value,<s:property value='account.id'/> );" />
                            <span class="required">*</span>
                            <div class="validate_msg_long" id="oldpwdinfo">30长度以内的字母、数字和下划线的组合</div>
                        </div>
                        <div class="text_info clearfix"><span>新密码：</span></div>
                        <div class="input_info">
                            <%--<input type="password"  />
                            --%>
                            <s:password name="newpassword1" id="newpassword1"></s:password>
                            <span class="required">*</span>
                            <div class="validate_msg_long">30长度以内的字母、数字和下划线的组合</div>
                        </div>
                        <div class="text_info clearfix"><span>重复新密码：</span></div>
                        <div class="input_info">
                            <%--<input type="password"  />
                            --%>
                            <input type="password"  id="newpassword2" onblur="validatepassword(this.value,<s:property value='account.id'/>);"/>
                            <span class="required">*</span>
                            <div class="validate_msg_long" id="pwdinfo">两次密码必须相同</div>
                        </div>  
                    </div>                   
                    <div class="text_info clearfix"><span>电话：</span></div>
                    <div class="input_info">
                        <%--<input type="text" class="width200"/>
                        --%>
                        <s:textfield name="account.telephone"  cssClass="width200" onblur="checktelnum(this.value);"></s:textfield>
                        <span class="required">*</span>
                        <div class="validate_msg_medium error_msg" id ="telinfo">正确的电话号码格式：手机或固话</div>
                    </div>
                    <div class="text_info clearfix"><span>推荐人身份证号码：</span></div>
                    <div class="input_info">
                        <%--<input type="text"/>
                        --%>
                        <s:textfield name="reidcard" onblur="validaterecommender(this.value);" id="reidcard"></s:textfield>
                        <s:hidden name="account.recommender_id" id="recommender_id"></s:hidden>
                        <div class="validate_msg_long error_msgs" id="idcardinfo">正确的身份证号码格式</div>
                    </div>
                    <div class="text_info clearfix"><span>生日：</span></div>
                    <div class="input_info">
                    <s:date format="" name=""/>
                        <input type="text" value="<s:date format="yyyy/mm/dd" name="birthdate"/> " readonly class="readonly" /><%--
                    
                    <s:textfield name="birthdate" disabled="true" cssClass="readonly"></s:textfield>
                    --%></div>
                    <div class="text_info clearfix"><span>Email：</span></div>
                    <div class="input_info">
                        <%--<input type="text" class="width200"/>
                        --%>
                        <s:textfield name="account.email"  cssClass="width200"></s:textfield>
                        <div class="validate_msg_medium">50长度以内，合法的 Email 格式</div>
                    </div> 
                    <div class="text_info clearfix"><span>职业：</span></div>
                    <div class="input_info"><%--
                        <select>
                            <option>干部</option>
                            <option>学生</option>
                            <option>技术人员</option>
                            <option>其他</option>
                        </select>                        
                    --%>
                    <s:select list="#{'干部':'干部','学生':'学生','技术人员':'技术人员','其他':'其他'}" name="account.occupation"></s:select>
                    </div>
                    <div class="text_info clearfix"><span>性别：</span></div>
                    <div class="input_info fee_type"><%--
                        <input type="radio" name="radSex" checked="checked" id="female" onclick="feeTypeChange(1);" />
                        <label for="female">女</label>
                        <input type="radio" name="radSex" id="male" onclick="feeTypeChange(2);" />
                        <label for="male">男</label>
                        --%>
                        <s:radio name="account.gender" list="#{'1':'男','0':'女'}"   id="gender" ></s:radio>
                    </div> 
                    <div class="text_info clearfix"><span>通信地址：</span></div>
                    <div class="input_info"><%--
                        <input type="text" class="width350"/>
                        --%>
                        <s:textfield name="account.mailaddress" cssClass="width350" ></s:textfield>
                        <div class="validate_msg_tiny">50长度以内</div>
                    </div> 
                    <div class="text_info clearfix"><span>邮编：</span></div>
                    <div class="input_info">
                        <%--<input type="text"/>
                        --%>
                        <s:textfield name="account.zipcode"></s:textfield>
                        <div class="validate_msg_long">6位数字</div>
                    </div> 
                    <div class="text_info clearfix"><span>QQ：</span></div>
                    <div class="input_info">
                        <%--<input type="text"/>
                        --%>
                        <s:textfield name="account.qq" ></s:textfield>
                        <div class="validate_msg_long">5到13位数字</div>
                    </div>                
                    <!--操作按钮-->
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
