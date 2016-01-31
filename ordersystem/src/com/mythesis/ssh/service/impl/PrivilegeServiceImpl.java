package com.mythesis.ssh.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mythesis.ssh.base.BaseDaoImpl;
import com.mythesis.ssh.model.Employee;
import com.mythesis.ssh.model.Privilege;
import com.mythesis.ssh.service.PrivilegeService;

@Service("privilegeService")
@Transactional
@SuppressWarnings("unchecked")
public class PrivilegeServiceImpl extends BaseDaoImpl<Privilege> implements PrivilegeService {

	@Override
	public List<Privilege> getTopPrivileges() {
		return getSession()
				.createQuery(//
						"FROM Privilege p WHERE p.parent IS NULL")//
				.list();
	}

	@Override
	public Collection<String> getAllPrivilegeUrls() {
		return getSession()
				.createQuery(//
						"SELECT DISTINCT p.url FROM Privilege p WHERE p.url IS NOT NULL")//
				.list();
	}

	@Override
	public List<Object[]> getTopPrivilegesByEmployee(Employee employee) {
		/*
		 * SELECT * FROM privilege p WHERE p.parentId IS NULL AND p.id IN (
		 * SELECT DISTINCT privilegeId FROM privilege_role t1 WHERE t1.roleId IN
		 * ( SELECT roleId FROM employee_role WHERE employeeId=21 ) )
		 */
		return getSession()
				.createSQLQuery(//
						"SELECT p.id,p.name FROM privilege p WHERE p.parentId IS NULL AND p.id IN ("
								+ "SELECT DISTINCT privilegeId FROM privilege_role t1 WHERE t1.roleId IN ("
								+ "SELECT roleId FROM employee_role WHERE employeeId=?))")//
				.setParameter(0, employee.getId())//
				.list();
		// SELECT DISTINCT e.roles.id FROM Employee e WHERE e.id = 21;
		// SELECT DISTINCT r.privileges.id FROM Role r WHERE r.id IN
	}

	@Override
	public List<Privilege> getChildrenByParentId(Long id) {
		return getSession().createQuery(//
				"FROM Privilege p WHERE p.parent.id = ?")//
				.setParameter(0, id)//
				.list();
	}
}
