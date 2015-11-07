package com.study.ssh.service;

import java.util.List;

import com.study.ssh.base.BaseDao;
import com.study.ssh.domain.Forum;
import com.study.ssh.domain.Topic;

public interface TopicService extends BaseDao<Topic> {

	List<Topic> findByForum(Forum forum);

}
