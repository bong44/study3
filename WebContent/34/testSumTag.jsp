<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="mytag" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/inc/header.jsp" %>
<title>34/ testSumTest.jsp</title>
</head>
<body>
<%@include file="/WEB-INF/inc/top.jsp" %>
<div class="container">
	1 부터 10 까지의 합<br>
	<mytag:sum begin="1" end="10" var="bong" />
	<hr>
	${bong}
</div><!--container-->
</body>
</html>