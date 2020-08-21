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
 <div class="container">	
	<jsp:useBean id="member" class="com.study.member.vo.MemberVO"></jsp:useBean>
	<jsp:setProperty property="*" name="member"/>		
<%
	IMemberService memberService = new MemberServiceImpl();
	try{
		memberService.modifyMember(member);
			%>
				<div class="alert alert-warning">
					<h4>수정 완료</h4>
					정상적으로 회원정보를 수정했습니다.
				</div>
			<%
	}catch(BizNotEffectedException ex){
			%>
				<div class="alert alert-warning">
					<h4>수정이 안 되었습니다</h4>
					아이디나 비밀번호를 확인해 주세요!
				</div>
			<%
	}catch(BizNotFoundException ex){
			%>
				<div class="alert alert-warning">
					<h4>회원이 존재하지 않습니다</h4>
					아이디나 비밀번호를 확인해 주세요! 
				</div>
			<%
	}
	
%>
		<a href="memberList.jsp" class="btn btn-default btn-sm">
		<span class="glyphicon glyphicon-list" aria-hidden="true"></span>
		&nbsp;목록
		</a>
	</div>
</body>
</html>