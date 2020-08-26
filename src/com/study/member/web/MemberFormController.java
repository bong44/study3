package com.study.member.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.code.service.CommonCodeServiceImpl;
import com.study.code.service.ICommonCodeService;
import com.study.code.vo.CodeVO;
import com.study.servlet.IController;

public class MemberFormController implements IController{

	private ICommonCodeService codeService = new CommonCodeServiceImpl();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		List<CodeVO> jobList = codeService.getCodeListByParent("JB00");
	 	List<CodeVO> hobList = codeService.getCodeListByParent("HB00");
	 	req.setAttribute("jobList", jobList);
	 	req.setAttribute("hobList", hobList);
		
		return "/WEB-INF/views/member/memberForm.jsp";
	}
	
}
