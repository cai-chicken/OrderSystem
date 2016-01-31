package com.mythesis.ssh.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mythesis.ssh.base.BaseDaoImpl;
import com.mythesis.ssh.model.User;
import com.mythesis.ssh.service.UserService;

/**
 * @author anbang
 * @description 顾客Service实现类
 * @date 2016年1月31日 下午4:09:40
 */
@Service("userService")
@Transactional
public class UserServiceImpl extends BaseDaoImpl<User> implements UserService {

}
