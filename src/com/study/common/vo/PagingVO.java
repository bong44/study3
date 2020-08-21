package com.study.common.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@SuppressWarnings("serial")
public class PagingVO implements Serializable{
	private int curPage = 1; // 현재 페이지 (def 1)
	private int firstRow; 	// 페이징 SQL의 시작 rownum
	private int lastRow;  	// 페이징 SQL의 마지막 rownum
	private int totalRowCount; 	//총 페이지 수
	private int totalPageCount; //총 레코드 수
	private int rowSizePerPage = 10; // 페이지당 보여줄 행 수 (def = 10)

	private int pageSize = 5; //페이지 리스트에 보여지는 페이지 건 수
	private int firstPage; 	 // 페이지 리스트의 첫 페이지 번호
	private int lastPage;    // 페이징 리스트의 마지막 페이지 번호

	
	
	//get //set //toString
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	public void pageSetting() {
		// firstRow, lastRow
		// rowSizePerSize = 10
		// curPage = 1 ==> fR = 1, 1R = 10 이 나와야댄다
		// curPage = 3 ==> fR = 21, 1R = 30 이 나와야댄다
		firstRow = (curPage - 1) * rowSizePerPage + 1;
		lastRow = curPage * rowSizePerPage;
		
		totalPageCount = (totalRowCount - 1) / rowSizePerPage + 1;
		
		//firstPage, lastPage
		firstPage = ((curPage-1) / pageSize) * pageSize + 1;
		lastPage = firstPage + pageSize - 1;
		if (lastPage > totalPageCount) {
			lastPage = totalPageCount;
		}
		
	}

	
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getFirstRow() {
		return firstRow;
	}
	public void setFirstRow(int firstRow) {
		this.firstRow = firstRow;
	}
	public int getLastRow() {
		return lastRow;
	}
	public void setLastRow(int lastRow) {
		this.lastRow = lastRow;
	}
	public int getTotalRowCount() {
		return totalRowCount;
	}
	public void setTotalRowCount(int totalRowCount) {
		this.totalRowCount = totalRowCount;
	}
	public int getTotalPageCount() {
		return totalPageCount;
	}
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	public int getRowSizePerPage() {
		return rowSizePerPage;
	}
	public void setRowSizePerPage(int rowSizePerPage) {
		this.rowSizePerPage = rowSizePerPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getFirstPage() {
		return firstPage;
	}
	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	
	
}
