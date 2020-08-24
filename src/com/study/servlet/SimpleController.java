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
import com.study.free.web.FreeDeleteController;
import com.study.free.web.FreeEditController;
import com.study.free.web.FreeFormController;
import com.study.free.web.FreeListController;
import com.study.free.web.FreeModifyController;
import com.study.free.web.FreeRegistController;
import com.study.free.web.FreeViewController;

public class SimpleController extends HttpServlet{
	
	//public static void main(String[] args) {
	//	String cp = "/study3";
	//	String t = "/study3/free/freeList.wow";
	//	System.out.println(t.substring(7));
	//}
	
	//IFreeBoardService freeBoardService = new FreeBoardServiceImpl();
	//ICommonCodeService codeService = new CommonCodeServiceImpl();
	
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
		IController controller = null;
		
		// 3. 모델를 사용하여 처리
		// 4. 결과를 속성에 저장
		if ("/free/freeList.wow".equals(uri)) {
			controller = new FreeListController();
		}else if ("/free/freeView.wow".equals(uri)) {
			controller = new FreeViewController();
		}else if ("/free/freeForm.wow".equals(uri)) {
			controller = new FreeFormController();
		}else if ("/free/freeRegist.wow".equals(uri)) {
			controller = new FreeRegistController();
		}else if ("/free/freeModify.wow".equals(uri)) {
			controller = new FreeModifyController();
		}else if ("/free/freeEdit.wow".equals(uri)) {
			controller = new FreeEditController();
		}else if ("/free/freeDelete.wow".equals(uri)) {
			controller = new FreeDeleteController();
		}
		if (controller != null) {
			
			try {
				viewPage = controller.process(req, resp);
				System.out.println("viewPage= "+viewPage);
				
				// 5. 뷰페이지 이동
				RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
				dispatcher.forward(req, resp);
			} catch (Exception e) {
				e.printStackTrace(); //500
				resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
			
		}else {
			// 요청에 대한 커트롤러가 없으므로 404 던지게
			resp.sendError(404,uri+" <-- 해당 URI는 존재하지 않습니다.");
		}
		
	}

}
