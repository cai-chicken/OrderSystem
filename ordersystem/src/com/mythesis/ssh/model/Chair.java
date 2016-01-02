package com.mythesis.ssh.model;

/**
 * @author anbang
 * @description 桌椅实体
 * @date 2016年1月2日 上午10:44:02
 */
public class Chair {
	private Long id;
	private String num;// 编号
	private int isGood;// 是否可用，1：可用；0：不可用

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

	public int getIsGood() {
		return isGood;
	}

	public void setIsGood(int isGood) {
		this.isGood = isGood;
	}

}
