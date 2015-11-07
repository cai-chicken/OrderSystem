package com.study.ssh;

import org.hibernate.SessionFactory;
import org.jbpm.api.ProcessEngine;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.study.ssh.util.LoggerManager;

public class HibernateAndJbpmTest {
	ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

	@Test
	public void testHibernate() {
		SessionFactory sf = (SessionFactory) ac.getBean("sessionFactory");
		LoggerManager.printInfo(HibernateAndJbpmTest.class, "建表成功");
	}
	
	@Test
	public void testJbpm () {
		ProcessEngine pe = (ProcessEngine) ac.getBean("processEngine");
		System.out.println(pe);
	}
	
	@Test
	public void testPage () {
		PageTestService pts = (PageTestService) ac.getBean("pageTestService");
		pts.save100User();
	}
}
