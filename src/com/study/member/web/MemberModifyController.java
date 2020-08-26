package com.study.member.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.member.service.IMemberService;
import com.study.member.service.MemberServiceImpl;
import com.study.member.vo.MemberVO;
import com.study.servlet.IController;

public class MemberModifyController implements IController {

	private IMemberService memberService = new MemberServiceImpl();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		MemberVO member = new MemberVO();
		ResultMessageVO messageVO = new ResultMessageVO();
		
		BeanUtils.populate(member, req.getParameterMap());
		
		try {
			memberService.modifyMember(member);
			messageVO.setResult(true).setTitle("회원정보 수정 완료").setMessage("회원정보 수정이 성공적으로 이루어졌습니다").setUrl("/member/memberList.wow").setUrlTitle("목록으로");
		} catch (BizNotEffectedException ex) {
			messageVO.setResult(true).setTitle("회원정보 수정 실패").setMessage("회원정보 수정 중 문제가 발생했습니다 다시 시도해주세요").setUrl("/member/memberList.wow").setUrlTitle("목록으로");
		} catch (BizNotFoundException ex) {
			messageVO.setResult(true).setTitle("회원정보 수정 실패").setMessage("해당 회원정보는 존재하지 않습니다 다시 시도해주세요").setUrl("/member/memberList.wow").setUrlTitle("목록으로");
		}
		req.setAttribute("messageVO", messageVO);

		return "/WEB-INF/views/common/message.jsp";
	}

}
