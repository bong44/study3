<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%
    	request.setCharacterEncoding("UTF-8");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="/WEB-INF/inc/header.jsp" %>
</head>
<%
	//세션을 삭제하고, 새로운 세션을 생성
	session.invalidate();
%>
<body>
<%
	//세션을 삭제하고, 새로운 세션을 생성
	response.sendRedirect(request.getContextPath()+"/");
%>
<%-- <%@include file="/WEB-INF/inc/top.jsp" %> --%>
<h3>정상적으로 로그아웃 되었습니다.</h3>
</body>
</html>