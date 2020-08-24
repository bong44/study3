<%@page import="java.util.ArrayList"%>
<%@page import="com.study.member.vo.MemberVO"%>
<%@page import="com.study.member.service.MemberServiceImpl"%>
<%@page import="com.study.member.service.IMemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<h3>회원목록</h3>		
	<%
		IMemberService memberService = new MemberServiceImpl();
		List<MemberVO> members = memberService.getMemberList();
	%>
	<div>
		<a href="memberForm.jsp" class="btn btn-primary btn-sm pull-right">회원 등록</a>
	</div>
	<table class="table table-striped table-bordered">
		<caption class="hidden">회원목록 조회</caption>
		<colgroup>
			<col style="width: 15%;"/>
			<col />
			<col style="width: 15%;"/>
			<col style="width: 15%;"/>
			<col style="width: 15%;"/>
			<col style="width: 15%;"/>
		</colgroup>
		<thead>
			<tr>
				<th>ID</th>
				<th>회원명</th>
				<th>HP</th>
				<th>생일</th>
				<th>직업</th>
				<th>마일리지</th>
			</tr>
		</thead>
		<tbody>
		<%
			for(int i=0; i < members.size(); i++){
			MemberVO vo = members.get(i);
		%>
			<tr>
				<td><%=vo.getMemId() %></td>
				<td><a href="memberView.jsp?memId=<%=vo.getMemId()%>" ><%=vo.getMemName() %></a></td>
				<td><%=vo.getMemHp() %></td>
				<td><%=vo.getMemBir() %></td>
				<td><%=vo.getMemJob() %></td>
				<td><%=vo.getMemMileage() %></td>
			</tr>
		<%
			}// for
		%>
		</tbody>	
	</table>
</div>

</body>
</html>