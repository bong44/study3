<%@page import="com.study.code.vo.CodeVO"%>
<%@page import="com.study.code.service.CommonCodeServiceImpl"%>
<%@page import="com.study.code.service.ICommonCodeService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="mytag" tagdir="/WEB-INF/tags"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/inc/header.jsp"%>
<title>Insert title here</title>
</head>
<body>
	<%@include file="/WEB-INF/inc/top.jsp"%>
	<div class="container">
<%
	ICommonCodeService codeService = new CommonCodeServiceImpl();
	List<CodeVO> jobs = codeService.getCodeListByParent("JB00");
	pageContext.setAttribute("jobs", jobs);
	pageContext.setAttribute("myJob", "JB06");
%>
		<select name="memJob" required="required" class="form-control input-sm" id="id_memJob">
			<option value="">-- 직업 선택 --</option>
			<c:forEach items="${jobs}" var="job">
				<option value="${job.commCd}" ${job.commCd eq myJob ? "selected='selected'":"" }>${job.commNm}</option>
			</c:forEach>
		</select> 
		
		<mytag:select 
		items="${jobs}" 
		name="memJob" 
		itemLabel="commNm"		 
		itemValue="commCd"	
		value="${myJob}"	
		required="required" 
		class="form-control input-sm" 
		id="id_memJob" 
			>
			<option value="">----직업선택-----</option>>
			</mytag:select>
	</div><!--container-->
</body>
</html>