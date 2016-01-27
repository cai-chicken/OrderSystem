package com.mythesis.ssh.service;

import com.mythesis.ssh.base.BaseDao;
import com.mythesis.ssh.model.Store;

public interface StoreService extends BaseDao<Store> {

	/**
	 * 找到当前启用状体的本店信息记录
	 * @return
	 */
	Store findByUseState();

}
