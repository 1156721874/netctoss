<%@page pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@page import="java.util.*"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>服务器资费管理系统</title>
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" />
        <script type="text/javascript" src="../js/common.js"></script>
        <script language="javascript" type="text/javascript">
            //保存成功的提示信息
            function showResult() {
                showResultDiv(true);
                window.setTimeout("showResultDiv(false);", 3000);
                document.getElementById("updateservice").submit();
                
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
<a onclick="show2Dcode();" > <img src="../images/ZxingImage/a.png" alt="得到二维码" width="50" height="50" title="得到二维码" />  </a>            
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
            <div id="save_result_info" class="save_fail">资费修改失败！数据并发错误。</div>
            <form action="UpdateService" method="post" class="main_form" id="updateservice">
                <!--必填项-->
                <div class="text_info clearfix"><span>业务账号ID：</span></div>
                <div class="input_info">
                    <%--<input type="text" value="1" readonly class="readonly" />
                    --%>
                    <s:textfield name="service.id" readonly="true" cssClass="readonly"></s:textfield>
                </div>
                <div class="text_info clearfix"><span>OS 账号：</span></div>
                <div class="input_info">
                    <%--<input type="text" value="openlab1" readonly class="readonly" />
                     --%>
                     <s:textfield name="service.os_username" readonly="true" cssClass="readonly"></s:textfield>
                </div>
                <div class="text_info clearfix"><span>服务器 IP：</span></div>
                <div class="input_info"><%--
                    <input type="text" value="192.168.0.23" readonly class="readonly" />
                    --%>
                    <s:textfield name="service.unix_host" readonly="true" cssClass="readonly"></s:textfield>
                </div>
                <div class="text_info clearfix"><span>资费类型：</span></div>
                <div class="input_info"><%--
                    <select class="width150">
                        <option>包 20 小时</option>
                        <option>包 40 小时</option>
                        <option>包 60 小时</option>
                        <option>包月</option>
                    </select>
                    --%>
                    <s:select list="costs" listKey="id" listValue="name" name="service.cost_id"></s:select>
                    <div class="validate_msg_long">请修改资费类型，或者取消修改操作。</div>                      
                </div>
                <!--操作按钮-->
                <div class="button_info clearfix">
                    <input type="button" value="保存" class="btn_save" onclick="showResult();" />
                    <input type="button" value="取消" class="btn_save" onclick="window.history.go(-1);" />
                </div>

                
                <p>业务说明：<br />
                1、修改资费后，点击保存，并未真正修改数据库中的数据；<br />
                2、提交操作到消息队列；<br />
                3、每月月底由程序自动完成业务所关联的资费；</p>
                
            </form>  
        </div>
        <!--主要区域结束-->
          <div id="footer">
  			<span>齐鲁工业大学。二零一四届毕业生课程设计。基于Java的电信行业计费系统的设计与实现</span>
        </div>
    </body>
</html>
