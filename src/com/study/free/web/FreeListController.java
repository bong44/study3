package com.study.free.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.study.code.service.CommonCodeServiceImpl;
import com.study.code.service.ICommonCodeService;
import com.study.code.vo.CodeVO;
import com.study.free.service.FreeBoardServiceImpl;
import com.study.free.service.IFreeBoardService;
import com.study.free.vo.FreeBoardSearchVO;
import com.study.free.vo.FreeBoardVO;
import com.study.servlet.IController;

public class FreeListController implements IController{
	
	IFreeBoardService freeBoardService = new FreeBoardServiceImpl();
	ICommonCodeService codeService = new CommonCodeServiceImpl();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		
		FreeBoardSearchVO searchVO = new FreeBoardSearchVO();
		BeanUtils.populate(searchVO, req.getParameterMap());
		
		List<FreeBoardVO> free = freeBoardService.getBoardList(searchVO);
		req.setAttribute("free", free);
		req.setAttribute("searchVO", searchVO);

		List<CodeVO> prList = codeService.getCodeListByParent("BC00");
		req.setAttribute("prList", prList);
		
		return "/WEB-INF/views/free/freeList.jsp";
	}

}
