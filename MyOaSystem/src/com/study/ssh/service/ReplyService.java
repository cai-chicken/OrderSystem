package com.study.ssh.service;

import java.util.List;

import com.study.ssh.base.BaseDao;
import com.study.ssh.domain.Reply;
import com.study.ssh.domain.Topic;

public interface ReplyService extends BaseDao<Reply> {

	/**
	 * 找到该主题下所有的回复列表
	 * @param topic
	 * @return
	 */
	List<Reply> findByTopic(Topic topic);

}
