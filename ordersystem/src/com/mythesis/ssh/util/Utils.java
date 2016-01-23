package com.mythesis.ssh.util;

import com.mythesis.ssh.model.Employee;
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
}
