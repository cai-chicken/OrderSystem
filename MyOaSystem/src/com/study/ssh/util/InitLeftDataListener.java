package com.study.ssh.util;

import java.util.Collection;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.study.ssh.domain.Privilege;
import com.study.ssh.service.PrivilegeService;

public class InitLeftDataListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ApplicationContext ac = WebApplicationContextUtils//
				.getWebApplicationContext(//
						arg0.getServletContext());
		PrivilegeService privilegeService = (PrivilegeService) ac.getBean("privilegeService");
		// 找出所有的顶级权限
		List<Privilege> privileges = privilegeService.findTopPrivilegeList();
		// 将其放在最大的作用域application中
		arg0.getServletContext().setAttribute("topPrivilegeList", privileges);
		
		// 准备权限数据，并放在最大的作用域中，所有不在数据库中的权限都放行，即只要用户登录就有的权限
		Collection<String> allPrivilegeUrls = privilegeService.getAllPrivilegeUrls();
		arg0.getServletContext().setAttribute("allPrivilegeUrls", allPrivilegeUrls);
		LoggerManager.printInfo(getClass(), "所有权限数据已放在application中");
	}

}
