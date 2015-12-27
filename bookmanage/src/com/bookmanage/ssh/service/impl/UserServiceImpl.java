package com.bookmanage.ssh.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookmanage.ssh.dao.UserDao;
import com.bookmanage.ssh.model.User;
import com.bookmanage.ssh.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	@Resource
	UserDao userDao;

	@Override
	public User findByNameAndPwd(String loginName, String password) {
		return userDao.findByNameAndPwd(loginName, password);
	}

}
