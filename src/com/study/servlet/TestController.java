package com.study.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestController extends HttpServlet{
	
	///study3/command.soju?c=list : 자유게시판 목록
	///study3/command.soju?c=view&boNo=12 : 자유게시판 상세보기
	
	// 1. 클라이언트 요청을 받는다. (doGet, doPost, service)???
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//파라미터 체킹
		String cc = req.getParameter("c");
		String viewPage = "";
		
		switch (cc) {
		case "list": viewPage = "/free/freeList.jsp"; break;
		case "view": viewPage = "/free/freeView.jsp"; break;
		default: viewPage = "/index.jsp";break;
		}
		
		//dispatcher의 역할?
		// 5. 뷰페이지로 이동 
		RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
		dispatcher.forward(req, resp);
		
	}

}
