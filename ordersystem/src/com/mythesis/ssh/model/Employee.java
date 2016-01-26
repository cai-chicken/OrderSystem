package com.mythesis.ssh.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author anbang
 * @description 员工实体
 * @date 2016年1月10日 上午11:09:20
 */
public class Employee {
	private Long id;
	private String name;
	private String loginName;
	private String password;
	private String num;// 编号
	private String sex;
	private Date birthday;
	private String address;
	private String phoneNumber;
	private Set<Role> roles = new HashSet<Role>();// 拥有的角色
	
	public boolean hasPrivilegeName(String name) {
		//超级管理员拥有所有的权限
		if ("admin".equals(loginName)) {
			return true;
		}
		//对于普通用户
		for(Role role : roles) {
			for(Privilege privilege:role.getPrivileges()){
				if (privilege.getName().equals(name)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public List<Privilege> getAllPrivilege(){
		Set<Privilege> setPrivilege = new HashSet<Privilege>();
		for(Role role:roles){
			for(Privilege privilege:role.getPrivileges()){
				setPrivilege.add(privilege);
			}
		}
		List<Privilege> privileges = new ArrayList<>(setPrivilege);
		return privileges;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", loginName=" + loginName + ", password=" + password
				+ ", num=" + num + ", sex=" + sex + ", birthday=" + birthday + ", address=" + address + ", phoneNumber="
				+ phoneNumber + ", roles=" + roles + "]";
	}

}
