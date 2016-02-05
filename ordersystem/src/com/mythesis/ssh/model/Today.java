package com.mythesis.ssh.model;

/**
 * @author anbang
 * @description 用于统计今天收入的数据
 * @date 2016年2月5日 下午5:25:20
 */
public class Today {
	private Long id;
	private String present;// 今天的日期，比如2016-02-05
	private String six;// 0到6点的收入
	private String nine;// 6到9点的收入
	private String twelve;
	private String fifteen;
	private String eighteen;
	private String twentyOne;
	private String twentyFour;
	private String count;// 今天的总收入

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPresent() {
		return present;
	}

	public void setPresent(String present) {
		this.present = present;
	}

	public String getSix() {
		return six;
	}

	public void setSix(String six) {
		this.six = six;
	}

	public String getNine() {
		return nine;
	}

	public void setNine(String nine) {
		this.nine = nine;
	}

	public String getTwelve() {
		return twelve;
	}

	public void setTwelve(String twelve) {
		this.twelve = twelve;
	}

	public String getFifteen() {
		return fifteen;
	}

	public void setFifteen(String fifteen) {
		this.fifteen = fifteen;
	}

	public String getEighteen() {
		return eighteen;
	}

	public void setEighteen(String eighteen) {
		this.eighteen = eighteen;
	}

	public String getTwentyOne() {
		return twentyOne;
	}

	public void setTwentyOne(String twentyOne) {
		this.twentyOne = twentyOne;
	}

	public String getTwentyFour() {
		return twentyFour;
	}

	public void setTwentyFour(String twentyFour) {
		this.twentyFour = twentyFour;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

}
