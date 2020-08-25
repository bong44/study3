package com.study.login.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.common.util.CookieUtils;
import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizNotFoundException;
import com.study.exception.BizPasswordNotMatchedException;
import com.study.login.service.ILoginService;
import com.study.login.service.LoginServiceImpl;
import com.study.login.vo.UserVO;
import com.study.servlet.IController;

public class LoginController implements IController{
	
	private ILoginService loginService = new LoginServiceImpl();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//요철 메소드가 GET : Login 화면 , POST : LoginCheck 를 할 거에요
		if ("GET".equals(req.getMethod())) {
			return "/WEB-INF/views/login/login.jsp";
		}else if ("POST".equals(req.getMethod())){
			UserVO vo = new UserVO();
			vo.setUserId(req.getParameter("userId"));
			vo.setUserPass(req.getParameter("userPass"));
			
			try {
				UserVO userVO = loginService.loginCheck(vo);
				HttpSession session = req.getSession();
				
				if(req.getParameter("rememberMe")!=null){
					if(req.getParameter("rememberMe").equals("Y")){
						Cookie cookie = CookieUtils.createCookie("SAVE_ID",vo.getUserId());
						resp.addCookie(cookie);
					}
				}else{
					Cookie cookie = CookieUtils.createCookie("SAVE_ID","","/",0);
					/* maxAge에 0을 주면 삭제 */
				    resp.addCookie(cookie);
				}
				//현재 사용자 정보를 세션에 저장
				System.out.print("세션에 정보저장 :" +userVO);
				session.setAttribute("USER_INFO", userVO);
				
				return "redirect:/";
			} catch (BizNotFoundException | BizPasswordNotMatchedException | IOException e) {
				e.printStackTrace();
				ResultMessageVO message = new ResultMessageVO();
				message.setResult(false).setTitle("로그인 실패").setMessage("회원이 존재하지 않거나ㅣ 비밀번호가 틀립니다.");
				return "/WEB-INF/views/common/message.jsp";
			}
			
		}else {
			throw new ServletException("지원하지 않는 메소드 요청입니다.");
		}
		
	}	
	
}
