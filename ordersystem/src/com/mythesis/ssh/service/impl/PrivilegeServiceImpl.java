package com.mythesis.ssh.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mythesis.ssh.base.BaseDaoImpl;
import com.mythesis.ssh.model.Employee;
import com.mythesis.ssh.model.Privilege;
import com.mythesis.ssh.model.Role;
import com.mythesis.ssh.service.PrivilegeService;

@Service("privilegeService")
@Transactional
@SuppressWarnings("unchecked")
public class PrivilegeServiceImpl extends BaseDaoImpl<Privilege> implements PrivilegeService {

	@Override
	public List<Privilege> getTopPrivileges() {
		return getSession().createQuery(//
				"FROM Privilege p WHERE p.parent IS NULL")//
				.list();
	}

	@Override
	public Collection<String> getAllPrivilegeUrls() {
		return getSession().createQuery(//
				"SELECT DISTINCT p.url FROM Privilege p WHERE p.url IS NOT NULL")//
				.list();
	}

}
