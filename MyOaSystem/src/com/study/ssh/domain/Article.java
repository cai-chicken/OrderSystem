package com.study.ssh.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章
 * @author anbang
 *
 */
public class Article implements Serializable {
	private Long id;
	private String title;
	private String content;
	private String ipAddr;// 所在ip地址
	private Date postTime;// 发表时间
	private User author;// 作者

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

}
