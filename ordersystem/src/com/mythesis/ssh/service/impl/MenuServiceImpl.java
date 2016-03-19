package com.mythesis.ssh.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mythesis.ssh.base.BaseDaoImpl;
import com.mythesis.ssh.model.Menu;
import com.mythesis.ssh.service.MenuService;

/**
 * @author anbang
 * @description 菜单Service实现类
 * @date 2016年1月31日 下午8:03:07
 */
@Service("menuService")
@Transactional
@SuppressWarnings("unchecked")
public class MenuServiceImpl extends BaseDaoImpl<Menu> implements MenuService {

	@Override
	public List<Menu> findFiveMenus(String now) {
		return getSession().createQuery(//
				"FROM Menu m WHERE m.orderTime like ? order by m.orderTime desc")//
				.setParameter(0, "%"+now+"%")//
				.setFirstResult(0)//
				.setMaxResults(5)//
				.list();
	}

	@Override
	public List<Menu> findAllOrderMenu() {
		return getSession().createQuery(//
				"FROM Menu m WHERE m.historyCount >= 0")//
				.list();
	}

	@Override
	public int getSuccessOrderCount() {
		String hql = "FROM Menu m WHERE m.count > 0 and m.status != null";
		List<Menu> menus = getSession().createQuery(hql).list();
		int count = 0;
		if (menus != null && menus.size() != 0) {
			for(Menu menu : menus){
				count += menu.getCount();
			}
		}
		return count;
	}

	@Override
	public List<Menu> getFiveSpecialMenu() {
		return getSession().createQuery(//
				"FROM Menu m WHERE m.image != null and m.isSpecial like '%1%' order by m.id desc")//
				.setMaxResults(5)//
				.list();
	}

	@Override
	public List<Menu> getOrderSuccessList() {
		String hql = "FROM Menu m WHERE m.count > 0 and m.status != null";
		return getSession().createQuery(hql).list();
	}

}
