package com.mythesis.ssh.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.mythesis.ssh.model.Employee;
import com.mythesis.ssh.model.Privilege;
import com.mythesis.ssh.model.Role;
import com.mythesis.ssh.service.PrivilegeService;
import com.opensymphony.xwork2.ActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
	 * 向jsp页面提示错误信息
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
	
	/**
	 * 组成json格式传到jsp页面
	 * @param privilegeList
	 * @return
	 */
	public static String getJsonPrivilege(List<Privilege> topPrivilegeList, PrivilegeService privilegeService){
		JSONArray array1 = new JSONArray();
		JSONObject object1 = new JSONObject();
		for(Iterator<Privilege> iterator = topPrivilegeList.iterator();iterator.hasNext();){
			Privilege topPrivilege = iterator.next();
			object1.put("id", topPrivilege.getId());
			JSONArray array2 = new JSONArray();
			JSONObject object2 = new JSONObject();
			object2.put("text", topPrivilege.getName());
			JSONArray array3 = new JSONArray();
			List<Privilege> childrenPrivileges = privilegeService.getChildrenByParentId(topPrivilege.getId());
			for(Privilege children:childrenPrivileges){
				JSONObject object = new JSONObject();
				object.put("id", children.getId());
				object.put("text", children.getName());
				object.put("href", "/ordersystem"+children.getUrl()+".action");
				array3.add(object);
			}
			object2.put("items", array3);
			array2.add(object2);
			object1.put("menu", array2);
			array1.add(object1);
		}
		return array1.toString();
	}
}
