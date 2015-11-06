package com.study.ssh.service;

import com.study.ssh.base.BaseDao;
import com.study.ssh.domain.Forum;

public interface ForumService extends BaseDao<Forum> {

	/**
	 * 上移
	 * @param id 需要移动的版块的id
	 */
	void moveUp(Long id);

	/**
	 * 下移
	 * @param id 需要移动的版块的id
	 */
	void moveDown(Long id);

}
