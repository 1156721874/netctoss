<%@page pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
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
                <li><a href="../privilege/findPrivilege.action" class="report_off"></a>
                <li><a href="../user/user_info.jsp" class="information_off"></a></li>
				<li><a href="../admin/findAdminById.action" class="information_off"></a></li>
				<li><a href="../admin/tomodpasswd.action" class="password_off"></a></li>
            </ul>
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">            
            <form action="" method="" class="main_form">
                <!--必填项-->
                <div class="text_info clearfix"><span>账务账号ID：</span></div>
                <div class="input_info">
                <%--<input type="text" value="10" readonly class="readonly" />
                 --%>
                 <s:textfield name="account.id" disabled="true" cssClass="readonly" ></s:textfield>
                </div>
                <div class="text_info clearfix"><span>姓名：</span></div>
                <div class="input_info">
                <%--<input type="text" value="张三" readonly class="readonly" />
                --%>
                <s:textfield name="account.real_name" disabled="true" cssClass="readonly" ></s:textfield>
                </div>
                <div class="text_info clearfix"><span>身份证：</span></div>
                <div class="input_info">
                    <%--<input type="text" value="230198765432123456" readonly class="readonly" />--%>
                    <s:textfield name="account.idcard_no" disabled="true" cssClass="readonly"></s:textfield>
                </div>
                <div class="text_info clearfix"><span>登录账号：</span></div>
                <div class="input_info">
                    <%--<input type="text" value="user1" readonly class="readonly" />
                    --%>
                    <s:textfield name="account.login_name" disabled="true" cssClass="readonly"></s:textfield>
                </div>                   
                <div class="text_info clearfix"><span>电话：</span></div>
                <div class="input_info"><%--
                    <input type="text" class="width200 readonly" readonly value="13687865435" />
                      --%>
                      <s:textfield name="account.telephone" disabled="true" cssClass="width200 readonly"></s:textfield>
                </div>
                <div class="text_info clearfix"><span>推荐人账务账号ID：</span></div>
                <div class="input_info">
                <%--<input type="text" readonly class="readonly" value="5" />
                --%>
                <s:textfield name="account.recommender_id" disabled="true" cssClass="readonly"></s:textfield>
                </div>
                <div class="text_info clearfix"><span>推荐人身份证号码：</span></div>
                <div class="input_info"><%--
                <input type="text" readonly class="readonly" value="230198765432123456" />
                --%>
                <s:textfield name="reidcard" disabled="true" cssClass="readonly"></s:textfield>
                </div>
                <div class="text_info clearfix"><span>状态：</span></div>
                <div class="input_info">
                <!-- 
                    <select disabled>
                        <option>开通</option>
                        <option>暂停</option>
                        <option>删除</option>
                    </select>  
                     -->
                    <s:select list="#{'0':'开通','1':'暂停','2':'删除'}" name="account.status"></s:select>                      
                </div>                    
                <div class="text_info clearfix"><span>开通/暂停/删除时间：</span></div>
                <div class="input_info"><%--
                <input type="text" readonly class="readonly" value="2013/01/21 12:00:00" />
                --%>
                <s:textfield name="account.create_date" disabled="true" cssClass="readonly"></s:textfield>
                </div>

                <div class="text_info clearfix"><span>上次登录时间：</span></div>
                <div class="input_info"><%--
                <input type="text" readonly class="readonly" value="2013/02/21 12:00:00" />
                 --%>
                 <s:textfield name="account.last_login_time" disabled="true" cssClass="readonly"></s:textfield>
                </div>
                <div class="text_info clearfix"><span>上次登录IP：</span></div>
                <div class="input_info">
                <%--<input type="text" readonly class="readonly" value="192.168.0.100" />
                --%>
                <s:textfield name="account.last_login_ip" disabled="true" cssClass="readonly"></s:textfield>
                </div>
                <!--可选项数据-->
                <div class="text_info clearfix"><span>生日：</span></div>
                <div class="input_info">
                    <%--<input type="text" readonly class="readonly" value="1980/01/21，由身份证计算而来" />
                     --%>
                     <s:textfield name="birthdate" disabled="true" cssClass="readonly"></s:textfield>
                </div>
                <div class="text_info clearfix"><span>Email：</span></div>
                <div class="input_info">
                    <%--<input type="text" class="width350 readonly" readonly value="aa@aa.com" />
                    --%>
                    <s:textfield name="account.email" disabled="true" cssClass="width350 readonly"></s:textfield>
                </div> 
                <div class="text_info clearfix"><span>职业：</span></div>
                <div class="input_info">
                <!--
                    <select disabled>
                        <option>干部</option>
                        <option>学生</option>
                        <option>技术人员</option>
                        <option>其他</option>
                    </select>  
                      -->
                      <s:select list="#{'干部':'干部','学生':'学生','技术人员':'技术人员','其他':'其他'}" name="account.occupation"></s:select>                      
                </div>
                <div class="text_info clearfix"><span>性别：</span></div>
                <div class="input_info fee_type"><%--
                    <input type="radio" name="radSex" checked="checked" id="female" disabled />
                    --%>
                    <s:radio name="account.gender" list="#{'1':'男','0':'女'}"  disabled="true" cssClass="width350 readonly" id="female"></s:radio>
                    <%--
                    <label for="female">女</label>
                    <input type="radio" name="radSex" id="male" disabled />
                    <label for="male">男</label>
                --%></div> 
                <div class="text_info clearfix"><span>通信地址：</span></div>
                <div class="input_info">
                <%--<input type="text" class="width350 readonly" readonly value="北京市海淀区北三环中路甲18号中鼎大厦" />
                --%>
                <s:textfield name="account.mailaddress" disabled="true" cssClass="readonly"></s:textfield>
                </div> 
                <div class="text_info clearfix"><span>邮编：</span></div>
                <div class="input_info"><%--
                <input type="text" class="readonly" readonly value="100098" />
                --%>
                <s:textfield name="account.zipcode" disabled="true" cssClass="readonly"></s:textfield>
                </div> 
                <div class="text_info clearfix"><span>QQ：</span></div>
                <div class="input_info"><%--
                <input type="text" class="readonly" readonly value="12345678" />
                --%>
                <s:textfield name="account.qq" disabled="true" cssClass="readonly"></s:textfield>
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
