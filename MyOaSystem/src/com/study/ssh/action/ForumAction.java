package com.study.ssh.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.study.ssh.base.ModelDrivenBaseAction;
import com.study.ssh.domain.Forum;
import com.study.ssh.domain.Topic;
import com.study.ssh.util.QueryHelper;

@Controller("forumAction")
@Scope("prototype")
public class ForumAction extends ModelDrivenBaseAction<Forum> {
	/**
	 * 0 表示查看全部主题<br>
	 * 1 表示只看精华帖
	 */
	private int viewType = 0;

	/**
	 * 0 表示默认排序(所有置顶帖在前面，并按最后更新时间降序排列)<br>
	 * 1 表示只按最后更新时间排序<br>
	 * 2 表示只按主题发表时间排序<br>
	 * 3 表示只按回复数量排序
	 */
	private int orderBy = 0;

	/**
	 * true 表示升序<br>
	 * false 表示降序
	 */
	private boolean asc = false;

	/** 列表 */
	public String list() throws Exception {
		List<Forum> forums = forumService.findAll();
		ActionContext.getContext().put("forumList", forums);
		return "list";
	}

	/** 显示板块下的所有主题 */
	public String showTopic() throws Exception {
		// 准备对应板块数据
		Forum forum = forumService.getById(model.getId());
		ActionContext.getContext().put("forum", forum);
		// 不考虑分页
		// List<Topic> topics = topicService.findByForum(forum);
		// ActionContext.getContext().put("topicList", topics);
		// 准备分页信息
		new QueryHelper(Topic.class, "t")//
				// 过滤条件
				.addWhereCondition("t.forum=?", forum)//
				.addWhereCondition((viewType == 1), "t.type=?", Topic.TYPE_BEST) // 1表示只看精华帖
				// 排序条件
				.addOrderProperty((orderBy == 1), "t.lastUpdateTime", asc) // 1表示只按最后更新时间排序
				.addOrderProperty((orderBy == 2), "t.postTime", asc) // 2表示只按主题发表时间排序
				.addOrderProperty((orderBy == 3), "t.replyCount", asc) // 3表示只按回复数量排序
				.addOrderProperty((orderBy == 0), "(CASE t.type WHEN 2 THEN 2 ELSE 0 END)", false)//
				.addOrderProperty((orderBy == 0), "t.lastUpdateTime", false) // 0
																				// 表示默认排序(所有置顶帖在前面，并按最后更新时间降序排列)
				.preparePageBean(topicService, pageNum, pageSize);
		return "showTopic";
	}

	/** 显示板块下的所有主题 */
	public String showLastTopic() throws Exception {
		return "showLastTopic";
	}

	public int getViewType() {
		return viewType;
	}

	public void setViewType(int viewType) {
		this.viewType = viewType;
	}

	public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	public boolean isAsc() {
		return asc;
	}

	public void setAsc(boolean asc) {
		this.asc = asc;
	}

}
