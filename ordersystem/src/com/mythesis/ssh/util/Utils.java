package com.mythesis.ssh.util;

import java.util.Collection;

import com.mythesis.ssh.model.Employee;
import com.mythesis.ssh.model.Privilege;
import com.mythesis.ssh.model.Role;
import com.opensymphony.xwork2.ActionContext;

public class Utils {
	private static final String currentEmployee = "employeeLogin";
	
	/**
	 *得到当前登录用户
	 */
	public static Employee getCurrentEmployee(){
		return (Employee) ActionContext.getContext().getSession().get(currentEmployee);
	}
	
	/**
	 *从Session中删除该用户
	 */
	public static void removeCurrentEmployee(){
		ActionContext.getContext().getSession().remove(currentEmployee);
	}
	
	/**
	 * 提示错误信息
	 * @param errMask 错误信息关键字
	 * @param errInfo 错误信息内容
	 */
	public static void displayErrorInfo(String errMask, String errInfo){
		ActionContext.getContext().put(errMask, errInfo);
	}
	
	/**
	 * 当前Session的用户登录信息时候已过期
	 * @return
	 */
	public static boolean isExistsCurrentEmployee(){
		if (getCurrentEmployee() == null) {
			return false;
		}
		return true;
	}
	
	/**
	 * 当前登录用户是否有权限访问某个url资源
	 * @param url
	 * @return
	 */
	public static boolean isCurrentEmployeeHasPrivilegeByUrl(Employee employee, String url) {
		if (employee == null) {
			return false;
		}
		// 针对超级管理员，超级管理员拥有所有的权限
		if ("admin".equals(employee.getLoginName())) {
			return true;
		}
		int index = url.indexOf("?");
		//去掉后面的参数
		if (index > -1) {
			url = url.substring(0, index);
		}
		// 去掉UI后缀
		if (url.endsWith("UI")) {
			url = url.substring(0, url.length() - 2);
		}
		// 不需要控制的权限，则只要是登录用户就可以使用
		Collection<String> allPrivilegeUrls = (Collection<String>) ActionContext.getContext().getApplication().get("allPrivilegeUrls");
		if (!allPrivilegeUrls.contains(url)) {
			return true;
		} else {
			// 针对普通用户
			for(Role role : employee.getRoles()) {
				for(Privilege privilege : role.getPrivileges()){
					if (url.equals(privilege.getUrl())) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
