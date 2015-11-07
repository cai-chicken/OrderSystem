package com.study.ssh.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.study.ssh.base.ModelDrivenBaseAction;
import com.study.ssh.domain.Reply;
import com.study.ssh.domain.Topic;

@Controller("replyAction")
@Scope("prototype")
public class ReplyAction extends ModelDrivenBaseAction<Reply> {
	private Long topicId;

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
		//设置属性
		model.setAuthor(getCurrentUser());
		model.setPostTime(new Date());
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
		model.setTopic(topicService.getById(topicId));
		//保存数据库
		replyService.save(model);
		return "toShowReply";
	}

	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

}
