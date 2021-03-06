package com.mythesis.ssh.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author anbang
 * @description 菜单实体
 * @date 2016年1月31日 下午4:27:41
 */
public class Menu implements Serializable {
	private Long id;
	private String num;// 编号
	private String name;// 菜名
	private String image;
	private Double price;// 价格
	private String description;// 描述
	private String isSpecial;// 是否是本店特色菜;1是，0不是
	private Integer count;// 今天卖出的份数
	private Integer historyCount;// 历史卖出的份数
	private String orderTime;// 下单时间
	private String chilli;// 辣味0无辣，1少辣，2中辣，3变态辣
	private String pack;// 打包0否，1是
	private String status;// 烹饪状态
	private Chair chair;// 几号桌下的单
	private Set<Comment> comments = new HashSet<Comment>();// 评论列表
	private Integer positiveRate;// 好评率
	private String mainFood;//主要食材
	private String method;//做法
	private Cuisine cuisine;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsSpecial() {
		return isSpecial;
	}

	public void setIsSpecial(String isSpecial) {
		this.isSpecial = isSpecial;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Chair getChair() {
		return chair;
	}

	public void setChair(Chair chair) {
		this.chair = chair;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Cuisine getCuisine() {
		return cuisine;
	}

	public void setCuisine(Cuisine cuisine) {
		this.cuisine = cuisine;
	}

	public Integer getHistoryCount() {
		return historyCount;
	}

	public void setHistoryCount(Integer historyCount) {
		this.historyCount = historyCount;
	}

	public Integer getPositiveRate() {
		return positiveRate;
	}

	public void setPositiveRate(Integer positiveRate) {
		this.positiveRate = positiveRate;
	}

	public String getMainFood() {
		return mainFood;
	}

	public void setMainFood(String mainFood) {
		this.mainFood = mainFood;
	}

	public String getChilli() {
		return chilli;
	}

	public void setChilli(String chilli) {
		this.chilli = chilli;
	}

	public String getPack() {
		return pack;
	}

	public void setPack(String pack) {
		this.pack = pack;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

}
