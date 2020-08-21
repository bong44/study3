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
	<title>memberList.jsp </title>
</head>
<body>
<%@ include file="/WEB-INF/inc/top.jsp" %>
	<h3>회원가입</h3>
	<jsp:useBean id="member" class="com.study.member.vo.MemberVO"></jsp:useBean>
	<jsp:setProperty property="*" name="member"/>		
<%
IMemberService memberService = new MemberServiceImpl();
try{
	memberService.registMember(member);
		%>
			<div class="alert alert-warning">
				<h4>회원가입 완료.</h4>
				정상적으로 회원가입이 완료되었습니다.
			</div>
		<%
}catch(BizDuplicateKeyException ex){
		%>
			<div class="alert alert-warning">
				<h4>해당 아이디는 사용중 입니다.</h4>
				새로운 아이디를 입력해주세요!
			</div>
		<%
}

%>
</body>
</html>