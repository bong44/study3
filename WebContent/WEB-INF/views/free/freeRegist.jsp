<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@ include file="/WEB-INF/inc/header.jsp" %>
	<title>freeRegist.jsp </title>
</head>
<body>
<%@ include file="/WEB-INF/inc/top.jsp" %>
	<h3>글 작성</h3>
	<c:if test="${empty ex}">
			<div class="alert alert-warning">
				<h4>글 등록 완료.</h4>
				정상적으로 글이 등록되었습니다
			</div>
	</c:if>
	<c:if test="${not empty ex}">
			<div class="alert alert-warning">
				<h4>글 등록에 실패하였습니다.</h4>
				글을 다시 작성해주세요.
			</div>
	</c:if>
</body>
</html>