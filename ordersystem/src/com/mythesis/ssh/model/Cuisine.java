package com.mythesis.ssh.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author anbang
 * @description 菜系实体
 * @date 2016年1月31日 下午4:37:54
 */
public class Cuisine implements Serializable {
	private Long id;
	private String name;
	private String description;
	private Integer count;// 份数
	private Integer historyCount;//历史下单份数
	private String orderTime;//下单时间
	private Set<Menu> menus = new HashSet<Menu>();

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

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Set<Menu> getMenus() {
		return menus;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}

	public Integer getHistoryCount() {
		return historyCount;
	}

	public void setHistoryCount(Integer historyCount) {
		this.historyCount = historyCount;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

}
