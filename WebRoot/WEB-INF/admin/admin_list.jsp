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
            //显示角色详细信息
            function showDetail(flag, a) {
                var detailDiv = a.parentNode.getElementsByTagName("div")[0];
                if (flag) {
                
                    detailDiv.style.display = "block";
                }
                else
                    detailDiv.style.display = "none";
            }
            //重置密码
            function resetPwd() {
               /** var ids = new Array();
                var checkobjs = document.getElementsByName("selectadmin");
                if(checkobjs==null||checkobjs.length==0)
                alert("请至少选择一条数据！");
                for(var i=0;i<checkobjs.length;i++){
                			if(!checkobjs[i].checked){
                												continue;
                												}else{
                		var trobj=checkobjs[i].parentNode.parentNode;
                		var tdobj=	trobj.getElementsByTagName("td");
                //		alert(tdobj[1].innerHTML);
												ids.push(tdobj[1].innerHTML);	 
												}
               								 }
               								*/ 
              var trObjs = $("input[name='selectadmin']:checked").parent().parent();
            	var ids = new Array();
            	for(var i=0;i<trObjs.length;i++){
            		ids[i]=$(trObjs[i]).children()[1].innerHTML;
            	}
               $.post(
               "resetPassword.action",
               {"ids":ids.toString()},
     function(data){
               var pass=data 
               if(pass){
               	alert("reset password success");
               }else{
               	alert("reset password success");
              						  }
               }
               );
                //document.getElementById("operate_result_info").style.display = "block";
            }
            //删除
            function deleteAdmin(id) {
                var r = window.confirm("确定要删除此管理员吗？");
                if(r){
                window.location.href="deleteAdmin.action?id="+id;
                }
                document.getElementById("operate_result_info").style.display = "block";
            }
            //全选
            function selectAdmins(inputObj) {
                var inputArray = document.getElementById("datalist").getElementsByTagName("input");
                for (var i = 1; i < inputArray.length; i++) {
                    if (inputArray[i].type == "checkbox") {
                        inputArray[i].checked = inputObj.checked;
                    }
                }
            }
            
            function topage(id){
            document.getElementById("page").value = id;
            document.getElementById("adminform").submit();
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
				<li>
					<a href="login.action" class="index_off"></a>
				</li>
				<li>
					<a href="../role/toRole.action" class="role_off"></a>
				</li>
				<li>
					<a href="../admin/findAdmin.action" class="admin_on"></a>
				</li>
				<li>
					<a href="../cost/findCost.action" class="fee_off"></a>
				</li>
				<li>
					<a href="../account/findaccount.action" class="account_off"></a>
				</li>
				<li>
					<a href="../service/findService.action" class="service_off"></a>
				</li>
				<li>
					<li><a href="../serverconfig/toconfiginfo.action" class="bill_off"></a>
				</li>
               	<li><a href="../privilege/findPrivilege.action" class="report_off"></a></li>
				<li><a href="../admin/findAdminById.action" class="information_off"></a></li>
				<li><a href="../admin/tomodpasswd.action" class="password_off"></a></li>
			</ul>
		</div>
		<!--导航区域结束-->
		<!--主要区域开始-->
		<div id="main">
			<form action="findAdmin.action" method="post" id="adminform">
			<s:hidden name="page" id="page"></s:hidden>
				<!--查询-->
				<div class="search_add">
					<div>
						模块：
						<select id="selModules" class="select_search" name="privilegeid">
							<option value="0">
								全部
							</option>
							<option value="1">
								角色管理
							</option>
							<option value="2">
								管理员管理
							</option>
							<option value="3">
								资费管理
							</option>
							<option value="4">
								账务账号
							</option>
							<option value="5">
								业务账号
							</option>
							<option value="6">
								账单管理
							</option>
							<option value="7">
								报表
							</option>
						</select>
					</div>
					<div>
						角色：
						<s:select list="roles" listKey="id" listValue="name" headerValue="无验证" headerKey="0"  name="roleid" cssClass="text_search width200"></s:select>
					</div>
					<div>
						<input type="button" value="搜索" class="btn_search" onclick="topage(1);" />
					</div>
					<input type="button" value="密码重置" class="btn_add"
						onclick="resetPwd();" />
					<input type="button" value="增加" class="btn_add"
						onclick="location.href='toAddAdmin.action';" />
				</div>
				<!--删除和密码重置的操作提示-->
				<div id="operate_result_info" class="operate_fail">
					<img src="../images/close.png"
						onclick="this.parentNode.style.display='none';" />
					<span>删除成功！</span>
					<!--密码重置失败！数据并发错误。-->
				</div>
				<!--数据区域：用表格展示数据-->
				<div id="data">
				<br />
					<table id="datalist">
						<tr>
							<th class="th_select_all">
								<input type="checkbox" onclick="selectAdmins(this);" />
								<span>全选</span>
							</th>
							<th>
								管理员ID
							</th>
							<th>
								姓名
							</th>
							<th>
								登录名
							</th>
							<th>
								电话
							</th>
							<th>
								电子邮件
							</th>
							<th>
								授权日期
							</th>
							<th class="width100">
								拥有角色
							</th>
							<th></th>
						</tr>
						<s:iterator value="admins">
						<tr>
							<td>
								<input type="checkbox" name="selectadmin" />
							</td>
							<td>
								<s:property value="id"/>
							</td>
							<td>
								<s:property value="name"/>
							</td>
							<td>
								<s:property value="adminCode"/>
							</td>
							<td>
								<s:property value="telephone"/>
							</td>
							<td>
								<s:property value="email"/>
							</td>
							<td>
								<s:property value="enrollDate"/>
							</td>
							<td>
								<a class="summary" onmouseover="showDetail(true,this);"
									onmouseout="showDetail(false,this);"><s:property value="rolenames.substring(0,4)"/></a>
								<!--浮动的详细信息-->
								<div class="detail_info">
									<s:property value="rolenames"/>
								</div>
							</td>
							<td class="td_modi">
								<input type="button" value="修改" class="btn_modify"
									onclick="location.href='toUpdate.action?id=<s:property value="id"/>';" />
								<input type="button" value="删除" class="btn_delete"
									onclick="deleteAdmin(<s:property value="id"/>);" />
							</td>
						</tr>
						</s:iterator>
					</table>
				</div>
				<!--分页-->
				<div id="pages">
				</div>
			</form>
		</div>
		<!--主要区域结束-->
	        <div id="footer">
  			<span>齐鲁工业大学。二零一四届毕业生课程设计。基于Java的电信行业计费系统的设计与实现</span>
        </div>
	</body>
</html>
