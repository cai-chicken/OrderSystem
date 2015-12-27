package com.bookmanage.ssh.base;

import javax.annotation.Resource;

import com.bookmanage.ssh.service.BookService;
import com.bookmanage.ssh.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author anbang
 * @description 所有不需要ModelDriven接口的都必须继承该Action
 * @date 2015年12月26日 上午12:08:00
 */
@SuppressWarnings("serial")
public class BaseAction extends ActionSupport {

	// ------------------------------Service提供-----------------------------------
	@Resource
	protected UserService userService;
	@Resource
	protected BookService bookService;

	// -----------------------------------------------------------------
	protected int pageNum = 1;// 当前页，默认是第一页
	protected int pageSize = 3;// 每页显示多少条，默认是2

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
