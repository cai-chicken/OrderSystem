package com.study.ssh.service;

import com.study.ssh.base.BaseDao;
import com.study.ssh.domain.Forum;
import com.study.ssh.domain.Topic;

public interface ForumService extends BaseDao<Forum> {

	/**
	 * 上移
	 * 
	 * @param id
	 *            需要移动的版块的id
	 */
	void moveUp(Long id);

	/**
	 * 下移
	 * 
	 * @param id
	 *            需要移动的版块的id
	 */
	void moveDown(Long id);

	/**
	 * 在回复列表进行"删除主题"或者"移动到其它版块"的时候修改版块中的特殊属性
	 * @param forumId 需要被修改特殊属性的版块id
	 * @param replyCount 移动主题的回复数量
	 * @param lastTopic 最后发表的主题
	 * @param isDelete 是否来自删除
	 */
	void editField(Long forumId, int replyCount, Topic lastTopic, boolean isDelete);

}
