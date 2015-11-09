package com.study.ssh.service;

import java.util.List;

import com.study.ssh.base.BaseDao;
import com.study.ssh.domain.Forum;
import com.study.ssh.domain.Topic;

public interface TopicService extends BaseDao<Topic> {

	List<Topic> findByForum(Forum forum);

	/**
	 * 如果指定id的主题是最新主题，那么它的前一个发表主题(也就是版块中最后发表主题的修改)
	 * @param topicId 
	 * @return
	 */
	Topic getLastTopic(Long topicId, boolean isDelete);

}
