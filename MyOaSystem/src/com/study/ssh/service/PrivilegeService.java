package com.study.ssh.service;

import java.util.Collection;
import java.util.List;

import com.study.ssh.base.BaseDao;
import com.study.ssh.domain.Privilege;

public interface PrivilegeService extends BaseDao<Privilege> {

	/**
	 * 找到所有的顶级权限菜单
	 * @return
	 */
	List<Privilege> findTopPrivilegeList();

	/**
	 * 查询数据库中所有的权限url
	 * @return
	 */
	Collection<String> getAllPrivilegeUrls();

}
