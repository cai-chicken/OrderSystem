package com.study.ssh.domain;

import java.util.List;

public class PageBean {

	//可以指定，页面参数
	private int currentPage;// 当前页
	private int pageSize;// 每页显示多少条
	
	//通过查询得到
	private int recordCount;// 总记录数
	private List recordList;// 本页的数据列表
	
	//通过计算得到
	private int pageCount;// 总页数
	private int beginPageIndex;// 页码列表的开始索引
	private int endPageIndex;// 页码列表的结束索引

	/**
	 * 只接受前4个必要的属性，会自动的计算出其他三个属性的值
	 * @param currentPage 当前页
	 * @param pageSize 每页显示多少条记录
	 * @param recordCount 总记录数
	 * @param arecordList 本页的数据列表
	 */
	public PageBean(int currentPage, int pageSize, int recordCount, List arecordList) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.recordCount = recordCount;
		this.recordList = arecordList;
		
		//计算总页码
		pageCount = (recordCount + pageSize - 1) / pageSize;
		
		//计算beginPageIndex 和 endPageIndex
		//总页数不多于10页，则全部显示
		if (pageCount <= 10) {
			beginPageIndex = 1;
			endPageIndex = pageCount;
		}
		//总页数多于10页，则显示当前页附近的10页码
		else {
			beginPageIndex = currentPage -4;
			endPageIndex = currentPage + 5;
			//当前页的页码不足4个时，则显示前面10个页码
			if (beginPageIndex < 1) {
				beginPageIndex = 1;
				endPageIndex = 10;
			}
			//当后面的页码不足4个时，则显示后面10个页码
			if (endPageIndex > pageCount) {
				endPageIndex = pageCount;
				beginPageIndex = pageCount - 10 + 1;
			}
		}
	}

	public List getRecordList() {
		return recordList;
	}

	public void setRecordList(List arecordList) {
		this.recordList = arecordList;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getBeginPageIndex() {
		return beginPageIndex;
	}

	public void setBeginPageIndex(int beginPageIndex) {
		this.beginPageIndex = beginPageIndex;
	}

	public int getEndPageIndex() {
		return endPageIndex;
	}

	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}

}
