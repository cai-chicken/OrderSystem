package com.bookmanage.ssh.model;

import java.util.Date;

/**
 * @author anbang
 * @description 图书实体
 * @date 2015年12月25日 下午11:30:13
 */
public class Book {
	private Long id;
	private String name;
	private String pubHome;// 出版社
	private Date pubDate;// 出版时间
	private String count;// 页数
	private String author;// 作者
	private double price;
	private String content;// 摘要

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

	public String getPubHome() {
		return pubHome;
	}

	public void setPubHome(String pubHome) {
		this.pubHome = pubHome;
	}

	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
