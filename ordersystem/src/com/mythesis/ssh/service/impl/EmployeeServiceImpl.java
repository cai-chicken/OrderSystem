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


}
