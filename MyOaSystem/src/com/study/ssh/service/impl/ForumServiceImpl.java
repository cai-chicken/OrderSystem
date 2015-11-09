package com.study.ssh.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.ssh.base.BaseDaoImpl;
import com.study.ssh.domain.Forum;
import com.study.ssh.domain.Topic;
import com.study.ssh.service.ForumService;
import com.study.ssh.util.LoggerManager;

@Service("forumService")
@Transactional
@SuppressWarnings("unchecked")
public class ForumServiceImpl extends BaseDaoImpl<Forum> implements ForumService {
	@Override
	public List<Forum> findAll() {
		return getSession()
				.createQuery(//
						"FROM Forum f ORDER BY f.position ASC")//
				.list();
	}

	@Override
	public void save(Forum entity) {
		super.save(entity);
		entity.setPosition(entity.getId().intValue());
		getSession().update(entity);
	}

	@Override
	public void moveUp(Long id) {
		moveForum(id, true);
	}

	@Override
	public void moveDown(Long id) {
		moveForum(id, false);
	}

	/**
	 * @param id
	 * @param isMoveUp
	 */
	private void moveForum(Long id, boolean isMoveUp) {
		// 1、找到要移动的Forum
		Forum forum = getById(id);
		// 2、找到需要被移动的Forum
		Forum other = findOtherForum(forum.getPosition(), isMoveUp);
		// 如果没有找到相应的Forum，则return
		if (other == null) {
			return;
		}
		// 3、交换position的值
		int temp = 0;
		temp = forum.getPosition();
		forum.setPosition(other.getPosition());
		other.setPosition(temp);
		// 4、保存到数据库中
		getSession().update(forum);
		getSession().update(other);
	}

	/**
	 * 找到当前位置的上一个或者下一个Forum
	 * 
	 * @param currentPosition
	 *            当前位置
	 * @param isMoveUp
	 *            找上一个还是找下一个
	 * @return
	 */
	private Forum findOtherForum(int currentPosition, boolean isMoveUp) {
		String hql = "";
		if (isMoveUp) {
			hql = "FROM Forum f WHERE f.position < ? ORDER BY f.position DESC";
		} else {
			hql = "FROM Forum f WHERE f.position > ? ORDER BY f.position ASC";
		}
		return (Forum) getSession()
				.createQuery(//
						hql)//
				.setParameter(0, currentPosition)//
				.setFirstResult(0)//
				.setMaxResults(1)//
				.uniqueResult();
	}

	@Override
	public void editField(Long forumId, int replyCount, Topic lastTopic, boolean isDelete) {
		Forum forum = getById(forumId);
		if (isDelete) {
			LoggerManager.printInfo(ForumServiceImpl.class, "执行版块特殊属性的减少");
			forum.setTopicCount(forum.getTopicCount() - 1);
			forum.setArticleCount(forum.getArticleCount() - (1 + replyCount));
		} else {
			LoggerManager.printInfo(ForumServiceImpl.class, "执行版块特殊属性的增加");
			forum.setTopicCount(forum.getTopicCount() + 1);
			forum.setArticleCount(forum.getArticleCount() + (1 + replyCount));
		}
		forum.setLastTopic(lastTopic);
		getSession().update(forum);
	}

}
