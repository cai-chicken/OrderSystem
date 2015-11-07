package com.study.ssh;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.ssh.domain.User;
import com.study.ssh.util.LoggerManager;

@Service
@Transactional
public class PageTestService {
	@Resource
	private SessionFactory sessionFactory;
	
	public void save100User() {
		Session session = sessionFactory.getCurrentSession();
		for (int i=0; i<100; i++) {
			User user = new User();
			user.setLoginName("testUser" + i);
			user.setDescription("测试分页的数据");
			session.save(user);
		}
		LoggerManager.printInfo(getClass(), "测试数据插入成功");
	}
}
