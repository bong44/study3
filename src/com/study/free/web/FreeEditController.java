package com.study.free.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.code.service.CommonCodeServiceImpl;
import com.study.code.service.ICommonCodeService;
import com.study.code.vo.CodeVO;
import com.study.exception.BizNotFoundException;
import com.study.free.service.FreeBoardServiceImpl;
import com.study.free.service.IFreeBoardService;
import com.study.free.vo.FreeBoardVO;
import com.study.servlet.IController;

public class FreeEditController implements IController {

	IFreeBoardService freeBoardService = new FreeBoardServiceImpl();
	ICommonCodeService codeService = new CommonCodeServiceImpl();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		try{
			FreeBoardVO free = freeBoardService.getBoard(Integer.parseInt(req.getParameter("boNo")));
			req.setAttribute("free", free);
	 	}catch(BizNotFoundException ex){
	 		req.setAttribute("ex", ex);
	 	}
		List<CodeVO> bList = codeService.getCodeListByParent("BC00");
	 	req.setAttribute("bList", bList);
		
		return "/WEB-INF/views/free/freeEdit.jsp";
	}

}
