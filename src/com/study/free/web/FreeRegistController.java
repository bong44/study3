package com.study.free.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.study.code.service.CommonCodeServiceImpl;
import com.study.code.service.ICommonCodeService;
import com.study.common.vo.ResultMessageVO;
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
		ResultMessageVO messageVO = new ResultMessageVO();
		
		//<jsp:setProperty property="*" name="free"/>		// <-- BeanUtils.populate 로 대체가능
		//board.setBoTitle(req.getParameter("boTitle")); //이거를 편하게 하기위해 BeanUtils의 메소드를 사용
		BeanUtils.populate(board, req.getParameterMap());
		
		try {
			board.setBoIp(req.getRemoteAddr());
			freeBoardService.registBoard(board);
			// 글 입력 성공시 메세지를 보여줄 필요 없이 바로 목록으로 돌아가기 (redirect는 쓸 때 :을 붙여주기로 spring에서 약속함)
			return "redirect:/free/freeList.wow";
			
//			messageVO.setResult(true).setTitle("글 등록 성공").setMessage("작성하신 내용이 저장되었습니다").setUrl("/free/freeList.wow").setUrlTitle("목록으로");
		} catch (DaoDuplicateKeyException e) {
			e.printStackTrace();
			messageVO.setResult(true).setTitle("글 등록 실패").setMessage("해당 글번호가 존재합니다.").setUrl("/free/freeList.wow").setUrlTitle("목록으로");
		}
		req.setAttribute("messageVO", messageVO);
		
		return "/WEB-INF/views/common/message.jsp";
	}

}
