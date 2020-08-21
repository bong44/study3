<%@page import="com.study.exception.BizPasswordNotMatchedException"%>
<%@page import="com.study.exception.BizNotFoundException"%>
<%@page import="com.study.exception.BizNotEffectedException"%>
<%@page import="com.study.exception.DaoException"%>
<%@page import="com.study.free.service.FreeBoardServiceImpl"%>
<%@page import="com.study.free.service.IFreeBoardService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	request.setCharacterEncoding("utf-8");
%>
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
	<jsp:useBean id="free" class="com.study.free.vo.FreeBoardVO"></jsp:useBean>
	<jsp:setProperty property="*" name="free"/>		
<%
IFreeBoardService boardService = new FreeBoardServiceImpl();

try{
	boardService.removeBoard(free);
		%>
			<div class="alert alert-warning">
				<h4>글 삭제 완료.</h4>
				정상적으로 글이 삭제되었습니다
			</div>
		<%
}catch(BizNotEffectedException ex){
	%>
	<div class="alert alert-warning">
		<h4>삭제가 안 되었습니다</h4>
		다시 삭제 해주세요!
	</div>
<%
}catch(BizNotFoundException ex){
%>
	<div class="alert alert-warning">
		<h4>글이 존재하지 않습니다</h4>
		글이 없습니다 
	</div>
<%
}catch(BizPasswordNotMatchedException ex){
	%>
	<div class="alert alert-warning">
		<h4>비밀번호가 맞지 않습니다</h4>
		비밀번호를 확인해 주세요! 
		<a href="#" onclick="history.back()" class="btn btn-default">뒤로가기</a>
	</div>
<%
}
%>

</body>
</html>