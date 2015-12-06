package com.study.ssm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.ssm.mapper.UserMapper;
import com.study.ssm.po.User;
import com.study.ssm.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	
	@Resource
	public UserMapper userMapper;

	@Override
	public User getUserById(Integer id) throws Exception {
		return userMapper.selectByPrimaryKey(id);
	}

}
