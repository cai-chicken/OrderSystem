package com.study.ssh.base;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionSupport;
import com.study.ssh.service.RoleService;

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
	//-----------------------------------------------------------------
	//-----------------------------------------------------------------
	//-----------------------------------------------------------------
}
