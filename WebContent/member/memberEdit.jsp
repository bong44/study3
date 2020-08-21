<%@page import="com.study.code.service.CommonCodeServiceImpl"%>
<%@page import="com.study.code.vo.CodeVO"%>
<%@page import="com.study.code.dao.CommonCodeDaoOracle"%>
<%@page import="com.study.code.service.ICommonCodeService"%>
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
	<title>memberEdit.jsp </title>
</head>
<body>
<%@ include file="/WEB-INF/inc/top.jsp" %>
 <div class="container">
	<h3>회원정보 수정</h3>		
   <%
 	IMemberService memberService = new MemberServiceImpl();
 	try{
	 	String memId = request.getParameter("memId");
	 	MemberVO mem = memberService.getMember(memId);
	 	request.setAttribute("mem", mem);
 	}catch(BizNotFoundException ex){
 		request.setAttribute("ex", ex);
 	}
 	
 	
 	ICommonCodeService codeService = new CommonCodeServiceImpl();
 	List<CodeVO> jobList = codeService.getCodeListByParent("JB00");
 	List<CodeVO> hobList = codeService.getCodeListByParent("HB00");
 	request.setAttribute("jobList", jobList);
 	request.setAttribute("hobList", hobList);
 	
 	
 	
 	
 %>
	<c:if test="${not empty ex}">
 		<div class="alert alert-warning"></div>
 		<p>해당 회원이 존재하지 않습니다</p>
 		<a href="memberList.jsp" class="btn btn-default btn-sm">
 		<span class="glyphicon glyphicon-list" aria-hidden="true"></span>
 		&nbsp;목록
 		</a>
 	</c:if>
 	<c:if test="${empty ex}">
	<form action="memberModify.jsp" method="post" >
		<input value='${mem.memId}' name="memId" type="hidden">
	<table class="table table-striped">
		<tbody>
			<tr>
				<th>아이디</th>
				<td>${mem.memId}</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="memPass" class="form-control input-sm" 
				pattern="\w{4,}" title="알파벳과 숫자 4글자 이상 입력"></td>
			</tr>
			<tr>
				<th>회원명</th>
				<td><input type="text" name="memName" class="form-control input-sm" value='${mem.memName}' 
				required="required" pattern="[가-힣]{2,}" title="한글로 두 글자 이상 입력"></td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td><input type="text" name="memZip" class="form-control input-sm" value='${mem.memZip}' ></td>
			</tr>
			<tr>
				<th>주소</th>
				<td>
				<input type="text" name="memAdd1" class="form-control input-sm" value='${mem.memAdd1}' >
				<input type="text" name="memAdd2" class="form-control input-sm" value='${mem.memAdd2}' >
				</td>
			</tr>
			<tr>
			<tr>
				<th>생일</th>
				<td><input type="date" name="memBir" class="form-control input-sm" value='${mem.memBir}'></td>
			</tr>
			<tr>
				<th>메일</th>
				<td><input type="email" name="memMail" class="form-control input-sm" required="required" value='${mem.memMail}'></td>
			</tr>
			<tr>
				<th>헨드폰</th>
				<td><input type="tel" name="memHp" class="form-control input-sm" value="${mem.memHp}"></td>
			</tr>
			<tr>
				<th>직업</th>
				<td>
					<select name="memJob" >
						<option value="">-- 직업 선택 --</option>
						<c:forEach items="${jobList}" var="job">
							<option value="${job.commCd}" ${job.commCd eq mem.memJob ? "selected='selected'":"" }>${job.commNm}</option>
						</c:forEach>
					</select>				
				</td>
			</tr>
			<tr>
				<th>취미</th>
				<td>
					<select name="memLike" >
						<option value="">-- 취미 선택 --</option>
						<c:forEach items="${hobList}" var="hob">
							<option value="${hob.commCd}" ${hob.commCd eq mem.memLike ?"selected='selected'":""}>${hob.commNm}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>마일리지</td>
				<td>${mem.memMileage}</td>
			</tr>			
			<tr>
				<td>탈퇴여부</td>
				<td>
					<input type="radio" name="memDelete" value="Y" ${"Y" eq mem.memDelete ?"checked='checked'":"" }>Y
					<input type="radio" name="memDelete" value="N" ${"N" eq mem.memDelete ?"checked='checked'":"" }>N
					
				</td>
			</tr>			
			<tr>
				<td colspan="2">
					<a href="memberList.jsp" class="btn btn-default btn-sm">
					<span class="glyphicon glyphicon-list" aria-hidden="true"></span>
					&nbsp; 목록
					</a>
					<button type="submit" class="btn btn-primary">
						<span class="glyphicon glyphicon-heart-save" aria-hidden="true" ></span>
						저장
					</button>
				</td>
			</tr>
		</tbody>	
	</table>
	</form>
	</c:if>
</div>

</body>
</html>