package com.study.ssh.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.ssh.base.BaseDaoImpl;
import com.study.ssh.domain.User;
import com.study.ssh.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl extends BaseDaoImpl<User> implements UserService {

}
