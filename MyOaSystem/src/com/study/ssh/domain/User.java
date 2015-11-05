package com.study.ssh.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户
 * @author anbang
 *
 */
@SuppressWarnings("serial")
public class User implements Serializable {

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
