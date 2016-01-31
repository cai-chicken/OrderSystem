package com.mythesis.ssh.service;

import java.util.List;

import com.mythesis.ssh.base.BaseDao;
import com.mythesis.ssh.model.Employee;
import com.mythesis.ssh.model.Privilege;

/**
 * @author anbang
 * @description 员工Service接口
 * @date 2016年1月10日 上午11:27:58
 */
public interface EmployeeService extends BaseDao<Employee> {

	/**
	 * 通过登录名和密码找到用户
	 * 
	 * @param loginName
	 * @param password
	 * @return
	 */
	Employee findByLoginNameAndPwd(String loginName, String password);

	/**
	 * 通过用户id找到用户的密码，返回是已经加过密的密码
	 */
	String getOldPwd(Long id);

	/**
	 * 更新指定用户的密码信息
	 * 
	 * @param id
	 * @param newPwdMd5
	 */
	void updatePwd(Long id, String newPwdMd5);

}
