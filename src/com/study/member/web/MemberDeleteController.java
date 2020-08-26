package com.study.member.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizNotFoundException;
import com.study.member.service.IMemberService;
import com.study.member.service.MemberServiceImpl;
import com.study.member.vo.MemberVO;
import com.study.servlet.IController;

public class MemberDeleteController implements IController{

	private IMemberService memberService = new MemberServiceImpl();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		MemberVO member = new MemberVO();
		ResultMessageVO messageVO = new ResultMessageVO();
		
		BeanUtils.populate(member, req.getParameterMap());
		
		try{
			memberService.removeMember(member);
			messageVO.setResult(true).setTitle("회원정보 삭제 완료").setMessage("회원정보 삭제가 완료되었습니다").setUrl("/member/memberList.wow").setUrlTitle("목록으로");
		}catch(BizNotFoundException ex){
			ex.printStackTrace();
			messageVO.setResult(true).setTitle("회원이 존재하지 않습니다").setMessage("다시 시도해주세요").setUrl("/member/memberList.wow").setUrlTitle("목록으로");
		}
		req.setAttribute("messageVO", messageVO);

		return "/WEB-INF/views/common/message.jsp";
		
	}
	
}
