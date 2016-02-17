package com.mythesis.ssh.action;

import java.util.Calendar;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mythesis.ssh.base.BaseAction;
import com.mythesis.ssh.model.CurrentMonth;
import com.mythesis.ssh.model.CurrentWeek;
import com.mythesis.ssh.model.CurrentYear;
import com.mythesis.ssh.model.Today;
import com.mythesis.ssh.util.Utils;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author anbang
 * @description 统计管理对应的Action类
 * @date 2016年2月15日 下午9:32:55
 */
@Controller("countAction")
@Scope("prototype")
public class CountAction extends BaseAction {
	private String currentYear = "0";
	private String currentMonth = "0";
	private String currentDate = "0";
	/**
	 * 菜单的销售情况
	 * @return
	 * @throws Exception
	 */
	public String menuStatus() throws Exception {
		return "menuStatus";
	}
	
	/**
	 * 菜系的销售情况
	 * @return
	 * @throws Exception
	 */
	public String cuisineStatus() throws Exception {
		return "cuisineStatus";
	}
	
	/**
	 * 财产收入情况
	 * @return
	 * @throws Exception
	 */
	public String ownershipIncome() throws Exception {
		String yearStr = "";
		String monthStr = "";
		String todayStr = "";
		// 准备当前年份的数据
		List<String> years = countService.getYear();
		ActionContext.getContext().put("yearList", years);
		if ("0".equals(currentYear)) {
			currentYear = Calendar.getInstance().get(Calendar.YEAR) + "";
		}
		CurrentYear year = countService.getYearIncome(currentYear);
		yearStr = inComeToString(year);
		ActionContext.getContext().put("currentYear", currentYear);
		ActionContext.getContext().put("year", yearStr);
		// 准备当前月份的数据
		if ("0".equals(currentMonth)) {
			int m = Calendar.getInstance().get(Calendar.MONTH) + 1;
			currentMonth = m + "";
		}
		String cMonth = currentYear + "-" +currentMonth;
		CurrentMonth month = countService.getMonthIncome(cMonth);
		if (month == null) {
			monthStr = "[0,0,0,0,0,0,0]";
		} else {
			monthStr = inComeToString(month);
		}
		ActionContext.getContext().put("currentMonth", cMonth);
		ActionContext.getContext().put("month", monthStr);
		// 准备今天的数据
		if ("0".equals(currentDate)) {
			currentDate = Calendar.getInstance().get(Calendar.DATE) + "";
		}
		String cDate = cMonth + "-" + currentDate;
		Today today = countService.getTodayIncome(cDate);
		if (today == null) {
			todayStr = "[0,0,0,0,0,0,0]";
		} else {
			todayStr = inComeToString(today);
		}
		ActionContext.getContext().put("currentDate", cDate);
		ActionContext.getContext().put("today", todayStr);
		return "ownershipIncome";
	}
	
	private String inComeToString(Object object) throws Exception {
		String str = "";
		if (object instanceof Today) {
			Today today = (Today) object;
			str = "[" + today.getSix() + "," + today.getNine() + "," + today.getTwelve()
				+ "," + today.getFifteen() + "," + today.getEighteen() + "," + today.getTwentyOne()
				 + "," + today.getTwentyFour() + "]";
		} else if (object instanceof CurrentMonth) {
			CurrentMonth month = (CurrentMonth) object;
			str = "[" + month.getFirst() + "," + month.getFive() + "," + month.getTen()
			+ "," + month.getFifteen() + "," + month.getTwenty() + "," + month.getTwentyFive()
			 + "," + month.getThirty() + "]";
		} else if (object instanceof CurrentYear) {
			CurrentYear year = (CurrentYear) object;
			str = "[" + year.getJanuary() + "," + year.getFebruary() + "," + year.getMarch()
			+ "," + year.getApril() + "," + year.getMay() + "," + year.getJune()
			 + "," + year.getJuly() + "," + year.getAuguest() + "," + year.getSeptember() + "," + 
			year.getOctorber() + ","+ year.getNovember() + "," + year.getDecember() + "]";
		}
		return str;
	}
	
//---------------------------------------------------------------------------------
	public String getCurrentYear() {
		return currentYear;
	}

	public void setCurrentYear(String currentYear) {
		this.currentYear = currentYear;
	}

	public String getCurrentMonth() {
		return currentMonth;
	}

	public void setCurrentMonth(String currentMonth) {
		this.currentMonth = currentMonth;
	}

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}
	
}
