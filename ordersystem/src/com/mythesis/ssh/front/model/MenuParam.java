package com.mythesis.ssh.front.model;

/**
 * @author anbang
 * @description 前端菜单列表过滤参数实体
 * @date 2016年3月19日 下午12:11:15
 */
public class MenuParam {
	private String menuName = "";// 菜名模糊查找条件
	private String sort = "0";// 评论星级模糊查找条件
	private Double price1 = null;// 价格区间模糊查找条件
	private Double price2 = null;
	private Long cuisineId = (long) 0;
	private Long menuId;

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Double getPrice1() {
		return price1;
	}

	public void setPrice1(Double price1) {
		this.price1 = price1;
	}

	public Double getPrice2() {
		return price2;
	}

	public void setPrice2(Double price2) {
		this.price2 = price2;
	}

	public Long getCuisineId() {
		return cuisineId;
	}

	public void setCuisineId(Long cuisineId) {
		this.cuisineId = cuisineId;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

}
