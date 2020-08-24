<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/inc/header.jsp" %>
<title>Insert title here</title>
</head>
<body>
<%@include file="/WEB-INF/inc/top.jsp" %>
	<h3>글 작성</h3>
	<c:if test="${empty ex1 and empty ex2}">
		<div class="alert alert-warning">
			<h4>글 삭제 완료.</h4>
			정상적으로 글이 삭제되었습니다
		</div>
	</c:if>	
	<c:if test="${not empty ex1}">
		<div class="alert alert-warning">
			<h4>글이 존재하지 않습니다</h4>
			글이 없습니다 
		</div>
	</c:if>
	<c:if test="${not empty ex2}">
		<div class="alert alert-warning">
			<h4>비밀번호가 맞지 않습니다</h4>
			비밀번호를 확인해 주세요! 
			<a href="#" onclick="history.back()" class="btn btn-default">뒤로가기</a>
		</div>
	</c:if>
</body>
</html>