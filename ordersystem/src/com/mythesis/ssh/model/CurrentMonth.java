package com.mythesis.ssh.model;

/**
 * @author anbang
 * @description 本月的收入数据
 * @date 2016年2月5日 下午5:53:54
 */
public class CurrentMonth {
	private Long id;
	private String cMonth;// 当前的月份
	private String first;// 1号
	private String five;// 5号
	private String ten;
	private String fifteen;
	private String twenty;
	private String twentyFive;
	private String thirty;
	private String count;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getcMonth() {
		return cMonth;
	}

	public void setcMonth(String cMonth) {
		this.cMonth = cMonth;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getFive() {
		return five;
	}

	public void setFive(String five) {
		this.five = five;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getFifteen() {
		return fifteen;
	}

	public void setFifteen(String fifteen) {
		this.fifteen = fifteen;
	}

	public String getTwenty() {
		return twenty;
	}

	public void setTwenty(String twenty) {
		this.twenty = twenty;
	}

	public String getTwentyFive() {
		return twentyFive;
	}

	public void setTwentyFive(String twentyFive) {
		this.twentyFive = twentyFive;
	}

	public String getThirty() {
		return thirty;
	}

	public void setThirty(String thirty) {
		this.thirty = thirty;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

}
