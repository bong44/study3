<%@page import="com.study.common.vo.PagingVO"%>
<%@page import="com.study.free.vo.FreeBoardSearchVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="mytag" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/inc/header.jsp" %>
<title>34/ testSumTest.jsp</title>
</head>
<body>
<%@include file="/WEB-INF/inc/top.jsp" %>
<div class="container">
<%
	FreeBoardSearchVO searchVO = new FreeBoardSearchVO();
	searchVO.setTotalRowCount(53);
	searchVO.pageSetting();
	request.setAttribute("searchVO",searchVO);
	int pNum = Integer.parseInt(request.getParameter("curPage"));
	//searchVO.setCurPage(pNum);
	PagingVO paVo = new PagingVO();
%>
<nav class="text-center">
	
	<mytag:paging pagingVO="${searchVO}" linkPage="testPagingTag.jsp?curPage=" />
</nav>
</div><!--container-->
</body>
</html>