package com.study.ssh.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.study.ssh.domain.Department;

public class DepartmentUtil {

	/**
	 * 显示树状结构
	 * @param topList
	 * @return
	 */
	public static List<Department> findDeptByTree(List<Department> topList) {
		List<Department> list = new ArrayList<Department>();
		displayTreeDepartment(topList, ">>", list);
		return list;
	}

	/**
	 * @param topList
	 * @param prefix
	 * @param list
	 */
	private static void displayTreeDepartment(Collection<Department> topList, String prefix, List<Department> list) {
		for (Department department : topList) {
			Department copy = new Department();
			copy.setId(department.getId());
			copy.setName(prefix + department.getName());
			list.add(copy);
			displayTreeDepartment(department.getChildren(), "　" + prefix, list);
		}
	}

}
