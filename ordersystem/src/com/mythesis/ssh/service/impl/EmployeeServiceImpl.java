package com.mythesis.ssh.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mythesis.ssh.base.BaseDaoImpl;
import com.mythesis.ssh.model.Employee;
import com.mythesis.ssh.model.Privilege;
import com.mythesis.ssh.service.EmployeeService;

/**
 * @author anbang
 * @description 员工Service实现类
 * @date 2016年1月10日 上午11:28:13
 */
@Service("employeeService")
@Transactional
@SuppressWarnings("unchecked")
public class EmployeeServiceImpl extends BaseDaoImpl<Employee> implements EmployeeService {

	@Override
	public Employee findByLoginNameAndPwd(String loginName, String password) {
		return (Employee) getSession().createQuery(//
				"FROM Employee e WHERE e.loginName = ? AND e.password = ?")//
				.setParameter(0, loginName)//
				.setParameter(1, password)//
				.uniqueResult();
	}

	@Override
	public String getOldPwd(Long id) {
		return (String) getSession().createQuery(//
				"SELECT e.password FROM Employee e WHERE e.id = ?")//
				.setParameter(0, id)//
				.uniqueResult();
	}

	@Override
	public void updatePwd(Long id, String newPwdMd5) {
	}

	@Override
	public List<Privilege> getTopPrivilegesByEmployee(Employee employee) {
		/*SELECT * FROM privilege p WHERE p.parentId IS NULL AND p.id IN (
			SELECT DISTINCT privilegeId FROM privilege_role t1 WHERE t1.roleId IN (
				SELECT roleId FROM employee_role WHERE employeeId=21
			)
		)*/
		return getSession().createSQLQuery(//
				"SELECT * FROM privilege p WHERE p.parentId IS NULL AND p.id IN ("
				+ "SELECT DISTINCT privilegeId FROM privilege_role t1 WHERE t1.roleId IN ("
				+ "SELECT roleId FROM employee_role WHERE employeeId=21))")//
				.list();
		/*return getSession().createQuery(//
				"FROM Privilege p WHERE p.parent.id IS NULL AND "
				+ "p.id IN (SELECT DISTINCT r.privileges.id FROM Role r WHERE r.id IN("
				+ "SELECT DISTINCT e.roles.id FROM Employee e WHERE e.id = ?))")//
				.setParameter(0, employee.getId())//
				.list();*/
		// 	SELECT DISTINCT e.roles.id FROM Employee e WHERE e.id = 21;
		//  SELECT DISTINCT r.privileges.id FROM Role r WHERE r.id IN
	}


}
