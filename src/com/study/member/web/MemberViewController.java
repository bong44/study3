package com.study.member.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizNotFoundException;
import com.study.member.service.IMemberService;
import com.study.member.service.MemberServiceImpl;
import com.study.member.vo.MemberVO;
import com.study.servlet.IController;

public class MemberViewController implements IController{

	private IMemberService memberService = new MemberServiceImpl();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ResultMessageVO messageVO = new ResultMessageVO();
		
	 	try{
		 	String memId = req.getParameter("memId");
		 	MemberVO mem = memberService.getMember(memId);
		 	req.setAttribute("mem", mem);
	 	}catch(BizNotFoundException ex){
	 		messageVO.setResult(true).setTitle("회원정보 표시 실패").setMessage("해당 회원이 존재하지 않습니다").setUrl("/member/memberList.wow").setUrlTitle("목록으로");
	 		req.setAttribute("messageVO", messageVO);
	 	
	 		return "/WEB-INF/views/common/message.jsp";
	 	}
		
		return "/WEB-INF/views/member/memberView.jsp";
	}
	
}
