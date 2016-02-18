package com.mythesis.ssh.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mythesis.ssh.base.BaseDaoImpl;
import com.mythesis.ssh.model.Cuisine;
import com.mythesis.ssh.service.CuisineService;

/**
 * @author anbang
 * @description 菜系Service实现类
 * @date 2016年1月31日 下午5:13:10
 */
@Service("cuisineService")
@Transactional
@SuppressWarnings("unchecked")
public class CuisineServiceImpl extends BaseDaoImpl<Cuisine> implements CuisineService {

	@Override
	public List<Cuisine> findFiveCuisines(String now) {
		return getSession().createQuery(//
				"FROM Cuisine c WHERE c.orderTime like ? order by c.orderTime desc")//
				.setParameter(0, "%"+now+"%")//
				.setFirstResult(0)//
				.setMaxResults(5)//
				.list();
	}

	@Override
	public List<Cuisine> findAllOrderCuisines() {
		return getSession().createQuery(//
				"FROM Cuisine c WHERE c.historyCount >= 0")//
				.list();
	}

}
