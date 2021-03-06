package com.study.free.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.code.service.CommonCodeServiceImpl;
import com.study.code.service.ICommonCodeService;
import com.study.code.vo.CodeVO;
import com.study.free.service.FreeBoardServiceImpl;
import com.study.free.service.IFreeBoardService;
import com.study.servlet.IController;

public class FreeFormController implements IController {

	IFreeBoardService freeBoardService = new FreeBoardServiceImpl();
	ICommonCodeService codeService = new CommonCodeServiceImpl();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ICommonCodeService codeService = new CommonCodeServiceImpl();
		List<CodeVO> categoryList = codeService.getCodeListByParent("BC00");
		req.setAttribute("bList", categoryList);
		return "/WEB-INF/views/free/freeForm.jsp";
	}

}
