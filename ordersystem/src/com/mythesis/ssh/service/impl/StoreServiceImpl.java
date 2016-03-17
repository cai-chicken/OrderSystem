package com.mythesis.ssh.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mythesis.ssh.base.BaseDaoImpl;
import com.mythesis.ssh.model.Store;
import com.mythesis.ssh.service.StoreService;

/**
 * @author anbang
 * @description 本店信息Service实现类
 * @date 2016年1月27日 下午9:10:02
 */
@Service("storeService")
@Transactional
public class StoreServiceImpl extends BaseDaoImpl<Store> implements StoreService {

	@Override
	public Store findByState() {
		return (Store) getSession().createQuery(//
				"FROM Store s WHERE s.state like '%1%'")//
				.uniqueResult();
	}

}
