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
	
	/**
	 * 得到用户的顶级权限列表
	 * @param employee
	 * @return
	 */
	List<Object[]> getTopPrivilegesByEmployee(Employee employee);

	/**
	 * 通过父权限id找到所有的子权限
	 * @param long1
	 * @return
	 */
	List<Privilege> getChildrenByParentId(Long long1);

}
