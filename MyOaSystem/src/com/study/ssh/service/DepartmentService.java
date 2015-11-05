package com.study.ssh.service;

import java.util.List;

import com.study.ssh.base.BaseDao;
import com.study.ssh.domain.Department;

public interface DepartmentService extends BaseDao<Department> {

	/**
	 * 找出所有的父部门
	 * @return
	 */
	List<Department> findTopList();

	/**
	 * 根据父部门的id，找到所有的子部门
	 * @param parentId
	 * @return
	 */
	List<Department> findChildren(Long parentId);


}
