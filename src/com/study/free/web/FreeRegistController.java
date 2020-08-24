package com.study.free.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.study.code.service.CommonCodeServiceImpl;
import com.study.code.service.ICommonCodeService;
import com.study.exception.DaoDuplicateKeyException;
import com.study.free.service.FreeBoardServiceImpl;
import com.study.free.service.IFreeBoardService;
import com.study.free.vo.FreeBoardVO;
import com.study.servlet.IController;

public class FreeRegistController implements IController {

	IFreeBoardService freeBoardService = new FreeBoardServiceImpl();
	ICommonCodeService codeService = new CommonCodeServiceImpl();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		//<jsp:useBean id="free" class="com.study.free.vo.FreeBoardVO"></jsp:useBean>
		FreeBoardVO board = new FreeBoardVO();
		
		//<jsp:setProperty property="*" name="free"/>		// <-- BeanUtils.populate 로 대체가능
		//board.setBoTitle(req.getParameter("boTitle")); //이거를 편하게 하기위해 BeanUtils의 메소드를 사용
		BeanUtils.populate(board, req.getParameterMap());
		try {
			board.setBoIp(req.getRemoteAddr());
			freeBoardService.registBoard(board);
		} catch (DaoDuplicateKeyException e) {
			e.printStackTrace();
			req.setAttribute("ex", e);
		}
		
		return "/WEB-INF/views/free/freeRegist.jsp";
	}

}
