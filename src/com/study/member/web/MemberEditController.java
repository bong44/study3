package com.study.member.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.code.service.CommonCodeServiceImpl;
import com.study.code.service.ICommonCodeService;
import com.study.code.vo.CodeVO;
import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizNotFoundException;
import com.study.member.service.IMemberService;
import com.study.member.service.MemberServiceImpl;
import com.study.member.vo.MemberVO;
import com.study.servlet.IController;

public class MemberEditController implements IController{

	//TODO 미검증

	private IMemberService memberService = new MemberServiceImpl();
	private ICommonCodeService codeService = new CommonCodeServiceImpl();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ResultMessageVO messageVO = new ResultMessageVO();
		
	 	try{
		 	String memId = req.getParameter("memId");
		 	MemberVO mem = memberService.getMember(memId);
		 	req.setAttribute("mem", mem);
	 	}catch(BizNotFoundException ex){
	 		messageVO.setResult(true).setTitle("글 조회 실패").setMessage("해당 회원이 존재하지 않습니다").setUrl("/member/memberList.wow").setUrlTitle("목록으로");
	 		req.setAttribute("messageVO", messageVO);
	 		return "/WEB-INF/views/common/message.jsp";
	 	}
	 	
	 	List<CodeVO> jobList = codeService.getCodeListByParent("JB00");
	 	List<CodeVO> hobList = codeService.getCodeListByParent("HB00");
	 	req.setAttribute("jobList", jobList);
	 	req.setAttribute("hobList", hobList);
	 	
	 	return "/WEB-INF/views/member/memberEdit.jsp";
	 	
	}
	
}
