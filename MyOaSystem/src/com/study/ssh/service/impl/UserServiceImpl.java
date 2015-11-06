package com.study.ssh.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.ssh.base.BaseDaoImpl;
import com.study.ssh.domain.User;
import com.study.ssh.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl extends BaseDaoImpl<User> implements UserService {

	@Override
	public User findByNameAndPwd(String loginName, String password) {
		String md5Pws = DigestUtils.md5Hex(password);
		return (User) getSession().createQuery(//
				"FROM User u WHERE u.loginName = ? AND u.password = ?")//
				.setParameter(0, loginName)//
				.setParameter(1, md5Pws)//
				.uniqueResult();
	}

}
