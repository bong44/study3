<%@page import="com.study.exception.DaoException"%>
<%@page import="com.study.free.service.FreeBoardServiceImpl"%>
<%@page import="com.study.free.service.IFreeBoardService"%>
<%@page import="com.study.exception.BizDuplicateKeyException"%>
<%@page import="com.study.exception.BizNotEffectedException"%>
<%@page import="com.study.member.service.IMemberService"%>
<%@page import="com.study.exception.BizNotFoundException"%>
<%@page import="com.study.member.vo.MemberVO"%>
<%@page import="com.study.member.service.MemberServiceImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <% request.setCharacterEncoding("utf-8"); %> 
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@ include file="/WEB-INF/inc/header.jsp" %>
	<title>freeRegist.jsp </title>
</head>
<body>
<%@ include file="/WEB-INF/inc/top.jsp" %>
	<h3>글 작성</h3>
	<jsp:useBean id="free" class="com.study.free.vo.FreeBoardVO"></jsp:useBean>
	<jsp:setProperty property="*" name="free"/>		
<%
IFreeBoardService boardService = new FreeBoardServiceImpl();
free.setBoIp(request.getRemoteAddr());
free.setBoHit(0);
free.setBoDelYn("N");

try{
	boardService.registBoard(free);
		%>
			<div class="alert alert-warning">
				<h4>글 등록 완료.</h4>
				정상적으로 글이 등록되었습니다
			</div>
		<%
}catch(DaoException ex){
		%>
			<div class="alert alert-warning">
				<h4>글 등록에 실패하였습니다.</h4>
				글을 다시 작성해주세요.
			</div>
		<%
}

%>
</body>
</html>