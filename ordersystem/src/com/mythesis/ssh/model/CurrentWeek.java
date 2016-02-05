package com.mythesis.ssh.model;

/**
 * @author anbang
 * @description 用于统计本周的收入数据
 * @date 2016年2月5日 下午5:37:36
 */
public class CurrentWeek {
	private Long id;
	private String cWeek;// 周数(今天的日期，用于确定在此日期前的一周数据)
	private String monday;
	private String tuesday;
	private String wednesday;
	private String thursday;
	private String friday;
	private String staturday;
	private String sunday;
	private String count;// 本周的总收入

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getcWeek() {
		return cWeek;
	}

	public void setcWeek(String cWeek) {
		this.cWeek = cWeek;
	}

	public String getMonday() {
		return monday;
	}

	public void setMonday(String monday) {
		this.monday = monday;
	}

	public String getTuesday() {
		return tuesday;
	}

	public void setTuesday(String tuesday) {
		this.tuesday = tuesday;
	}

	public String getWednesday() {
		return wednesday;
	}

	public void setWednesday(String wednesday) {
		this.wednesday = wednesday;
	}

	public String getThursday() {
		return thursday;
	}

	public void setThursday(String thursday) {
		this.thursday = thursday;
	}

	public String getFriday() {
		return friday;
	}

	public void setFriday(String friday) {
		this.friday = friday;
	}

	public String getStaturday() {
		return staturday;
	}

	public void setStaturday(String staturday) {
		this.staturday = staturday;
	}

	public String getSunday() {
		return sunday;
	}

	public void setSunday(String sunday) {
		this.sunday = sunday;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

}
