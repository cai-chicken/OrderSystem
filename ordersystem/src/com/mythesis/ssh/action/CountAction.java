package com.mythesis.ssh.action;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mythesis.ssh.base.BaseAction;
import com.mythesis.ssh.model.Cuisine;
import com.mythesis.ssh.model.CurrentMonth;
import com.mythesis.ssh.model.CurrentWeek;
import com.mythesis.ssh.model.CurrentYear;
import com.mythesis.ssh.model.Menu;
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
		//准备今日销售前五的菜单数据
		Format format = new SimpleDateFormat("yyyy-MM-dd");
        String now = format.format(new Date());
		List<Menu> fiveMenu = menuService.findFiveMenus(now);
		//准备历史所有菜单的数据
		List<Menu> allOrderMenu = menuService.findAllOrderMenu();
		menuToString(fiveMenu, false);
		menuToString(allOrderMenu, true);
		return "menuStatus";
	}
	
	/**
	 * 请菜单销售情况转换成字符串
	 * @param menuCount
	 * @param isPie 是否转换成饼图的json结构
	 */
	private void menuToString(List<Menu> menuCount, boolean isPie) {
		if (isPie) {
			String historyCountData = "[";
			/*{value:335, name:'直接访问'},
            {value:310, name:'邮件营销'}*/
			if (menuCount == null || menuCount.size() == 0) {
				historyCountData = "[{value:0, name:'无数据'}]";
			} else {
				for(int i=0; i<menuCount.size(); i++){
					Menu menu = menuCount.get(i);
					if (i == (menuCount.size()-1)) {
						historyCountData += ("{value:"+menu.getHistoryCount()+", name:'"+ menu.getName() +"'}]");
					} else {
						historyCountData += ("{value:"+menu.getHistoryCount()+", name:'"+ menu.getName() +"'},");
					}
				}
			}
			System.out.println("historyCountData---->" + historyCountData);
			ActionContext.getContext().put("historyCountData", historyCountData);
		} else {
			String xAxisData = "[";
			String seriesData = "[";
			if (menuCount == null || menuCount.size() == 0) {
				xAxisData += "\"无数据\",\"无数据\",\"无数据\",\"无数据\",\"无数据\"]";
				seriesData += "0,0,0,0,0]";
			} else {
				for(int i=0; i<menuCount.size(); i++){
					Menu menu = menuCount.get(i);
					if (i == (menuCount.size()-1)) {
						xAxisData += ("\""+menu.getName()+"\"" + "]");
						seriesData += (menu.getCount() + "]");
					} else {
						xAxisData += ("\""+menu.getName()+"\"" + ",");
						seriesData += (menu.getCount() + ",");
					}
				}
			}
			System.out.println("xAxisData----->"+xAxisData);
			System.out.println("seriesData---->"+seriesData);
			ActionContext.getContext().put("xAxisData", xAxisData);
			ActionContext.getContext().put("seriesData", seriesData);
		}
	}
	
	/**
	 * 菜系的销售情况
	 * @return
	 * @throws Exception
	 */
	public String cuisineStatus() throws Exception {
		//准备今日销售前五的菜系数据
		Format format = new SimpleDateFormat("yyyy-MM-dd");
        String now = format.format(new Date());
		List<Cuisine> fiveCuisine = cuisineService.findFiveCuisines(now);
		//准备历史所有菜系的数据
		List<Cuisine> allOrderCuisine = cuisineService.findAllOrderCuisines();
		cuisineToString(fiveCuisine, false);
		cuisineToString(allOrderCuisine, true);
		return "cuisineStatus";
	}
	
	/**
	 * 将菜系数据转换为相应的String数据
	 * @param fiveCuisine
	 * @param isPie 
	 */
	private void cuisineToString(List<Cuisine> cuisineCount, boolean isPie) {
		if (isPie) {
			String historyCountData = "[";
			/*{value:335, name:'直接访问'},
            {value:310, name:'邮件营销'}*/
			if (cuisineCount == null || cuisineCount.size() == 0) {
				historyCountData = "[{value:0, name:'无数据'}]";
			} else {
				for(int i=0; i<cuisineCount.size(); i++){
					Cuisine cuisine = cuisineCount.get(i);
					if (i == (cuisineCount.size()-1)) {
						historyCountData += ("{value:"+cuisine.getHistoryCount()+", name:'"+ cuisine.getName() +"'}]");
					} else {
						historyCountData += ("{value:"+cuisine.getHistoryCount()+", name:'"+ cuisine.getName() +"'},");
					}
				}
			}
			System.out.println("historyCountData---->" + historyCountData);
			ActionContext.getContext().put("historyCountData", historyCountData);
		} else {
			String xAxisData = "[";
			String seriesData = "[";
			if (cuisineCount == null || cuisineCount.size() == 0) {
				xAxisData += "\"无数据\",\"无数据\",\"无数据\",\"无数据\",\"无数据\"]";
				seriesData += "0,0,0,0,0]";
			} else {
				for(int i=0; i<cuisineCount.size(); i++){
					Cuisine cuisine = cuisineCount.get(i);
					if (i == (cuisineCount.size()-1)) {
						xAxisData += ("\""+cuisine.getName()+"\"" + "]");
						seriesData += (cuisine.getCount() + "]");
					} else {
						xAxisData += ("\""+cuisine.getName()+"\"" + ",");
						seriesData += (cuisine.getCount() + ",");
					}
				}
			}
			System.out.println("xAxisData----->"+xAxisData);
			System.out.println("seriesData---->"+seriesData);
			ActionContext.getContext().put("xAxisData", xAxisData);
			ActionContext.getContext().put("seriesData", seriesData);
		}
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
