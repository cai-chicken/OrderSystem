package com.bookmanage.ssh.util;

import com.bookmanage.ssh.model.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;


/**
 * @author anbang
 * @description 防止未登录就访问其他资源
 * @date 2015年12月26日 下午6:22:13
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
			return "login";
		} else {
			//用户已登录，则放行
			return arg0.invoke();
		}
	}

}
