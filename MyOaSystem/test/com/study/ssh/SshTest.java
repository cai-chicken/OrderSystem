package com.study.ssh;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionSupport;
import com.study.ssh.domain.Role;

@SuppressWarnings("serial")
@Controller("strutsTest")
@Scope("prototype")
@Transactional
public class SshTest extends ActionSupport {
	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	public String execute() throws Exception {
		Role role = new Role();
		Session session = sessionFactory.getCurrentSession();
		role.setName("员工");
		role.setDescription("拼了命打工挣钱的");
		session.save(role);
		return "second";
	}
}
