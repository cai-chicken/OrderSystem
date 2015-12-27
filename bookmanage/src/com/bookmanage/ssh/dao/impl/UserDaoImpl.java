package com.bookmanage.ssh.dao.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookmanage.ssh.base.BaseDaoImpl;
import com.bookmanage.ssh.dao.UserDao;
import com.bookmanage.ssh.model.User;

@Service("userDao")
@Transactional
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public User findByNameAndPwd(String loginName, String password) {
		return (User) getSession().createQuery(//
				"FROM User u WHERE u.loginName =? AND u.password = ?")//
				.setParameter(0, loginName)//
				.setParameter(1, password)//
				.uniqueResult();
	}

}
