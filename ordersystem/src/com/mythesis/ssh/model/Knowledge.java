package com.mythesis.ssh.model;

import java.io.Serializable;

/**
 * @author anbang
 * @description 小知识实体
 * @date 2016年1月31日 下午2:47:00
 */
public class Knowledge implements Serializable {
	private Long id;
	private String name;
	private String description;

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

}
