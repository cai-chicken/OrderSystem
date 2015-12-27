package com.bookmanage.ssh.service;

import com.bookmanage.ssh.model.User;

public interface UserService {

	User findByNameAndPwd(String loginName, String password);

}
