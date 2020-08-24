package com.study.free.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.code.service.CommonCodeServiceImpl;
import com.study.code.service.ICommonCodeService;
import com.study.free.service.FreeBoardServiceImpl;
import com.study.free.service.IFreeBoardService;
import com.study.free.vo.FreeBoardVO;
import com.study.servlet.IController;

public class FreeViewController implements IController{
	
	IFreeBoardService freeBoardService = new FreeBoardServiceImpl();
	ICommonCodeService codeService = new CommonCodeServiceImpl();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		try {
			FreeBoardVO free = freeBoardService.getBoard(Integer.parseInt(req.getParameter("boNo")));
			req.setAttribute("free", free);
			if (free != null) {
				freeBoardService.increaseHit(Integer.parseInt(req.getParameter("boNo")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return "/WEB-INF/views/free/freeView.jsp";
	}
}
