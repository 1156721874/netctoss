<%response.setStatus(200);%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
		<head>
				<title></title>
		</head>
<body style="background-image:url(../images/404/404backimg.jpg) ">
<h1 align="center">404</h1>
</body>
</html>