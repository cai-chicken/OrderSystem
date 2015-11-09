package com.study.ssh.action;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.study.ssh.base.ModelDrivenBaseAction;
import com.study.ssh.domain.Forum;
import com.study.ssh.domain.Reply;
import com.study.ssh.domain.Topic;
import com.study.ssh.util.LoggerManager;

@Controller("replyAction")
@Scope("prototype")
public class ReplyAction extends ModelDrivenBaseAction<Reply> {
	private Long topicId;
	private int topicType;// 用于接收置顶，精华或者普通的参数
	private Long forumId;// 用于接收移动其它版块和删除该版块下的主题的forumId

	/**
	 * 回复页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addUI() throws Exception {
		Topic topic = topicService.getById(topicId);
		ActionContext.getContext().put("topic", topic);
		return "addUI";
	}

	/**
	 * 回复
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		// 设置属性
		model.setAuthor(getCurrentUser());
		model.setPostTime(new Date());
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
		model.setTopic(topicService.getById(topicId));
		// 保存数据库
		replyService.save(model);
		return "toShowReply";
	}

	/**
	 * 移动到其他版块页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String moveToOtherForumUI() throws Exception {
		// 准备所有的版块数据
		List<Forum> forums = forumService.findAll();
		ActionContext.getContext().put("forumList", forums);
		// 准备当前主题数据
		Topic topic = topicService.getById(topicId);
		ActionContext.getContext().put("topic", topic);
		return "moveToOtherForumUI";
	}

	/**
	 * 移动到其他版块成功时，转到本主题的回复列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String moveToOtherForum() throws Exception {
		// 修改主题所属版块
		Topic topic = topicService.getById(topicId);
		Forum forum = topic.getForum();//先拿到原来版块
		topic.setForum(forumService.getById(forumId));//再修改成新版块
		String info = "从id为：" + forum.getId() + " 移动到id为：" + forumId + "的版块";
		LoggerManager.printInfo(ReplyAction.class, info);
		// 更新数据
		topicService.update(topic);
		// 维护主题所在原来版块的特殊属性
		editForumField(forum.getId(), topicId, true);
		// 维护主题所在新版块的特殊属性
		editForumField(forumId, topicId, false);
		return "toShowReply";
	}

	/**
	 * 删除本帖，转到主题列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		// 维护版块中的特殊属性
		editForumField(forumId, topicId, true);
		// 删除指定topicId的主题
		topicService.delete(topicId);
		return "toShowTopic";
	}

	/**
	 * 将本帖转成“精华，置顶，普通”
	 * 
	 * @return
	 * @throws Exception
	 */
	public String changeType() throws Exception {
		// 1:精华，2:置顶，0:普通
		Topic topic = topicService.getById(topicId);
		topic.setType(topicType);
		topicService.update(topic);
		return "toShowReply";
	}

	/**
	 * 维护版块中的特殊属性
	 * 
	 * @param forumId
	 * @param topicId
	 */
	private void editForumField(Long forumId, Long topicId, boolean isDelete) {
		// 要删除主题的回复数量
		int replyCount = topicService.getById(topicId).getReplyCount();
		// 如果当前主题是最新主题，那么它的前一个发表主题(也就是版块中最后发表主题的修改)
		Topic lastTopic = null;
//		if (isDelete) {
			lastTopic = topicService.getLastTopic(topicId, isDelete);
//		} else {
//			lastTopic = topicService.getById(topicId);
//		}
		if (lastTopic != null) {
			LoggerManager.printInfo(ReplyAction.class, "最新主题的信息：" + lastTopic.getTitle());
		} else {
			LoggerManager.printInfo(ReplyAction.class, "最新主题的信息：" + lastTopic);
		}
		forumService.editField(forumId, replyCount, lastTopic, isDelete);
	}
	// --------------------------------------------------------

	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	public int getTopicType() {
		return topicType;
	}

	public void setTopicType(int topicType) {
		this.topicType = topicType;
	}

	public Long getForumId() {
		return forumId;
	}

	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}

}
