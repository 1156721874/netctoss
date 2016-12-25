<%@page pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>服务器资费管理系统</title>
		<link type="text/css" rel="stylesheet" media="all"
			href="../styles/global.css" />
		<link type="text/css" rel="stylesheet" media="all"
			href="../styles/global_color.css" />
		<script type="text/javascript" src="../js/jquery-1.4.3.js"></script>
			<script type="text/javascript" src="../js/common.js"></script>
		<script language="javascript" type="text/javascript">
            //删除
            function deleteAccount() {
                var r = window.confirm("确定要删除此账务账号吗？\r\n删除后将不能恢复，且会删除其下属的所有业务账号。");
                document.getElementById("operate_result_info").style.display = "block";
            }
            //开通或暂停
            function startAccount(id) {
                var r = window.confirm("确定要开通此账务账号吗？");
                if(r){
                		$.post("startAccount.action",{"id":id},function(data){
                		var msg = "";
                			 if(data){
                			 								msg = "开通成功";
                			 							
                			 }else{
                									   msg = "开通失败";
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
            
           function pauseAccount(id){
                 var r = window.confirm("确定要暂停此账务账号吗？");
                if(r){
                		$.post("pauseAccount.action",{"id":id},function(data){
                		var msg = "";
                			 if(data){
                			 								msg = "暂停成功";
                			 							
                			 }else{
                									   msg = "开暂停失败";
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
           
            //分页
            function topage(page){
            	//把当前页page记录到hidden中
          
            	document.getElementById("page").value=page;
            	  	// alert(document.getElementById("page").value);
            	//提交表单
            	document.getElementById("findForm").submit();
            }
            
            function deleteAccount(id){
             var r = window.confirm("确定要删除此账务账号吗？");
                if(r){
                		$.post("deleteaccount.action",{"id":id},function(data){
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
               	<li><a href="../privilege/findPrivilege.action" class="report_off"></a></li>
				<li><a href="../admin/findAdminById.action" class="information_off"></a></li>
				<li><a href="../admin/tomodpasswd.action" class="password_off"></a></li>
			</ul>
		</div>
		<!--导航区域结束-->
		<!--主要区域开始-->
		<div id="main">
			<form action="findaccount.action" method="post" id="findForm">
				<s:hidden name="page" id="page" ></s:hidden>
				<!--查询-->
				<div class="search_add">
					<div>
						身份证：
						<s:textfield name="idcard_no" cssClass="text_search" />
					</div>
					<div>
						姓名：
						<s:textfield name="real_name" cssClass="width70 text_search" />
					</div>
					<div>
						登录名：
						<s:textfield name="login_name" cssClass="text_search" />
					</div>
					<div>
						状态：
						<s:select name="status"
							list="#{'-1':'全部','0':'开通','1':'暂停','2':'删除'}"
							cssClass="select_search"></s:select>
					</div>
					<div>
						<input type="button" value="搜索" class="btn_search" onclick="topage(1)" />
					</div>
					<input type="button" value="增加" class="btn_add"
						onclick="location.href='toAddCost.action';" />
				</div>
				<!--删除等的操作提示-->
				<div id="operate_result_info" class="operate_success">
					<img src="../images/close.png"
						onclick="this.parentNode.style.display='none';" />
					<span id="operMsg">删除成功，且已删除其下属的业务账号！</span>
				</div>
				<!--数据区域：用表格展示数据-->
				<div id="data">
					<table id="datalist">
						<tr>
							<th>
								账号ID
							</th>
							<th>
								姓名
							</th>
							<th class="width150">
								身份证
							</th>
							<th>
								登录名
							</th>
							<th>
								状态
							</th>
							<th class="width100">
								创建日期
							</th>
							<th class="width150">
								上次登录时间
							</th>
							<th class="width200"></th>
						</tr>
						<s:iterator value="accounts">
							<tr>
								<td>
									<s:property value="id" />
								</td>
								<td>
									<a href="accountDetail.action?id=<s:property value='id'/>"> <s:property value="real_name" />
									</a>
								</td>
								<td>
									<s:property value="idcard_no" />
								</td>
								<td>
									<s:property value="login_name" />
								</td>
								<td>
									<s:if test="status==0">开通</s:if>
									<s:if test="status==1">暂停</s:if>
									<s:if test="status==2">删除</s:if>
								</td>
								<td>
									<s:property value="create_date" />
								</td>
								<td>
									<s:property value="last_login_time" />
								</td>
								<td class="td_modi">
									<s:if test="status==0">
										<input type="button" value="暂停" class="btn_pause"
											onclick="pauseAccount(<s:property value='id'/>)" />
										<input type="button" value="修改" class="btn_modify"
											onclick="location.href='toUpdate.action?id=<s:property value='id'/>';" />
										<input type="button" value="删除" class="btn_delete"
											onclick="deleteAccount(<s:property value='id'/>);" />
									</s:if>
									<s:if test="status==1">
										<input type="button" value="开通" class="btn_start"
											onclick="startAccount(<s:property value='id'/>);" />
										<input type="button" value="修改" class="btn_modify"
											onclick="location.href='toUpdate.action?id=<s:property value='id'/>';" />
										<input type="button" value="删除" class="btn_delete"
											onclick="deleteAccount(<s:property value='id'/>);" />
									</s:if>
									<s:if test="status==2">
									</s:if>
								</td>
							</tr>
						</s:iterator>
					</table>
					<p>
						业务说明：
						<br />
						1、创建则开通，记载创建时间；
						<br />
						2、暂停后，记载暂停时间；
						<br />
						3、重新开通后，删除暂停时间；
						<br />
						4、删除后，记载删除时间，标示为删除，不能再开通、修改、删除；
						<br />
						5、暂停账务账号，同时暂停下属的所有业务账号；
						<br />
						6、暂停后重新开通账务账号，并不同时开启下属的所有业务账号，需要在业务账号管理中单独开启；
						<br />
						7、删除账务账号，同时删除下属的所有业务账号。
					</p>
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
