package com.study.mypage.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizNotFoundException;
import com.study.login.vo.UserVO;
import com.study.member.service.IMemberService;
import com.study.member.service.MemberServiceImpl;
import com.study.member.vo.MemberVO;
import com.study.servlet.IController;

public class InfoController implements IController{

	private IMemberService memberService = new MemberServiceImpl();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ResultMessageVO messageVO = new ResultMessageVO();
		
	 	try{
	 		HttpSession session = req.getSession();
	 		UserVO user = (UserVO)session.getAttribute("USER_INFO");
	 		//로그인이 되지 않은 경우는 홈 또는 로그인 화면으로
	 		//동일한 기능을 필터로 하면 좋곘다
//	 		if(user == null) {
//	 			return "redirect:/"
//	 		}
		 	String memId = user.getUserId();
		 	MemberVO mem = memberService.getMember(memId);
		 	req.setAttribute("mem", mem);
		 	return "/WEB-INF/views/mypage/info.jsp";
	 	}catch(BizNotFoundException ex){
	 		messageVO.setResult(true).setTitle("회원정보 표시 실패").setMessage("해당 회원이 존재하지 않습니다").setUrl("/member/memberList.wow").setUrlTitle("목록으로");
	 		req.setAttribute("messageVO", messageVO);
	 	
	 		return "/WEB-INF/views/common/message.jsp";
	 	}
		
	}
	
}
