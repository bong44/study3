<%@page import="com.study.member.service.IMemberService"%>
<%@page import="com.study.exception.BizNotFoundException"%>
<%@page import="com.study.member.vo.MemberVO"%>
<%@page import="com.study.member.service.MemberServiceImpl"%>
<%@page import="org.apache.catalina.tribes.membership.McastServiceImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <% request.setCharacterEncoding("utf-8"); %> 
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@ include file="/WEB-INF/inc/header.jsp" %>
	<title>memberForm.jsp </title>
</head>
<body>
<%@ include file="/WEB-INF/inc/top.jsp" %>
 <div class="container">	
 <%
 	IMemberService memberService = new MemberServiceImpl();
 	try{
	 	String memId = request.getParameter("memId");
	 	MemberVO mem = memberService.getMember(memId);
	 	request.setAttribute("mem", mem);
 	}catch(BizNotFoundException ex){
 		request.setAttribute("ex", ex);
 	}
 	
 %>
 	<h3>회원 상세 정보</h3>
 	<c:if test="${not empty ex}">
 		<div class="alert alert-warning"></div>
 		<p>해당 회원이 존재하지 않습니다</p>
 		<a href="memberList.jsp" class="btn btn-default btn-sm">
 		<span class="glyphicon glyphicon-list" aria-hidden="true"></span>
 		&nbsp;목록
 		</a>
 	</c:if>
 	<c:if test="${empty ex}">
	<h3>회원가입</h3>		
	<table class="table table-striped">
		<tbody>
			<tr>
				<th>아이디</th>
				<td>${mem.memId}</td>
			</tr>
			<tr>
				<th>회원명</th>
				<td>${mem.memName}</td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td>${mem.memZip}</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>
				${mem.memAdd1}
				${mem.memAdd2}
				</td>
			</tr>
			<tr>
			<tr>
				<th>생일</th>
				<td>${mem.memBir}</td>
			</tr>
			<tr>
				<th>메일</th>
				<td>${mem.memMail}</td>
			</tr>
			<tr>
				<th>헨드폰</th>
				<td>${mem.memHp}</td>
			</tr>
			<tr>
				<th>직업</th>
				<td>${mem.memJobNm}</td>
			</tr>
			<tr>
				<th>취미</th>
				<td>${mem.memLikeNm}</td>
			</tr>
			<tr>
				<td>마일리지</td>
				<td>${mem.memMileage}</td>
			</tr>			
			<tr>
				<td>탈퇴여부</td>
				<td>${mem.memDelete}</td>
			</tr>			
			<tr>
				<td colspan="2">
					<a href="memberList.jsp" class="btn btn-default btn-sm">
					<span class="glyphicon glyphicon-list" aria-hidden="true"></span>
					&nbsp;목록
					</a>
					<a href="memberEdit.jsp?memId=<%=request.getParameter("memId") %>" class="btn btn-default btn-sm">
					<span class="glyphicon glyphicon-list" aria-hidden="true"></span>
					&nbsp;수정
					</a>
				</td>
				
			</tr>
		</tbody>	
	</table>
 	</c:if>
</div>

</body>
</html>