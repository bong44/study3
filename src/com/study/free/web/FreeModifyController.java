package com.study.free.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.study.code.service.CommonCodeServiceImpl;
import com.study.code.service.ICommonCodeService;
import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizNotFoundException;
import com.study.exception.BizPasswordNotMatchedException;
import com.study.free.service.FreeBoardServiceImpl;
import com.study.free.service.IFreeBoardService;
import com.study.free.vo.FreeBoardVO;
import com.study.servlet.IController;

public class FreeModifyController implements IController {

	IFreeBoardService freeBoardService = new FreeBoardServiceImpl();
	ICommonCodeService codeService = new CommonCodeServiceImpl();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		FreeBoardVO board = new FreeBoardVO();
		ResultMessageVO messageVO = new ResultMessageVO();
		
		BeanUtils.populate(board, req.getParameterMap());
		try {
			freeBoardService.modifyBoard(board);
			return "redirect:/free/freeView.wow?boNo="+board.getBoNo();
		} catch (BizNotFoundException e) {
			e.printStackTrace();
			messageVO.setResult(true).setTitle("글 수정 실패").setMessage("글이 존재하지 않습니다").setUrl("/free/freeList.wow").setUrlTitle("목록으로");
		} catch (BizPasswordNotMatchedException e) {
			e.printStackTrace();
			messageVO.setResult(true).setTitle("글 수정 실패").setMessage("글 비밀번호가 일치하지 않습니다.").setUrl("/free/freeList.wow").setUrlTitle("목록으로");
		} 
		req.setAttribute("messageVO", messageVO);
		
		return "/WEB-INF/views/common/message.jsp";
	}

}
