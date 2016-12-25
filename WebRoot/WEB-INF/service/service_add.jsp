<%@page pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@page import="java.util.*"%>
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
                showResultDiv(true);
                window.setTimeout("showResultDiv(false);", 3000);
                document.getElementById("serviceform").submit();
            }
            function showResultDiv(flag) {
                var divResult = document.getElementById("save_result_info");
                if (flag)
                    divResult.style.display = "block";
                else
                    divResult.style.display = "none";
            }

            //自动查询账务账号
            function searchAccounts(txtObj) {
                //document.getElementById("a1").innerHTML = txtObj.value;
            }
            function checkidcard(){
				            var $accountidcardno = $("#accountidcardno").val();
				            var $accountnum = $("#accountnum");
				            var $accountid = $("#accountid");
				            if($accountidcardno==null||$accountidcardno==""){$("#idcardmsg").text("没有此身份证号，请重新录入。");}
				            $.post(
				           							 "checkidcard.action",
				           							 {"idcard":$accountidcardno},
				           		function(data){
				           							 if((data!=null&&data!=""&&data!="undefined"&&data!="null")&&data.idcard_no==$accountidcardno){
				           							 					 	alert(data);
				           							 						$accountid.val(data.id);
				           							 						$accountnum.val(data.login_name);
				           							 						$("#idcardmsg").text("存在此身份证号，录入正确。");
				           							 					} else {
				           							 						$accountid.val("");
				           							 						$accountnum.val("");
				           							 						$("#idcardmsg").text("不存在此身份证号，请重新录入。");
				           							 					}
				           							 }
				            					);
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
                <li><a href="../account/findaccount.action" class="account_off"></a></li>
                <li><a href="../service/findService.action" class="service_on"></a></li>
                <li><a href="../serverconfig/toconfiginfo.action" class="bill_off"></a>
               	<li><a href="../privilege/findPrivilege.action" class="report_off"></a></li>
				<li><a href="../admin/findAdminById.action" class="information_off"></a></li>
				<li><a href="../admin/tomodpasswd.action" class="password_off"></a></li>
            </ul>
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">
            <!--保存操作的提示信息-->
            <div id="save_result_info" class="save_fail">保存失败！192.168.0.23服务器上已经开通过 OS 账号 “mary”。</div>
            <form action="addService.action" method="post" class="main_form" id="serviceform">
                <!--内容项-->
                <div class="text_info clearfix"><span>身份证：</span></div>
                <div class="input_info">
                    <input type="text" value="查询出的结果写入账务账号" class="width180" id="accountidcardno" />
                    <input type="hidden" name="service.account_id" id="accountid" />
                    <input type="button" value="查询账务账号" class="btn_search_large" onclick="checkidcard();" />
                    <span class="required">*</span>
                    <div class="validate_msg_short" id="idcardmsg"></div>
                </div>
                <div class="text_info clearfix"><span>账务账号：</span></div>
                <div class="input_info">
                    <input type="text" id="accountnum" readonly="readonly" onkeyup="searchAccounts(this);" />
                    <span class="required">*</span>
                    <div class="validate_msg_long"></div>
                </div>
                <div class="text_info clearfix"><span>资费类型：</span></div>
                <div class="input_info"><%--
                    <select>
                        <option>包 20 小时</option>
                        <option>包 40 小时</option>
                        <option>包 60 小时</option>
                        <option>包月</option>
                    </select>                        
                --%>
                <s:select list="costs" listKey="id" listValue="name" name="service.cost_id"></s:select>
                </div> 
                <div class="text_info clearfix"><span>服务器 IP：</span></div>
                <div class="input_info">
                    <input type="text" name="service.unix_host"  />
                    
                    <span class="required">*</span>
                    <div class="validate_msg_long">15 长度，符合IP的地址规范</div>
                </div>                   
                <div class="text_info clearfix"><span>登录 OS 账号：</span></div>
                <div class="input_info"> 
                    <input type="text" value="创建即启用，状态为开通"   name="service.os_username"/>
                    <span class="required">*</span>
                    <div class="validate_msg_long">8长度以内的字母、数字和下划线的组合</div>
                </div>
                <div class="text_info clearfix"><span>密码：</span></div>
                <div class="input_info">
                    <input type="password"/>
                    <span class="required">*</span>
                    <div class="validate_msg_long">30长度以内的字母、数字和下划线的组合</div>
                </div>
                <div class="text_info clearfix"><span>重复密码：</span></div>
                <div class="input_info">
                    <input type="password" name="service.login_passwd"/>
                    <span class="required">*</span>
                    <div class="validate_msg_long">两次密码必须相同</div>
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
