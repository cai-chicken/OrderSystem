package com.study.ssh.util;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.study.ssh.domain.User;

/**
 * 自定义检查权限的拦截器
 * @author anbang
 *
 */
@SuppressWarnings("serial")
public class CheckPrivilegeIterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		//当前用户
		User user = (User) ActionContext.getContext().getSession().get("user");
		//当前访问的url
		String nameSpace = arg0.getProxy().getNamespace();
		String actionName = arg0.getProxy().getActionName();
		String currentUrl = nameSpace + actionName;
		//如果用户没有登录，则去登录
		if (user == null) {
			//如果是去登录，则放行，否则跳转到登录页面
			if (currentUrl.startsWith("/user_login")) {
				return arg0.invoke();
			}
			return "loginUI";
		} else {
			//用户已登录，则判断有没有访问权限，有的话则放行，没有的话则报错
			if (user.hasPrivilegeByUrl(currentUrl)) {
				return arg0.invoke();
			} else {
				return "noPrivilege";
			}
		}
	}

}
