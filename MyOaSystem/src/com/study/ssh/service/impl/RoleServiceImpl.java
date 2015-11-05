package com.study.ssh.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.ssh.base.BaseDaoImpl;
import com.study.ssh.domain.Role;
import com.study.ssh.service.RoleService;

@Service("roleService")
@Transactional
public class RoleServiceImpl extends BaseDaoImpl<Role> implements RoleService {

}
