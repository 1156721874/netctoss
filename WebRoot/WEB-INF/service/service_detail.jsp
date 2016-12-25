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
            <form action="" method="" class="main_form">
                <!--必填项-->
                <div class="text_info clearfix"><span>业务账号ID：</span></div>
                <div class="input_info">
                <%--<input type="text" value="1" readonly class="readonly" />
                --%>
                <s:textfield name="service.id" readonly="true" cssClass="readonly"></s:textfield>
                </div>
                <div class="text_info clearfix"><span>账务账号ID：</span></div>
                <div class="input_info">
                <%--<input type="text" value="101" readonly class="readonly" />
                --%>
                <s:textfield name="service.account.id" readonly="true" cssClass="readonly"></s:textfield>
                </div>
                <div class="text_info clearfix"><span>客户姓名：</span></div>
                <div class="input_info">
                <%--<input type="text" readonly class="readonly" value="张三" />
                 --%>
                 <s:textfield name="service.realName" readonly="true" cssClass="readonly"></s:textfield>
                </div>
                <div class="text_info clearfix"><span>身份证号码：</span></div>
                <div class="input_info">
                <%--<input type="text" readonly class="readonly" value="230111111111111111" />
                --%>
                <s:textfield name="service.idCardNo" readonly="true" cssClass="readonly"></s:textfield>
                </div>
                <div class="text_info clearfix"><span>服务器 IP：</span></div>
                <div class="input_info">
                <%--<input type="text" value="192.168.0.23" readonly class="readonly" />
                 --%>
                 <s:textfield name="service.unix_host" readonly="true" cssClass="readonly"></s:textfield>
                </div>
                <div class="text_info clearfix"><span>OS 账号：</span></div>
                <div class="input_info"><%--
                <input type="text" value="openlab1" readonly class="readonly" />
                --%>
                <s:textfield name="service.os_username" readonly="true" cssClass="readonly"></s:textfield>
                </div>
                <div class="text_info clearfix"><span>状态：</span></div>
                <div class="input_info">
                    <%--<select disabled>
                        <option>开通</option>
                        <option>暂停</option>
                        <option>删除</option>
                    </select>                        
                --%>
                <s:select list="#{'0':'开通','1':'暂停','2':'删除'}" name="service.status"></s:select>
                </div>
                <div class="text_info clearfix"><span>开通时间：</span></div>
                <div class="input_info"><%--
                <input type="text" readonly class="readonly" />
                --%>
                <s:textfield name="service.create_date" readonly="true" cssClass="readonly"></s:textfield>
                </div>
                <div class="text_info clearfix"><span>资费 ID：</span></div>
                <div class="input_info">
                <%--<input type="text" class="readonly" readonly />
                --%>
                <s:textfield name="service.cost.id" readonly="true" cssClass="readonly"></s:textfield>
                </div>
                <div class="text_info clearfix"><span>资费名称：</span></div>
                <div class="input_info">
                <%--<input type="text" readonly class="width200 readonly" />
                --%>
                <s:textfield name="service.costName" readonly="true" cssClass="readonly"></s:textfield>
                </div>
                <div class="text_info clearfix"><span>资费说明：</span></div>
                <div class="input_info_high">
                    <%--<textarea class="width300 height70 readonly" readonly>包 20 小时，20 小时以内 24.5 元，超出部分 0.03 元/分钟</textarea>
                    --%>
                    <s:textarea name="service.descr" cssClass="width300 height70 readonly" readonly="true"></s:textarea>
                </div>  
                <!--操作按钮-->
                <div class="button_info clearfix">
                    <input type="button" value="返回" class="btn_save" onclick="window.history.go(-1);" />
                </div>
            </form>
        </div>
        <!--主要区域结束-->
          <div id="footer">
  			<span>齐鲁工业大学。二零一四届毕业生课程设计。基于Java的电信行业计费系统的设计与实现</span>
        </div>
    </body>
</html>
