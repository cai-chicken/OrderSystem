package com.mythesis.ssh.service;

import java.util.List;

import com.mythesis.ssh.base.BaseDao;
import com.mythesis.ssh.model.Menu;

public interface MenuService extends BaseDao<Menu> {

	/**
	 * 找到今日销售前五的菜单情况
	 * @return
	 */
	List<Menu> findFiveMenus(String now);

	/**
	 * 找到历史所有菜单的销售情况
	 * @return
	 */
	List<Menu> findAllOrderMenu();

	/**
	 * 得到下单成功的份数
	 * @return
	 */
	int getSuccessOrderCount();

}
