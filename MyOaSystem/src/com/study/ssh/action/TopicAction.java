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

@Controller("topicAction")
@Scope("prototype")
public class TopicAction extends ModelDrivenBaseAction<Topic> {
	private Long forumId;

	/** 显示该主题下的所有回复列表 */
	public String showReply() throws Exception {
		// 准备转到回复列表需要的数据
		Topic topic = topicService.getById(model.getId());
		ActionContext.getContext().put("topic", topic);
		// 准备回复列表，不考虑分页
		List<Reply> replies = replyService.findByTopic(topic);
		ActionContext.getContext().put("replyList", replies);
		return "showReply";
	}

	/** 发表新主题 */
	public String addUI() throws Exception {
		Forum forum = forumService.getById(forumId);
		ActionContext.getContext().put("forum", forum);
		return "addUI";
	}

	/** 发表新主题成功时，转到该主题下的所有回复列表 */
	public String add() throws Exception {
		// 设置属性
		model.setForum(forumService.getById(forumId));
		model.setAuthor(getCurrentUser());
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
		model.setPostTime(new Date());
		// 四个特殊属性的设置放在业务方法中
		// model.setType(type);
		// model.setReplyCount(replyCount);
		// model.setLastReply(lastReply);
		// model.setLastUpdateTime(lastUpdateTime);
		// 保存到数据库中
		topicService.save(model);

		return "toShowReply";
	}

	public Long getForumId() {
		return forumId;
	}

	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}

}
