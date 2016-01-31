package com.mythesis.ssh.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author anbang
 * @description
 * @date 2016年1月31日 下午4:11:42
 */
public class Comment implements Serializable {
	private Long id;
	private User user;// 顾客
	private Date commentTime;
	private int star;
	private String description;
	private Menu menu;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

}
