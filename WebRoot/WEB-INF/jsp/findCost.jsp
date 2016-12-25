<%@page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@page import="java.util.*"%>
<%@page isELIgnored="false"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
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
            //排序按钮的点击事件
            function sort(btnObj) {
                if (btnObj.className == "sort_desc")
                    btnObj.className = "sort_asc";
                else
                    btnObj.className = "sort_desc";
            }

            //启用
            function startCost(id,page) {
                var r = window.confirm("确定要启用此资费吗？资费启用后将不能修改和删除。");
                if(r){
                		$.post("startCost.action",{"id":id},function(data){
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
					           			window.location.href="findCost.action?page="+page;
				           			},
				           			1500)
				           			 							
                		});
                }else{
                return ;
                }
            }
            //删除
            function deleteCost(id) {
                var r = window.confirm("确定要删除此资费吗？");
                if(r){
											window.location.href="deleteCost.action?id="+id;		                
                document.getElementById("operate_result_info").style.display = "block";
                }
            }
            
            
        </script>
</head>
<%-- <body onbeforeunload="RunOnBeforeUnload('<%=basePath %>');" onunload="RunOnUnload();"> --%>
<body>
	<!--Logo区域开始-->
	<div id="header">
		<img src="../images/logo.png" alt="logo" class="left" /> <a
			href="../login/toLogin.action">[退出]</a> <a onclick="show2Dcode();">
			<img src="../images/ZxingImage/a.png" alt="得到二维码" width="50"
			height="50" title="得到二维码" /> </a>
	</div>
	<!--Logo区域结束-->
	<!--导航区域开始-->
	<div id="navi">
		<ul id="menu">
			<li><a href="login.action" class="index_off"></a></li>
			<li><a href="../role/toRole.action" class="role_off"></a></li>
			<li><a href="../admin/findAdmin.action" class="admin_off"></a></li>
			<li><a href="../cost/findCost.action" class="fee_on"></a></li>
			<li><a href="../account/findaccount.action" class="account_off"></a>
			</li>
			<li><a href="../service/findService.action" class="service_off"></a>
			</li>
			<li><a href="../serverconfig/toconfiginfo.action"
				class="bill_off"></a>
               	<li><a href="../privilege/findPrivilege.action" class="report_off"></a></li>
				<li><a href="../admin/findAdminById.action" class="information_off"></a></li>
				<li><a href="../admin/tomodpasswd.action" class="password_off"></a></li>
		</ul>
	</div>
	<!--导航区域结束-->
	<!--主要区域开始-->
	<div id="main">
		<form action="findCost.action" method="post" id="costForm">
			<!--排序-->
			<div class="search_add">
				<div>
					<input type="button" value="月租" class="sort_asc"
						onclick="sort(this);" /> <input type="button" value="基费"
						class="sort_asc" onclick="sort(this);" /> <input type="button"
						value="时长" class="sort_asc" onclick="sort(this);" />
				</div>
				<input type="button" value="增加" class="btn_add"
					onclick="location.href='toAddCost.action';" />
			</div>
			<!--启用操作的操作提示-->
			<div id="operate_result_info" class="operate_success">
				<img src="../images/close.png"
					onclick="this.parentNode.style.display='none';" /> <span
					id="operMsg"></span>
			</div>
			<!--数据区域：用表格展示数据-->
			<div id="data">
				<table id="datalist">
					<tr>
						<th>资费ID</th>
						<th class="width100">资费名称</th>
						<th>基本时长</th>
						<th>基本费用</th>
						<th>单位费用</th>
						<th>创建时间</th>
						<th>开通时间</th>
						<th class="width50">状态</th>
						<th class="width200"></th>
					</tr>
					<s:iterator value="costlist" var="cost">
						<tr>
							<td><s:property value="#cost.id" />
							</td>
							<td><a
								href="toCostDetail.action?id=<s:property value='#cost.id'/>"><s:property
										value="#cost.name" /> </a></td>
							<td><s:property value="#cost.baseDuration" />&nbsp;H</td>
							<td><s:property value="#cost.baseCost" />元</td>
							<td><s:property value="#cost.unitCost" />元</td>
							<td><s:date name="#cost.createTime" format="yyyy-mm-dd" />
							</td>
							<!-- 此处日期处理和JSTL不一样 ，JSTL标签显示年时是四位，struts标签显示年是2位年，要用struts的date标签 -->
							<td><s:property value="#cost.startTime" /></td>
							<td><s:if test="#cost.status==0">开通</s:if> <s:if
									test="#cost.status==1">暂停</s:if> <s:if test="#cost.status==2">删除</s:if>
							</td>
							<td><s:if test="status==0">
								</s:if> <s:if test="status==1">
									<input type="button" value="开通" class="btn_start"
										onclick="startCost(<s:property value='id'/>,<s:property value='page'/>);" />
									<input type="button" value="修改" class="btn_modify"
										onclick="location.href='toUpdateCost.action?id=<s:property value='id'/>';" />
									<input type="button" value="删除" class="btn_delete"
										onclick="deleteCost(<s:property value='id'/>);" />
								</s:if> <s:if test="status==2">
								</s:if>
							</td>
						</tr>
					</s:iterator>
				</table>
				<p>
					业务说明：<br /> 1、创建资费时，状态为暂停，记载创建时间；<br /> 2、暂停状态下，可修改，可删除；<br />
					3、开通后，记载开通时间，且开通后不能修改、不能再停用、也不能删除；<br />
					4、业务账号修改资费时，在下月底统一触发，修改其关联的资费ID（此触发动作由程序处理）
				</p>
			</div>
			<!--分页-->
			<div id="pages">
				<s:if test="page==1">
					<a href="#">上一页</a>
				</s:if>
				<s:else>
					<a href="findCost.action?page=<s:property value='page-1'/>">上一页</a>
				</s:else>

				<s:iterator var="p" begin="1" end="totalpage" step="1">
					<s:if test="page==#p">
						<a href="findCost.action?page=<s:property value="#p"/>"
							class="current_page"><s:property value="#p" /> </a>
					</s:if>
					<s:else>
						<a href="findCost.action?page=<s:property value="#p"/>"><s:property
								value="#p" /> </a>
					</s:else>
				</s:iterator>
				<s:if test="page == totalpage">
					<a href="#">下一页</a>
				</s:if>
				<s:else>
					<a href="findCost.action?page=<s:property value='page+1'/>">下一页</a>
				</s:else>
			</div>
		</form>
	</div>
	<!--主要区域结束-->
	<div id="footer">
  			<span>齐鲁工业大学。二零一四届毕业生课程设计。基于Java的电信行业计费系统的设计与实现</span>
	</div>
</body>
</html>
