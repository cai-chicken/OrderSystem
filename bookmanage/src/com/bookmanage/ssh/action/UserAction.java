package com.bookmanage.ssh.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.bookmanage.ssh.base.ModelDrivenBaseAction;
import com.bookmanage.ssh.model.User;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author anbang
 * @description 用来处理用户相关的请求
 * @date 2015年12月26日 上午9:00:17
 */
@Controller("userAction")
@Scope("prototype")
public class UserAction extends ModelDrivenBaseAction<User> {

	/** 转到登录界面 */
	public String loginUI() throws Exception {
		// 不需要准备任何数据，直接返回逻辑视图名
		return "login";
	}

	/** 登录成功时，转到图书列表页 */
	public String login() throws Exception {
		User user = userService.findByNameAndPwd(model.getLoginName(), model.getPassword());
		if (user == null) {
			addFieldError("error", "用户名或密码错误");
			return "login";
		} else {
			String str = "欢迎你 " + user.getName();
			if (user.getType() == 1) {
				str = str + " ,你当前的身份是超级管理员";
			} else {
				str = str + " ,你当前的身份是普通用户";
			}
			// 将当前用户放入到Session中
			ActionContext.getContext().getSession().put("user", user);
			// 将欢迎语放入到Session中
			ActionContext.getContext().getSession().put("userType", str);
			return "toBookList";
		}
	}

	/** 退出成功，跳转到登录页 */
	public String logout() throws Exception {
		ActionContext.getContext().getSession().remove("user");
		ActionContext.getContext().getSession().remove("userType");
		return "login";
	}
}
