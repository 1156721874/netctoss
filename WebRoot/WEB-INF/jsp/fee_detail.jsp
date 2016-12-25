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
    <script type="text/javascript" src="../js/jquery-1.4.3.js"></script>     
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
            <form action="" method="" class="main_form">
                <div class="text_info clearfix"><span>资费ID：</span></div>
                <div class="input_info">
                <%--<input type="text" class="readonly" readonly value="1" />
                --%>
                <s:textfield name="cost.id" cssClass="readonly" readonly="true"></s:textfield>
                </div>
                <div class="text_info clearfix"><span>资费名称：</span></div>
                <div class="input_info"><%--
                <input type="text" class="readonly" readonly value="包 20 小时"/>
                --%>
                <s:textfield name="cost.name" cssClass="readonly" readonly="true"></s:textfield>
                </div>
                <div class="text_info clearfix"><span>资费状态：</span></div>
                <div class="input_info"><%--
                    <select class="readonly" disabled>
                        <option>开通</option>
                        <option>暂停</option>
                        <option>删除</option>
                    </select>    
                    --%>
                    <s:select list="#{'0':'开通','1':'暂停','2':'删除'}" name="cost.status" disabled="true"></s:select>                    
                </div>
                <div class="text_info clearfix"><span>资费类型：</span></div>
                <div class="input_info fee_type"><%--
                    <input type="radio" name="radFeeType" id="monthly" disabled="disabled" />
                    <label for="monthly">包月</label>
                    <input type="radio" name="radFeeType" id="package" disabled="disabled" />
                    <label for="package">套餐</label>
                    <input type="radio" name="radFeeType" checked="checked" id="timeBased" disabled="disabled" />
                    <label for="timeBased">计时</label>
                    --%>
                    <s:radio list="#{'1':'包月','2':'套餐','3':'计时'}" name="cost.costType"></s:radio>
                </div>
                <div class="text_info clearfix"><span>基本时长：</span></div>
                <div class="input_info">
                    <input type="text" class="readonly" readonly value="20"  />
                    <span>小时</span>
                </div>
                <div class="text_info clearfix"><span>基本费用：</span></div>
                <div class="input_info">
                    <%--<input type="text"  class="readonly" readonly value="24.5" />
                    --%>
                    <s:textfield name="cost.baseCost" cssClass="readonly" readonly="true"></s:textfield>
                    <span>元</span>
                </div>
                <div class="text_info clearfix"><span>单位费用：</span></div>
                <div class="input_info"><%--
                    <input type="text"  class="readonly" readonly value="3.00" />
                    --%>
                    <s:textfield name="cost.unitCost" cssClass="readonly" readonly="true"></s:textfield>
                    <span>元/小时</span>
                </div>
                <div class="text_info clearfix"><span>创建时间：</span></div>
                <div class="input_info">
                <%--<input type="text"  class="readonly" readonly value="2013/1/1 00:00:00" />
                --%>
                <s:textfield name="cost.createTime" cssClass="readonly" readonly="true"></s:textfield>
                </div>      
                <div class="text_info clearfix"><span>启动时间：</span></div>
                <div class="input_info">
                <%--<input type="text"  class="readonly" readonly value="2013/1/1 00:00:00" />
                --%>
                <s:textfield name="cost.startTime" cssClass="readonly" readonly="true"></s:textfield>
                </div>      
                <div class="text_info clearfix"><span>资费说明：</span></div>
                <div class="input_info_high">
                    <%--<textarea class="width300 height70 readonly" readonly>包 20 小时，20 小时以内 24.5 元，超出部分 3.00 元/小时</textarea>
                    --%>
                    <s:textarea cssClass="width300 height70 readonly" readonly="true" name="cost.descr"></s:textarea>
                </div>                    
                <div class="button_info clearfix">
                    <input type="button" value="返回" class="btn_save" onclick="history.go(-1);" />
                </div>
            </form>  
        </div>
        <!--主要区域结束-->
        <div id="footer">
  			<span>齐鲁工业大学。二零一四届毕业生课程设计。基于Java的电信行业计费系统的设计与实现</span>
        </div>
    </body>
</html>
