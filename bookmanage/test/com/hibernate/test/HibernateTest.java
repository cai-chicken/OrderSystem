package com.hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.bookmanage.ssh.model.User;

public class HibernateTest {

	@Test
	public void test() {
		SessionFactory sessionFactory = 
				new Configuration().configure("classpath:hibernate.cfg.xml")
				.buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		User user = new User();
		user.setName("张三");
		user.setLoginName("zhangsan");
		user.setPassword("12345");
		session.save(user);
	}

}
