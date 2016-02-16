package com.mythesis.ssh.service;

import java.util.List;

import com.mythesis.ssh.model.CurrentMonth;
import com.mythesis.ssh.model.CurrentYear;
import com.mythesis.ssh.model.Today;

public interface CountService {

	/**
	 * 得到指定日期的今天收入数据
	 * @param string
	 * @return
	 */
	Today getTodayIncome(String string);

	/**
	 * 得到当前月份的收入数据
	 * @param string
	 * @return
	 */
	CurrentMonth getMonthIncome(String string);

	/**
	 * 得到当前年份的收入数据
	 * @param string
	 * @return
	 */
	CurrentYear getYearIncome(String string);

	/**
	 * 得到数据库中的年份数据
	 * @return
	 */
	List<String> getYear();

}
