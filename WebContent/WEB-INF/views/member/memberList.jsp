<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="mytag" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@ include file="/WEB-INF/inc/header.jsp" %>
	<title>memberList.jsp </title>
</head>
<body>
 <div class="container">	
	<h3>회원목록</h3>		
<%@ include file="/WEB-INF/inc/top.jsp" %>

<!-- START : 검색 폼  -->
		<div class="collapse in panel panel-default" id="id_search_area">
			<div class="panel-body">
				<form name="frm_search" action="memberList.wow" method="post"
					class="form-horizontal">
					<input type="hidden" name="curPage" value="${searchVO.curPage}">
					<input type="hidden" name="rowSizePerPage"
						value="${searchVO.rowSizePerPage}">
					<div class="form-group">
						<label for="id_searchType" class="col-sm-2 control-label">검색</label>
						<div class="col-sm-2">
							<select id="id_searchType" name="searchType"
								class="form-control input-sm">
								<option value="NM"
									${searchVO.searchType eq "NM" ?  "selected='selected'" : ""}>이름</option>
								<option value="ID"
									${searchVO.searchType eq "ID" ?  "selected='selected'" : ""}>아이디</option>
								<option value="HP"
									${searchVO.searchType eq "HP" ?  "selected='selected'" : ""}>전화번호</option>
								<option value="ADD1"
									${searchVO.searchType eq "ADD1" ?  "selected='selected'" : ""}>주소</option>
								<option value="ADD2"
									${searchVO.searchType eq "ADD2" ?  "selected='selected'" : ""}>상세주소</option>
							</select>
						</div>
						<div class="col-sm-2">
							<input type="text" name="searchWord"
								class="form-control input-sm" value="${searchVO.searchWord}"
								placeholder="검색어">
						</div>
						<label for="id_searchCategory"
							class="col-sm-2 col-sm-offset-2 control-label">직업 분류</label>
						<div class="col-sm-2">
							<select id="id_searchCategory" name="searchJob"
								class="form-control input-sm">
								<option value="">-- 전체 --</option>
								<c:forEach items="${jbList}" var="code">
									<option value="${code.commCd}"
										${code.commCd eq searchVO.searchJob ? "selected='selected'" : ""}>${code.commNm}
									</option>
								</c:forEach>
							</select>
						</div>
						<label for="id_searchCategory"
							class="col-sm-8 col-sm-offset-2 control-label">취미 분류</label>
						<div class="col-sm-2">
							<select id="id_searchCategory" name="searchLike"
								class="form-control input-sm">
								<option value="">-- 전체 --</option>
								<c:forEach items="${hbList}" var="code">
									<option value="${code.commCd}"
										${code.commCd eq searchVO.searchLike ? "selected='selected'" : ""}>${code.commNm}
									</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2 col-sm-offset-9 text-right">
							<button type="button" id="id_btn_reset"
								class="btn btn-sm btn-default">
								<i class="fa fa-sync"></i> &nbsp;&nbsp;초기화
							</button>
						</div>
						<div class="col-sm-1 text-right">
							<button type="submit" class="btn btn-sm btn-primary ">
								<i class="fa fa-search"></i> &nbsp;&nbsp;검 색
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<!-- END : 검색 폼  -->

	<!-- START : 목록건수 및 새글쓰기 버튼  -->
		<div class="row" style="margin-bottom: 10px;">
			<div class="col-sm-3 form-inline">
				전체 ${searchVO.totalRowCount}건 조회 <select id="id_rowSizePerPage"
					name="rowSizePerPage" class="form-control input-sm">
					<option value="10">10</option>
					<option value="20">20</option>
					<option value="30">30</option>
					<option value="50">50</option>
				</select>
			</div>
			<div class="col-sm-2 col-sm-offset-6 text-right"
				data-toggle="collapse" data-target="#id_search_area">
				<a id="id_close" href="#" class="btn btn-info btn-sm"> <i
					class="fas fa-chevron-up"></i><span> &nbsp;검색닫기
				</span></a>
			</div>
			<div>
		<a href="memberForm.wow" class="btn btn-primary btn-sm pull-right">회원 등록</a>
	</div>
		</div>
		<!-- END : 목록건수 및 새글쓰기 버튼  -->


	<table class="table table-striped table-bordered">
		<caption class="hidden">회원목록 조회</caption>
		<colgroup>
			<col style="width: 15%;"/>
			<col />
			<col style="width: 15%;"/>
			<col style="width: 15%;"/>
			<col style="width: 15%;"/>
			<col style="width: 15%;"/>
		</colgroup>
		<thead>
			<tr>
				<th>ID</th>
				<th>회원명</th>
				<th>HP</th>
				<th>생일</th>
				<th>직업</th>
				<th>마일리지</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${members}" var="vo">
			<tr>
				<td>${vo.memId}</td> 
				<td><a href="memberView.wow?memId=${vo.memId}" >${vo.memName}</a></td>
				<td>${vo.memHp}</td>
				<td>${vo.memBir}</td>
				<td>${vo.memJobNm}</td>
				<td>${vo.memMileage}</td>
			</tr>
		</c:forEach>
		</tbody>	
	</table>
	
	<!-- START : 페이지네이션  -->
		<nav class="text-center">
			<mytag:paging pagingVO="${searchVO}" linkPage="memberList.wow?curPage=" />
		</nav>
		<!-- END : 페이지네이션  -->
	
	
</div> <!-- container -->
</body>
<script type="text/javascript">
	// 변수정의
	var f = document.forms['frm_search'];

	// 함수 정의

	// 각 이벤트 등록
	// 페이지 링크 클릭
	$('ul.pagination > li > a[data-page]').click(function(e) {
		e.preventDefault(); // 이벤트 전파 방지, a의 href로 이동 금지
		// data-page에 있는 값을 f.curPage.value에 설정, 서브밋
		f.curPage.value = $(this).data('page');
		f.submit();
	}); // 'ul.pagination > li > a[data-page]'

	// 검색버튼 클릭
	$(f).submit(function(e) {
		e.preventDefault();
		f.curPage.value = 1;
		f.submit();
	}); // f.submit

	// id_rowSizePerPage 변경되었을 때, 목록 수 변경 
	$('#id_rowSizePerPage').change(function(e) {
		// page 1로 설정, 목록 수 = 현재 값으로 변경 후 서브밋
		f.curPage.value = 1;
		f.rowSizePerPage.value = this.value;
		f.submit();
	}); // '#id_rowSizePerPage'.change

	// 초기화 버튼 클릭
	$('#id_btn_reset').click(function() {
		f.curPage.value = 1;
		f.searchWord = "";
		f.searchType.options[0].selected = true;
		f.searchCategory.options[0].selected = true;
	}); //#id_btn_reset.click

	// jQuery에서 클래스삭제 및 추가 -> 검색창 열고 닫기
	$('#id_search_area').on('shown.bs.collapse', function() {
		$('#id_close > i').removeClass('fa-chevron-down').addClass('fa-chevron-up');
		$('#id_close > span').html("&nbsp; 검색 닫기");
	})

	$('#id_search_area').on('hidden.bs.collapse', function() {
		$('#id_close > i').removeClass('fa-chevron-up').addClass('fa-chevron-down');
		$('#id_close > span').html("&nbsp; 검색 열기");
	})
</script>
</html>