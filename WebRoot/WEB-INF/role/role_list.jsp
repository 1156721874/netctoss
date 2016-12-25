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
        <script language="javascript" type="text/javascript">
            function deleteRole(id) {
                var r = window.confirm("确定要删除此角色吗？");
                if(r){
                document.getElementById("operate_result_info").style.display = "block";
                window.location.href="deleterole.action?id="+id;
                }
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
            <form action="" method="">
                <!--查询-->
                <div class="search_add">
                    <input type="button" value="增加" class="btn_add" onclick="location.href='toAddRole.action';" />
                </div>  
                <!--删除的操作提示-->
                <div id="operate_result_info" class="operate_success">
                    <img src="../images/close.png" onclick="this.parentNode.style.display='none';" />
                    删除成功！
                </div> <!--删除错误！该角色被使用，不能删除。-->
                <!--数据区域：用表格展示数据-->     
                <div id="data">                      
                    <table id="datalist">
                        <tr>                            
                            <th>角色 ID</th>
                            <th>角色名称</th>
                            <th class="width600">拥有的权限</th>
                            <th class="td_modi"></th>
                        </tr>  
                        <s:iterator value="roles">       
                        <tr>
                            <td><s:property value="id"/> </td>
                            <td><s:property value="name"/></td>
                            <td><s:property value="privilegenames"/></td>
                            <td>
                                <input type="button" value="修改" class="btn_modify" onclick="location.href='toUpdateRole.action?id=<s:property value="id"/>';"/>
                                <input type="button" value="删除" class="btn_delete" onclick="deleteRole(<s:property value="id"/>);" />
                            </td>
                        </tr>
                        </s:iterator>             
                    </table>
                </div> 
                <!--分页-->
                <div id="pages">
        	        		<s:if test="page==1">
        	        <a href="#">上一页</a>
        	        						</s:if>
						        	        <s:else >
						        <a href="toRole.action?page=<s:property value='page-1'/>">上一页</a>
						        	    			</s:else >
        	        					
        	        		 <s:iterator var="p" begin="1" end="totalpage" step="1">
        	        											<s:if test="page==#p">
		                    <a href="toRole.action?page=<s:property value="#p"/>" class="current_page"><s:property value="#p"/> </a>
		                    								</s:if>
		                    						<s:else>
		                    <a href="toRole.action?page=<s:property value="#p"/>"><s:property value="#p"/></a>
		                    							</s:else>
                   </s:iterator>
                    <s:if test="page == totalpage" >
                    <a href="#">下一页</a>
                   </s:if>
                          <s:else>
						        	       <a href="toRole.action?page=<s:property value='page+1'/>">下一页</a>
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
