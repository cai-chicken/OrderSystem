package com.mythesis.ssh.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mythesis.ssh.model.CurrentMonth;
import com.mythesis.ssh.model.CurrentYear;
import com.mythesis.ssh.model.Today;
import com.mythesis.ssh.service.CountService;

/**
 * @author anbang
 * @description 统计管理对应的Service实现类
 * @date 2016年2月16日 下午8:08:27
 */
@Service("countService")
@Transactional
public class CountServiceImpl implements CountService {
	@Resource
	SessionFactory sessionFactory;
	
	/**
	 * 得到当前可用的Session
	 * @return 当前可用的Session对象
	 */
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Today getTodayIncome(String string) {
		return (Today) getSession().createQuery(//
				"FROM Today t WHERE t.present like ?")//
				.setParameter(0, "%"+string+"%")//
				.uniqueResult();
	}

	@Override
	public CurrentMonth getMonthIncome(String string) {
		return (CurrentMonth) getSession().createQuery(//
				"FROM CurrentMonth cm WHERE cm.cMonth like ?")//
				.setParameter(0, string)//
				.uniqueResult();
	}

	@Override
	public CurrentYear getYearIncome(String string) {
		return (CurrentYear) getSession().createQuery(//
				"FROM CurrentYear cy WHERE cy.cYear like ?")//
				.setParameter(0, string)//
				.uniqueResult();
	}

	@Override
	public List<String> getYear() {
		return getSession().createQuery(//
				"SELECT DISTINCT cy.cYear FROM CurrentYear cy")//
				.list();
	}

}
