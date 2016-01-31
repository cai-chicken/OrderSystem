package com.mythesis.ssh.model;

import java.io.Serializable;

/**
 * @author anbang
 * @description 顾客实体
 * @date 2016年1月31日 下午4:06:48
 */
public class User implements Serializable {
	private Long id;
	private String name;

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

}
