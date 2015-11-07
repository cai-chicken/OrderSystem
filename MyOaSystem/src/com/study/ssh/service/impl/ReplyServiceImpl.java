package com.study.ssh.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.ssh.base.BaseDaoImpl;
import com.study.ssh.domain.Forum;
import com.study.ssh.domain.Reply;
import com.study.ssh.domain.Topic;
import com.study.ssh.service.ReplyService;

@Service("replyService")
@Transactional
public class ReplyServiceImpl extends BaseDaoImpl<Reply> implements ReplyService {

	@Override
	public void save(Reply entity) {
		getSession().save(entity);
		
		Topic topic = entity.getTopic();
		Forum forum = topic.getForum();
		
		forum.setArticleCount(forum.getArticleCount() + 1);
		topic.setReplyCount(topic.getReplyCount() + 1);
		topic.setLastReply(entity);
		topic.setLastUpdateTime(entity.getPostTime());
		
		getSession().update(topic);
		getSession().update(forum);
	}

	@Override
	public List<Reply> findByTopic(Topic topic) {
		return getSession().createQuery(//
				"FROM Reply r WHERE r.topic = ? ORDER BY r.postTime")//
				.setParameter(0, topic)//
				.list();
	}
}
