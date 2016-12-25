<%@page pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@page import="java.util.*"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
		<head>
				<title>服务器资费管理系统</title>
				  <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" />
				<script language="javascript" type="text/javascript" src="../js/jquery-1.4.3.js" > </script>
					<script type="text/javascript" src="../js/common.js"></script>
				<script type="text/javascript">
				var nameFlag=false;
				$(function(){
					$("#costName").blur(function(){
							var name = $(this).val();
							if(name==null||name==""){
								$("#nameMsg").text("资费名称不能为空");
								$("#nameMsg").addClass("error_msg");
								nameFlag = false;
								return;
							}
							var $id = $("#costID").val()
							$.post("updateCostValidate.action",{"name":name,"id":$id},
							function(data){
								if(data){
									$("#nameMsg").text("50长度的字母、数字、汉字和下划线的组合");
									$("#nameMsg").removeClass("error_msg");
									nameFlag = true;
								}else{
									$("#nameMsg").text("资费名称不能重复");
									$("#nameMsg").addClass("error_msg");
									nameFlag = false;
								}
							}
							);
					});
					
					$("#baseDuration").blur(function(){
						var $baseDuration = $("#baseDuration").val();
						if($baseDuration<1||$baseDuration>600){
							$("#baseDuration").siblings("div").html("1-600之间的整数").addClass("error_msg");
						}else{
						$("#baseDuration").siblings("div").html("ok");
						$("#nameMsg").removeClass("error_msg");
						}
					});
					
					$("#baseCost").blur(function(){
						var $baseCost = $("#baseCost").val();
						if($baseCost<0||$baseCost>(99999.99+0)){
							$("#baseCost").siblings("div").html("0-99999.99之间的数值");
						}else{
						$("#baseCost").siblings("div").html("ok");
						}
					});
					
					
				});
				
				 //保存结果的提示
            function showResult() {
            	if(!nameFlag){
            		return;
            	}
            	document.getElementById("updateCostForm").submit();
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

            //切换资费类型
            function feeTypeChange(type) {
                var inputArray = document.getElementById("main").getElementsByTagName("input");
                if (type == 1) {
                    inputArray[4].readonly = true;
                    inputArray[4].value = "";
                    inputArray[4].className += " readonly";
                    inputArray[5].readonly = false;
                    inputArray[5].className = "width100";
                    inputArray[6].readonly = true;
                    inputArray[6].className += " readonly";
                    inputArray[6].value = "";
                }
                else if (type == 2) {
                    inputArray[4].readonly = false;
                    inputArray[4].className = "width100";
                    inputArray[5].readonly = false;
                    inputArray[5].className = "width100";
                    inputArray[6].readonly = false;
                    inputArray[6].className = "width100";
                }
                else if (type == 3) {
                    inputArray[4].readonly = true;
                    inputArray[4].value = "";
                    inputArray[4].className += " readonly";
                    inputArray[5].readonly = true;
                    inputArray[5].value = "";
                    inputArray[5].className += " readonly";
                    inputArray[6].readonly = false;
                    inputArray[6].className = "width100";
                }
            }
				</script>
		</head>
<body>
<s:debug/>
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
                <li><a href="../cost/findCost.action" class="fee_on"></a></li>
                <li><a href="../account/findaccount.action" class="account_off"></a></li>
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
            <div id="save_result_info" class="save_fail">保存失败，资费名称重复！</div>
            <form action="updateCost.action" method="post" id="updateCostForm" class="main_form">
            			<div class="text_info clearfix"><span>资费ID：</span></div>
                <div class="input_info">
                	<s:textfield name="cost.id" cssClass="readonly" readonly="true" id="costID"></s:textfield>
                </div>
                <div class="text_info clearfix"><span>资费名称：</span></div>
                <div class="input_info">
                    <s:textfield name="cost.name" cssClass="width300" id="costName"></s:textfield>
                    <span class="required">*</span>
                    <div class="validate_msg_short" id="nameMsg">50长度的字母、数字、汉字和下划线的组合</div>
                </div>
                <div class="text_info clearfix"><span>资费类型：</span></div>
                <div class="input_info fee_type">
                <!--
                    <input type="radio" name="radFeeType" id="monthly" onclick="feeTypeChange(1);" />
                    <label for="monthly">包月</label>
                    <input type="radio" name="radFeeType" checked="checked" id="package" onclick="feeTypeChange(2);" />
                    <label for="package">套餐</label>
                    <input type="radio" name="radFeeType" id="timeBased" onclick="feeTypeChange(3);" />
                    <label for="timeBased">计时</label>
                      -->
                <s:radio name="cost.costType"  list="#{'1':'包月','2':'套餐','3':'计时'}" onclick="feeTypeChange(this.value);" ></s:radio>
                </div>
                <div class="text_info clearfix"><span>基本时长：</span></div>
                <div class="input_info">
                    <s:textfield name="cost.baseDuration" cssClass="width100" id="baseDuration"></s:textfield>
                    <span class="info">小时</span>
                    <span class="required">*</span>
                    <div class="validate_msg_long">ok</div>
                </div>
                <div class="text_info clearfix"><span>基本费用：</span></div>
                <div class="input_info">
                    <s:textfield name="cost.baseCost" cssClass="width100" id="baseCost"></s:textfield>
                    <span class="info">元</span>
                    <span class="required">*</span>
                    <div class="validate_msg_long error_msg">ok</div>
                </div>
                <div class="text_info clearfix"><span>单位费用：</span></div>
                <div class="input_info">
                    <s:textfield name="cost.unitCost" cssClass="width100"></s:textfield>
                    <s:hidden name="cost.createTime"></s:hidden>
                    <s:hidden name="cost.startTime"></s:hidden>
                    <s:hidden name="cost.status"></s:hidden>
                    <span class="info">元/小时</span>
                    <span class="required">*</span>
                    <div class="validate_msg_long error_msg">0-99999.99之间的数值</div>
                </div>
                <div class="text_info clearfix"><span>资费说明：</span></div>
                <div class="input_info_high">
                    <s:textarea name="cost.descr" cssClass="width300 height70"></s:textarea>
                    <div class="validate_msg_short error_msg">100长度的字母、数字、汉字和下划线的组合</div>
                </div>
                <div class="button_info clearfix">
                    <input type="button" value="保存" class="btn_save"  onclick="showResult();" />
                    <input type="button" value="取消" class="btn_save" onclick="window.history.go(-1);"/>
                </div>
            </form>  
        </div>
        <!--主要区域结束-->
         <div id="footer">
  			<span>齐鲁工业大学。二零一四届毕业生课程设计。基于Java的电信行业计费系统的设计与实现</span>
        </div>
    </body>
</html>