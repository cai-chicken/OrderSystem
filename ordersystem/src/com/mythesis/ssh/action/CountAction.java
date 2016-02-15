package com.mythesis.ssh.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mythesis.ssh.base.BaseAction;

/**
 * @author anbang
 * @description 统计管理对应的Action类
 * @date 2016年2月15日 下午9:32:55
 */
@Controller("countAction")
@Scope("prototype")
public class CountAction extends BaseAction {
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
		return "ownershipIncome";
	}
}
