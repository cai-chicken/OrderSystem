package com.bookmanage.ssh.dao;

import com.bookmanage.ssh.base.BaseDao;
import com.bookmanage.ssh.model.User;

public interface UserDao extends BaseDao<User>{

	/**
	 * 根据登录名和密码确定用户身份
	 * @description @param loginName
	 * @description @param password
	 * @description @return
	 */
	User findByNameAndPwd(String loginName, String password);

}
