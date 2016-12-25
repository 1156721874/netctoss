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
        <script language="javascript" type="text/javascript">
            //显示角色详细信息
            function showDetail(flag, a) {
                var detailDiv = a.parentNode.getElementsByTagName("div")[0];
                if (flag) {
                    detailDiv.style.display = "block";
                }
                else
                    detailDiv.style.display = "none";
            }
            //删除
            function deleteAccount() {
                var r = window.confirm("确定要删除此业务账号吗？删除后将不能恢复。");
                document.getElementById("operate_result_info").style.display = "block";
            }
            //开通或暂停
            function setState() {
                var r = window.confirm("确定要开通此业务账号吗？");
                document.getElementById("operate_result_info").style.display = "block";
            }
            
            function topage(page){
            	//把当前页page记录到hidden中
          
            	document.getElementById("page").value=page;
            	  	// alert(document.getElementById("page").value);
            	//提交表单
            	document.getElementById("serviceform").submit();
            					}
            					
            	function pauseService(id){
            					  var r = window.confirm("确定要暂停此账务账号吗？");
                if(r){
                		$.post("pauseService.action",{"id":id},function(data){
                		$("#operMsg").text("");
                		var msg = "";
                			 if(data){
                			 								msg = "暂停成功";
                			 							
                			 }else{
                									   msg = "暂停失败";
                									  }
			           									  	$("#operMsg").text(msg);
			           			 								$("#operate_result_info").show();
			           			 								setTimeout(function (){
			           			 									var page = $("#page").val();
			           			 								topage(page);
			           			 								},1500)
			           			 							
                								});
                }else{return ;}
            					}
            					
            			function startService(id) {
                var r = window.confirm("确定要开通此账务账号吗？");
                if(r){
                		$.post("startService.action",{"id":id},function(data){
                		$("#operMsg").text("");
                		var msg = "";
                			 if(data==1){
                			 								msg = "开通成功";
                			 							
                			 }else if(data==0){
                									   msg = "帐务帐号状态为暂停或删除时不能开通业务";
                									  }else{
                									  msg = "开通失败 稍后重试";
                									  }
			           									  	$("#operMsg").text(msg);
			           			 								$("#operate_result_info").show();
			           			 								setTimeout(function (){
			           			 									var page = $("#page").val();
			           			 								topage(page);
			           			 								},1500)
			           			 							
                								});
                }else{return ;}
            }
            function deleteService(id){
             var r = window.confirm("确定要删除此账务账号吗？");
                if(r){
                		$.post("deleteservice.action",{"id":id},function(data){
                		var msg = "";
                			 if(data){
                			 								msg = "删除成功";
                			 							
                			 }else{
                									   msg = "删除失败";
                									  }
			           									  	$("#operMsg").text(msg);
			           			 								$("#operate_result_info").show();
			           			 								setTimeout(function (){
			           			 									var page = $("#page").val();
			           			 								topage(page);
			           			 								},1500)
			           			 							
                								});
                }else{return ;}
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
            <form action="findService.action" method="post" id="serviceform">
                <!--查询-->
                <div class="search_add">                        
                    <div>OS 账号：
                    <%--<input type="text" value="" class="width100 text_search" />
                    --%>
                    <s:textfield cssClass="width100 text_search" name="os_username"></s:textfield>
                    </div>                            
                    <div>服务器 IP：
                    <%--<input type="text" value="" class="width100 text_search" />
                    --%>
                    <s:textfield name="unix_host" cssClass="width100 text_search" ></s:textfield>
                    </div>
                    <div>身份证：
                    <%--<input type="text"  value="" class="text_search" />
                    --%>
                    <s:textfield name="idCardNo" cssClass="text_search" ></s:textfield>
                    </div>
                    <div><%--状态：
                        <select class="select_search">
                            <option>全部</option>
                            <option>开通</option>
                            <option>暂停</option>
                            <option>删除</option>
                        </select>
                        --%>
                        <s:select list="#{'-1':'全部','0':'开通','1':'暂停','2':'删除'}" name="status" cssClass="select_search">
                        </s:select>
                    </div>
                    <div>
                    <s:hidden name="page" id="page" ></s:hidden>
                    <input type="button" value="搜索" class="btn_search" onclick="topage(1);" />
                    
                    </div>
                    <input type="button" value="增加" class="btn_add" onclick="location.href='toAddService.action';" />
                </div>  
                <!--删除的操作提示-->
                <div id="operate_result_info" class="operate_success">
                    <img src="../images/close.png" onclick="this.parentNode.style.display='none';" />
                    <span id="operMsg">
                    </span>
                </div>   
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                    <tr>
                        <th class="width50">业务ID</th>
                        <th class="width70">账务账号ID</th>
                        <th class="width150">身份证</th>
                        <th class="width70">姓名</th>
                        <th>OS 账号</th>
                        <th class="width50">状态</th>
                        <th class="width100">服务器 IP</th>
                        <th class="width100">资费</th>                                                        
                        <th class="width200"></th>
                    </tr>
                    <s:iterator value="servicevos">
                    <tr>
                    
                        <td><a href="service_detail.action?id=<s:property value='id'/>" title="查看明细"><s:property value="id"/></a></td>
                        <td><s:property value="account.id"/> </td>
                        <td><s:property value="idCardNo"/></td>
                        <td><s:property value="realName"/> </td>
                            <td><s:property value="os_username"/></td>
                        <td>					
                        <s:if test="status==0">开通</s:if>
																<s:if test="status==1">暂停</s:if>
																<s:if test="status==2">删除</s:if>
																</td>
                        <td><s:property value="unix_host"/> </td>
                        <td>
                            <a class="summary"  onmouseover="showDetail(true,this);" onmouseout="showDetail(false,this);">
																	<s:property value="costName"/>
</a>
                            <!--浮动的详细信息-->
                            <div class="detail_info">
                                20小时，24.5 元，超出部分 0.03元/分钟
                            </div>
                        </td>                            
                        <td class="td_modi">
                <s:if test="status==0">
										<input type="button" value="暂停" class="btn_pause"
											onclick="pauseService(<s:property value='id'/>)" />
										<input type="button" value="修改" class="btn_modify"
											onclick="location.href='toUpdateService.action?id=<s:property value='id'/>';" />
										<input type="button" value="删除" class="btn_delete"
											onclick="deleteService(<s:property value='id'/>);" />
									</s:if>
									<s:if test="status==1">
										<input type="button" value="开通" class="btn_start"
											onclick="startService(<s:property value='id'/>);" />
										<input type="button" value="修改" class="btn_modify"
											onclick="location.href='toUpdateService.action?id=<s:property value='id'/>';" />
										<input type="button" value="删除" class="btn_delete"
											onclick="deleteService(<s:property value='id'/>);" />
									</s:if>
									<s:if test="status==2">
									</s:if>
                        </td>
                    </tr>
                    </s:iterator>
                </table>                
                <p>业务说明：<br />
                1、创建即开通，记载创建时间；<br />
                2、暂停后，记载暂停时间；<br />
                3、重新开通后，删除暂停时间；<br />
                4、删除后，记载删除时间，标示为删除，不能再开通、修改、删除；<br />
                5、业务账号不设计修改密码功能，由用户自服务功能实现；<br />
                6、暂停和删除状态的账务账号下属的业务账号不能被开通。</p>
                </div>                    
                <!--分页-->
                <div id="pages">
              <a href="javascript:topage(1);">首页</a>
					<s:if test="page==1">
						<a href="#">上一页</a>
					</s:if>
					<s:else>
						<a href="javascript:topage(<s:property value="page-1"/>);">上一页</a>
					</s:else>

					<s:iterator begin="1" end="totalpage" var="p">
						<s:if test="#p==page">
							<a href="javascript:topage(<s:property value="#p"/>);"
								class="current_page"><s:property value="#p" />
							</a>
						</s:if>
						<s:else>
							<a href="javascript:topage(<s:property value="#p"/>);"><s:property
									value="#p" />
							</a>
						</s:else>
					</s:iterator>

					<s:if test="page==totalpage">
						<a href="#">下一页</a>
					</s:if>
					<s:else>
						<a href="javascript:topage(<s:property value="page+1"/>);">下一页</a>
					</s:else>

					<a href="javascript:topage(<s:property value="totalpage"/>);">末页</a>
                </div>                    
            </form>
        </div>
        <!--主要区域结束-->
           <div id="footer">
  			<span>齐鲁工业大学。二零一四届毕业生课程设计。基于Java的电信行业计费系统的设计与实现</span>
        </div>
    </body>
</html>
