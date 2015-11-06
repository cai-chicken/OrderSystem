package com.study.ssh.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.study.ssh.base.ModelDrivenBaseAction;
import com.study.ssh.domain.Forum;
import com.study.ssh.domain.Topic;

@Controller("forumAction")
@Scope("prototype")
public class ForumAction extends ModelDrivenBaseAction<Forum> {

	/**列表*/
	public String list() throws Exception {
		List<Forum> forums = forumService.findAll();
		ActionContext.getContext().put("forumList", forums);
		return "list";
	}
	
	/**显示板块下的所有主题*/
	public String showTopic() throws Exception {
		//准备对应板块数据
		Forum forum = forumService.getById(model.getId());
		ActionContext.getContext().put("forum", forum);
		//不考虑分页
		List<Topic> topics = topicService.findAll();
		ActionContext.getContext().put("topicList", topics);
		return "showTopic";
	}
	
	/**显示板块下的所有主题*/
	public String showLastTopic() throws Exception {
		return "showLastTopic";
	}
}
