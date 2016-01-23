package com.mythesis.ssh.service;

import java.util.Collection;
import java.util.List;

import com.mythesis.ssh.base.BaseDao;
import com.mythesis.ssh.model.Employee;
import com.mythesis.ssh.model.Privilege;

public interface PrivilegeService extends BaseDao<Privilege> {

	/**
	 * 得到所有的父权限
	 */
	List<Privilege> getTopPrivileges();
	
	/**
	 * 得到所有的权限数据
	 */
	Collection<String> getAllPrivilegeUrls();

}
