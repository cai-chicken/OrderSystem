package com.mythesis.ssh.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mythesis.ssh.base.BaseDaoImpl;
import com.mythesis.ssh.model.Privilege;
import com.mythesis.ssh.service.PrivilegeService;

@Service("privilegeService")
@Transactional
public class PrivilegeServiceImpl extends BaseDaoImpl<Privilege> implements PrivilegeService {

}
