package com.study.ssh.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.ssh.base.BaseDaoImpl;
import com.study.ssh.domain.Privilege;
import com.study.ssh.service.PrivilegeService;

@Service("privilegeService")
@Transactional
@SuppressWarnings("unchecked")
public class PrivilegeServiceImpl extends BaseDaoImpl<Privilege> implements PrivilegeService {

	@Override
	public List<Privilege> findTopPrivilegeList() {
		return getSession().createQuery(
				"FROM Privilege p WHERE p.parent IS NULL")
				.list();
	}

	@Override
	public Collection<String> getAllPrivilegeUrls() {
		return getSession().createQuery(//
				"SELECT DISTINCT p.url FROM Privilege p WHERE p.url IS NOT NULL")//
				.list();
	}

}
