package com.mythesis.ssh.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author anbang
 * @description 角色
 * @date 2016年1月10日 上午11:12:36
 */
public class Role implements Serializable{
	private Long id;
	private String name;
	private String description;
	private Set<Employee> employees = new HashSet<Employee>();
	private Set<Privilege> privileges = new HashSet<Privilege>();

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public Set<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}

}
