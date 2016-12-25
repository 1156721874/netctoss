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
            function changeTab(e,ulObj) {                
                var obj = e.srcElement || e.target;
                if (obj.nodeName == "A") {
                    var links = ulObj.getElementsByTagName("a");
                    for (var i = 0; i < links.length; i++) {
                        if (links[i].innerHTML == obj.innerHTML) {
                            links[i].className = "tab_on";
                        }
                        else {
                            links[i].className = "tab_out";
                        }
                    }
                }
            }
            
            function addprivilege(){
            window.location.href="toaddPrivilege.action";
            
            }
            function showdiv(id){
			  $("#"+"url"+eval(id-1)).show("slow");
            }
            
            function hidediv(id){
             $("#"+"url"+eval(id-1)).hide("slow");
            }
            
            var count=0;
            function clickdiv(id){
            if((count++)%2==0){
              $("#"+"url"+eval(id)).show("slow");
            }else{
             $("#"+"url"+eval(id)).hide("slow");
            }
            }
            function modPrivilege(obj,evt){
            window.location.href="toModPrivilege.action?id="+obj;
            evt.cancelBubble=true;  
            }
            
            function delPrivilege(obj,evt){
            window.confirm("Are you sure delete this info?【deleted is canot recover】");
            window.location.href="delprivilege.action?id="+obj;
            evt.cancelBubble=true;  
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
                <li><a href="../account/findaccount.action" class="account_off"></a></li>
                <li><a href="../service/findService.action" class="service_off"></a></li>
               <li><a href="../serverconfig/toconfiginfo.action" class="bill_off"></a>
               	<li><a href="../privilege/findPrivilege.action" class="report_on"></a></li>
				<li><a href="../admin/findAdminById.action" class="information_off"></a></li>
				<li><a href="../admin/tomodpasswd.action" class="password_off"></a></li>
            </ul>            
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->        
        <div id="report_main">
        	<div class="tabs">
			<input type="button" value="新增" onclick="addprivilege();" class="btn_add" />
            </div>            
            <div class="report_box">
                <!--数据区域：用表格展示数据-->
                <div id="report_data">
                       <s:iterator value="privileges"  var="privilege" status="seq">
                           <div id="<s:property value="id"/>"  style="border: 1px;background-color: #e8f3f8" onclick="clickdiv(<s:property value="id"/>);" >
	                          	<div style="border: 3px;background-color:#8ac1db" title="单击展开" >
		                          	id:<s:property value="id"/> <a style="margin-left: 778px" href="#" onclick="modPrivilege(<s:property value="id"/>,event);">修改</a><a style="margin-left: 5px" href="#" onclick="delPrivilege(<s:property value="id"/>,event);">删除</a><br/>
		                          	name:<s:property value="name"/><br/>
		                        </div>
		                        <hr style="color: red;border: 1px" />
		                          	<div id="url<s:property value="id"/>" style="display: none;background-color: pink" >
		                          	  <s:iterator value="urls" status="seq">
			                          		<s:property value="urls[#seq.index]"/><br/>
			                          </s:iterator>		
			                        </div>
		                   </div> 
                       </s:iterator>   
                </div>
            </div>
        </div>
        <!--主要区域结束-->
               <div id="footer">
  			<span>齐鲁工业大学。二零一四届毕业生课程设计。基于Java的电信行业计费系统的设计与实现</span>
        </div>
    </body>
</html>
