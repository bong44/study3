package com.study.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.code.service.CommonCodeServiceImpl;
import com.study.code.service.ICommonCodeService;
import com.study.code.vo.CodeVO;
import com.study.free.service.FreeBoardServiceImpl;
import com.study.free.service.IFreeBoardService;
import com.study.free.vo.FreeBoardSearchVO;
import com.study.free.vo.FreeBoardVO;

public class SimpleController extends HttpServlet{
	
	//public static void main(String[] args) {
	//	String cp = "/study3";
	//	String t = "/study3/free/freeList.wow";
	//	System.out.println(t.substring(7));
	//}
	
	IFreeBoardService freeBoardService = new FreeBoardServiceImpl();
	ICommonCodeService codeService = new CommonCodeServiceImpl();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//공통기능 처리
		req.setCharacterEncoding("utf-8");

		//2. 요청 분석 (uri) ???????
		String uri = req.getRequestURI(); // /study3/free/freeList.wow <-- 이렇게 보인다는데
		String viewPage = "";
		//경로추출
		//String cp = "/study3";  == getContextPath  몇번째 글자까지 자를지
		//String t = "/study3/free/freeList.wow";
		//System.out.println(t.substring(cp.length));         <<--로 원하는 라인만 가져올 수 있음
		//
		//위 로직 실제 활용
		uri = uri.substring(req.getContextPath().length());  // == /free/freeList.wow
		System.out.println("uri= " + uri);
		
		// 3. 모델를 사용하여 처리
		// 4. 결과를 속성에 저장
		if ("/free/freeList.wow".equals(uri)) {
			FreeBoardSearchVO searchVO = new FreeBoardSearchVO();
			List<FreeBoardVO> free = freeBoardService.getBoardList(searchVO);
			req.setAttribute("free", free);

			List<CodeVO> prList = codeService.getCodeListByParent("BC00");
			req.setAttribute("prList", prList);
			viewPage = "/free/freeList.jsp";
			
		}else if ("/free/freeView.wow".equals(uri)) {
			FreeBoardVO free;
			try {
				free = freeBoardService.getBoard(Integer.parseInt(req.getParameter("boNo")));
				req.setAttribute("free", free);
				if (free != null) {
					freeBoardService.increaseHit(Integer.parseInt(req.getParameter("boNo")));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
			viewPage = "/free/freeView.jsp";
		}else if ("/free/freeForm.wow".equals(uri)) {
		 	List<CodeVO> bList = codeService.getCodeListByParent("BC00");
		 	req.setAttribute("bList", bList);
		 	
		 	viewPage = "/free/freeForm.jsp";
		}
		System.out.println("viewPage= "+viewPage);
		// 5. 뷰페이지 이동
		RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
		dispatcher.forward(req, resp);
		
	}

}
