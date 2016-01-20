package com.mythesis.ssh.util;

import java.util.Collection;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.mythesis.ssh.model.Privilege;
import com.mythesis.ssh.service.PrivilegeService;

public class InitAllPrivilegesListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		/*得到spring容器上下文对象*/
		ApplicationContext applicationContext = WebApplicationContextUtils//
				.getWebApplicationContext(arg0.getServletContext());
		PrivilegeService privilegeService = (PrivilegeService) 
				applicationContext.getBean("privilegeService");
		/*得到所有的父权限*/
		List<Privilege> topPrivilegeList = privilegeService.getTopPrivileges();
		/*将取出来的数据放到application中*/
		arg0.getServletContext().setAttribute("topPrivilegeList", topPrivilegeList);
		
		// 准备权限数据，并放在最大的作用域中，所有不在数据库中的权限都放行，即只要用户登录就有的权限
		Collection<String> allPrivilegeUrls = privilegeService.getAllPrivilegeUrls();
		arg0.getServletContext().setAttribute("allPrivilegeUrls", allPrivilegeUrls);
		LoggerManager.printInfo(getClass(), "所有权限数据已放在application中");
	}

}
