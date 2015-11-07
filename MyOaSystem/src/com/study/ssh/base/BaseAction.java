package com.study.ssh.base;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.study.ssh.domain.User;
import com.study.ssh.service.DepartmentService;
import com.study.ssh.service.ForumService;
import com.study.ssh.service.PrivilegeService;
import com.study.ssh.service.ReplyService;
import com.study.ssh.service.RoleService;
import com.study.ssh.service.TopicService;
import com.study.ssh.service.UserService;

/**
 * 所有不需要ModelDriven接口的都必须继承该Action
 * @author anbang
 *
 */
@SuppressWarnings("serial")
public class BaseAction extends ActionSupport {
	
	//------------------------------Service提供-----------------------------------
	@Resource
	protected RoleService roleService;
	
	@Resource
	protected DepartmentService departmentService;
	
	@Resource
	protected UserService userService;
	
	@Resource
	protected PrivilegeService privilegeService;
	
	@Resource
	protected ForumService forumService;
	
	@Resource
	protected TopicService topicService;
	
	@Resource
	protected ReplyService replyService;
	//------------------------------获取当前登录-----------------------------------
	protected User getCurrentUser () {
		return (User) ActionContext.getContext().getSession().get("user");
	}
	//-----------------------------------------------------------------
	protected int pageNum = 1;// 当前页，默认是第一页
	protected int pageSize = 10;// 每页显示多少条，默认是第十页

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
	//-----------------------------------------------------------------
}
