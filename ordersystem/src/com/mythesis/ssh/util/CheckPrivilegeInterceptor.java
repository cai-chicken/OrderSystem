package com.mythesis.ssh.util;

import com.mythesis.ssh.model.Employee;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 权限拦截器
 * @author anbang
 * @description 
 * @date 2016年1月23日 下午6:24:14
 */
public class CheckPrivilegeInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		//得到当前的登录用户
		Employee employee = Utils.getCurrentEmployee();
		//当前访问的url
		String nameSpace = arg0.getProxy().getNamespace();
		String actionName = arg0.getProxy().getActionName();
		String currentUrl = nameSpace + actionName;
		LoggerManager.printInfo(getClass(), "当前访问路径："+currentUrl);
		//如果当前用户不存在
		if (employee == null) {
			//如果是去登录，则放行
			if (currentUrl.startsWith("/employee_login")) {
				LoggerManager.printInfo(getClass(), "登录，放行");
				return arg0.invoke();//放行
			}
			LoggerManager.printInfo(getClass(), "跳转到登录页");
			return "loginUI";
		} else {
			if (Utils.isCurrentEmployeeHasPrivilegeByUrl(employee, currentUrl)) {
				LoggerManager.printInfo(getClass(), "有权限，放行");
				return arg0.invoke();
			} else {
				LoggerManager.printInfo(getClass(), "没有权限访问");
				return "noPrivilege";
			}
		}
	}

}
