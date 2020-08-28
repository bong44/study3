<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="mytag" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/inc/header.jsp" %>
<title>34/ testLoveTest.jsp</title>
</head>
<body>
<%@include file="/WEB-INF/inc/top.jsp" %>
<div class="container">
	<mytag:love count="3">
		밀키스
	</mytag:love>
</div><!--container-->
</body>
</html>