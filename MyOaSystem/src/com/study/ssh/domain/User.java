package com.study.ssh.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.study.ssh.util.LoggerManager;

/**
 * 用户
 * @author anbang
 *
 */
@SuppressWarnings("serial")
public class User implements Serializable{

	private Long id;
	private String loginName;// 登录名，唯一
	private String name;// 名字
	private String password;// 密码
	private String gender;// 性别
	private String phoneNum;
	private String email;
	private String description;
	private Set<Role> roles = new HashSet<Role>();// 所拥有的角色
	private Department department;// 所属部门
	
	/**
	 * 判断当前用户有没有指定名称的权限(用于初始化左侧菜单)
	 * @param name
	 * @return
	 */
	public boolean hasPrivilegeByName(String name) {
		//超级管理员拥有所有权限
		if ("admin".equals(loginName)) {
			return true;
		}
		//普通用户的权限是他拥有的角色的权限的总和
		for(Role role : roles) {
			for(Privilege privilege : role.getPrivileges()){
				if (privilege.getName().equals(name)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 当前用户是否访问该url的权限
	 * @param privUrl
	 * @return
	 */
	public boolean hasPrivilegeByUrl(String privUrl) {
		// 针对超级管理员,超级管理员拥有所有权限
		if ("admin".equals(loginName)) {
			return true;
		}
		int index = privUrl.indexOf("?");
		// 去掉后面的参数
		if (index > -1) {
			privUrl = privUrl.substring(0, index);
		}
		// 去掉UI后缀
		if (privUrl.endsWith("UI")) {
			privUrl = privUrl.substring(0, privUrl.length() - 2);
		}
		// 不需要控制的权限，则只要用户登录就可以使用。
		// 因为这些权限的数据是不会经常变动的，所以只需要查询一次，以后需要的时候直接使用，而不是再去查询数据库
		Collection<String> allPrivilegeUrls = (Collection<String>) ActionContext.getContext().getApplication()//
				.get("allPrivilegeUrls");
		LoggerManager.printInfo(getClass(), "当前访问的url：" + privUrl);
		if (!allPrivilegeUrls.contains(privUrl)) {
			return true;
		} else {
			// 针对普通用户
			for (Role role : roles) {
				for (Privilege privilege : role.getPrivileges()) {
					if (privUrl.equals(privilege.getUrl())) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}
