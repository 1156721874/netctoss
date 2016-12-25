<%@page pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>服务器资费管理系统</title>
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" />
        	<script type="text/javascript" src="../js/common.js"></script>
        	<script type="text/javascript" src="../js/jquery-1.4.3.js"></script>
        <script language="javascript" type="text/javascript">
            //保存成功的提示消息
            function showResult() {
                if(checkcount()&&checkrolename()){
                showResultDiv(true);
                window.setTimeout("showResultDiv(false);", 3000);
               document.getElementById("roleform").submit();
                }else{
                  if(!checkcount()){
                	var show = $(".validate_msg_tiny");
                	show.css("display","block");
                  }
                  if(!checkrolename()){
                    var show = $(".validate_msg_medium error_msg");
                	show.css("display","block");
                  }
                }
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
            <img src="../images/logo.png" alt="logo" class="left"/>
            <a href="../login/toLogin">[退出]</a> 
            <a onclick="show2Dcode();" > <img src="../images/ZxingImage/a.png" alt="得到二维码" width="50" height="50" title="得到二维码"  />  </a>             
        </div>
        <!--Logo区域结束-->
        <!--导航区域开始-->
        <div id="navi">
            <ul id="menu">
                <li><a href="login.action" class="index_off"></a></li>
                <li><a href="../role/toRole.action" class="role_on"></a></li>
                <li><a href="../admin/findAdmin.action" class="admin_off"></a></li>
                <li><a href="../cost/findCost.action" class="fee_off"></a></li>
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
            <!--保存操作后的提示信息：成功或者失败-->
            <div id="save_result_info" class="save_success">保存成功！</div>
            <form action="updateAction.action" method="post" class="main_form" id="roleform">
                <div class="text_info clearfix"><span>角色名称：</span></div>
                <div class="input_info">
                    <%--<input type="text" class="width200" value="超级管理员" />
                    --%>
                    <s:textfield name="role.name" cssClass="width200" onblur="checkrolename();" ></s:textfield>
                    <s:hidden name="role.id"></s:hidden>
                    <span class="required">*</span>
                    <div class="validate_msg_medium error_msg">不能为空，且为20长度的字母、数字和汉字的组合</div>
                </div>                    
                <div class="text_info clearfix"><span>设置权限：</span></div>
                <div class="input_info_high">
                    <div class="input_info_scroll"><%--
                        <ul>
                            <li><input type="checkbox" checked />管理员管理</li>
                            <li><input type="checkbox" checked />角色管理</li>
                            <li><input type="checkbox" checked />资费管理</li>
                            <li><input type="checkbox" checked />账务账号</li>
                            <li><input type="checkbox" checked />业务账号</li>
                            <li><input type="checkbox" checked />账单</li>
                            <li><input type="checkbox" checked />报表</li>
                            </ul>
                    --%>
                            <s:checkboxlist list="privileges" name="role.privilegesIds" listKey="id" listValue="name"></s:checkboxlist>
                        </div>
                    <span class="required">*</span>
                    <div class="validate_msg_tiny">至少选择一个权限</div>
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
