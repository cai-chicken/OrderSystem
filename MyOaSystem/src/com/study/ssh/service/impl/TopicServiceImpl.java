package com.study.ssh.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.ssh.base.BaseDaoImpl;
import com.study.ssh.domain.Forum;
import com.study.ssh.domain.Topic;
import com.study.ssh.service.TopicService;

@Service("topicService")
@Transactional
@SuppressWarnings("unchecked")
public class TopicServiceImpl extends BaseDaoImpl<Topic> implements TopicService {

	@Override
	public void save(Topic entity) {
		// 1、设置属性并保存
		entity.setType(Topic.TYPE_NORMAL);
		entity.setLastReply(null);
		entity.setReplyCount(0);
		entity.setLastUpdateTime(entity.getPostTime());
		getSession().save(entity);
		// 2、维护相关的特殊属性
		Forum forum = entity.getForum();
		forum.setLastTopic(entity);
		forum.setTopicCount(forum.getTopicCount() + 1);
		forum.setArticleCount(forum.getArticleCount() + 1);
		getSession().update(forum);
	}

	@Override
	public List<Topic> findByForum(Forum forum) {
		// 0普通，1精华，2置顶
		return getSession().createQuery(//
				// 排序问题
				"FROM Topic t WHERE t.forum = ? ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 1 END) DESC, t.lastUpdateTime DESC")//
				.setParameter(0, forum)//
				.list();
	}

	@Override
	public Topic getLastTopic(Long topicId) {
		Date oldTime = getById(topicId).getPostTime();
		//找出发表时间比这个oldTime还大的主题，如果没有，那么删除的就是最新主题，继续找出前一个发表时间最新的主题，如果有，那么删除
		//的就不是最新主题，返回返回原来最新的主题。
		Topic lastTopic = (Topic) getSession().createQuery(//
				"FROM Topic t WHERE t.postTime > ? ORDER BY t.postTime DESC")//
				.setParameter(0, oldTime)//
				.setFirstResult(0)//
				.setMaxResults(1)//
				.uniqueResult();
		if (lastTopic == null) {
			//按发表时间降序，取出第一个，就是前一个发表时间最新的主题
			return (Topic) getSession().createQuery(//
					"FROM Topic t WHERE t.postTime < ? ORDER BY t.postTime DESC")//
					.setParameter(0, oldTime)//
					.setFirstResult(0)//
					.setMaxResults(1)//
					.uniqueResult();
		}
		return lastTopic;
	}
}
