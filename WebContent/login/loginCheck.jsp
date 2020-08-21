<%@page import="com.study.common.util.CookieUtils"%>
<%@page import="com.study.login.vo.UserVO"%>
<%@page import="com.study.login.vo.UserList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	request.setCharacterEncoding("utf-8");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="/WEB-INF/inc/header.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/inc/top.jsp" %>
<%
	String id = request.getParameter("userId");
	String pw = request.getParameter("userPass");
	
	//1.널체크
	//jsp:forward , msg 파라미터에 "ID또는 비밀번호가 비었습니다."
	if(id == null || pw == null || id.isEmpty() || pw.isEmpty()){
		%>
		<jsp:forward page="login.jsp">
			<jsp:param value="ID 또는 비밀번호가 비었습니다." name="msg"/>
		</jsp:forward>
		<% 
	}
	
	//2.유저List에 id를 통해 조회
	UserList users = new UserList();
	UserVO use = users.getUser(id);
	
	if(use==null){
		%>
			<h3>해당 회원은 존재하지 않습니다.</h3>
			<a href="#" class="btn btn-default" onclick="history.go(-1)">뒤로가기</a>
		<%
	}else {
		if(use.getUserPass().equals(pw)){
	//조회된 객체가 존재하고 pw가 맞으면
	// response.sendRedirect 사용해서 "/" 또는 "/index.jsp"
			if(request.getParameter("rememberMe")!=null){
				if(request.getParameter("rememberMe").equals("Y")){
					Cookie cookie = CookieUtils.createCookie("SAVE_ID",id);
					
					response.addCookie(cookie);
				}
			}else{
				Cookie cookie = CookieUtils.createCookie("SAVE_ID","","/",0);
				/* maxAge에 0을 주면 삭제 */
			    response.addCookie(cookie);
			}
			//현재 사용자 정보를 세션에 저장
			System.out.print("세션에 정보저장 :" +use);
			session.setAttribute("USER_INFO", use);
			response.sendRedirect(request.getContextPath()+"/");
	
		}else{
	//조회된 객체가 존재하고 pw가 틀리면
	// pageContext 의 forward 사용해서 "비밀번호를 확인해 주세요"
			pageContext.forward("login.jsp?msg=비밀번호를 확인해 주세요");
		}
	}
	
	
	
%>



</body>
</html>