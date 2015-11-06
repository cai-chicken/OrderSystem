package com.study.ssh.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.ssh.base.BaseDaoImpl;
import com.study.ssh.domain.Privilege;
import com.study.ssh.service.PrivilegeService;

@Service("privilegeService")
@Transactional
public class PrivilegeServiceImpl extends BaseDaoImpl<Privilege> implements PrivilegeService {

}
