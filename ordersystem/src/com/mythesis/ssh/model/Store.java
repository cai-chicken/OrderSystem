package com.mythesis.ssh.model;

/**
 * 本店信息实体
 * 
 * @author anbang
 * @description
 * @date 2016年1月27日 下午8:03:40
 */
public class Store {
	private Long id;
	private String name;
	private String image;// 保存的是图片的路径
	private String description;
	private String use;

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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUse() {
		return use;
	}

	public void setUse(String use) {
		this.use = use;
	}

}
