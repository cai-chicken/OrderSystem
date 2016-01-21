package com.mythesis.ssh.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mythesis.ssh.base.BaseDaoImpl;
import com.mythesis.ssh.model.Employee;
import com.mythesis.ssh.service.EmployeeService;

/**
 * @author anbang
 * @description 员工Service实现类
 * @date 2016年1月10日 上午11:28:13
 */
@Service("employeeService")
@Transactional
public class EmployeeServiceImpl extends BaseDaoImpl<Employee> implements EmployeeService {

	@Override
	public Employee findByLoginNameAndPwd(String loginName, String password) {
		return (Employee) getSession().createQuery(//
				"FROM Employee e WHERE e.loginName = ? AND e.password = ?")//
				.setParameter(0, loginName)//
				.setParameter(1, password)//
				.uniqueResult();
	}


}
