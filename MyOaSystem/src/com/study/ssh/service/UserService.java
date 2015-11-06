package com.study.ssh.service;

import com.study.ssh.base.BaseDao;
import com.study.ssh.domain.User;

public interface UserService extends BaseDao<User> {

	/**
	 * 通过用户名和密码找到该用户
	 * @param name
	 * @param password
	 * @return
	 */
	User findByNameAndPwd(String name, String password);

}
