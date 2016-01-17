package com.mythesis.ssh.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mythesis.ssh.base.BaseDaoImpl;
import com.mythesis.ssh.model.Role;
import com.mythesis.ssh.service.RoleService;

/**
 * @author anbang
 * @description 角色Service类
 * @date 2016年1月16日 下午8:07:16
 */
@Service("roleService")
@Transactional
public class RoleServiceImpl extends BaseDaoImpl<Role> implements RoleService {

}
