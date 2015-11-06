package com.study.ssh.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 权限
 * 
 * @author anbang
 *
 */
@SuppressWarnings("serial")
public class Privilege implements Serializable {
	private Long id;
	private String name;
	private String url;// 对权限的控制就是对对应的url的拦截
	private Privilege parent;// 父权限
	private Set<Privilege> children = new HashSet<Privilege>();// 子权限
	private Set<Role> roles = new HashSet<Role>();// 对应的角色
	
	public Privilege() {
		//必须要有一个无参的构造方法
	}

	public Privilege(String name, String url, Privilege parent) {
		this.name = name;
		this.url = url;
		this.parent = parent;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Privilege getParent() {
		return parent;
	}

	public void setParent(Privilege parent) {
		this.parent = parent;
	}

	public Set<Privilege> getChildren() {
		return children;
	}

	public void setChildren(Set<Privilege> children) {
		this.children = children;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
