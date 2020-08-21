<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
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
	<h3>회원목록</h3>
	<jsp:useBean id="member" class="com.study.member.vo.MemberVO"></jsp:useBean>
	<jsp:setProperty property="*" name="member"/>		
<%
	//1.드라이버 로딩
	//변수 선언
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	//2.커넥션 구하기
	conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","java","oracle");
	
	//3.구문 객체 생성
	stmt = conn.createStatement(); //이게 기본형 (아무것도 설정하지 않았을때)
	
	StringBuffer sb = new StringBuffer();
	
	sb.append("   INSERT INTO member (                             ");
	sb.append("	    mem_id                                       ");
	sb.append("	    , mem_pass    , mem_name    , mem_bir        ");
	sb.append("	    , mem_zip    , mem_add1    , mem_add2        ");
	sb.append("	    , mem_hp    , mem_mail    , mem_job          ");
	sb.append("	    , mem_like    , mem_mileage    , mem_delete  ");
	sb.append("	) VALUES (                                       ");
	sb.append("	    	'"+member.getMemId()+"'    , '"+member.getMemPass()+"'    , '"+member.getMemName()+"'                        ");
	sb.append("	    ,  '"+member.getMemBir()+"'    , '"+member.getMemZip()+"'    , '"+member.getMemAdd1()+"'                      ");
	sb.append("	    ,  '"+member.getMemAdd2()+"'    , '"+member.getMemHp()+"'    , '"+member.getMemMail()+"'                      ");
	sb.append("	    ,  '"+member.getMemJob()+"'    , '"+member.getMemLike()+"'    , 0                       ");
	sb.append("	    , 'N'                                       ");
	sb.append("	)                                                ");
	
	int cnt = stmt.executeUpdate(sb.toString());
	if(cnt > 0){
		%>
			<div class="alert alert-success">
				정상적으로 회원이 등록되었습니다!!
			</div>
		<%
	}
	if(conn!=null){try{conn.close();}catch(SQLException e){e.printStackTrace();}}
	if(rs!=null){try{rs.close();}catch(SQLException e){e.printStackTrace();}}
	if(stmt!=null){try{stmt.close();}catch(SQLException e){e.printStackTrace();}}
%>
</body>
</html>