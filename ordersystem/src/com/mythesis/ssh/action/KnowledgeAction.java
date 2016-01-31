package com.mythesis.ssh.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mythesis.ssh.base.ModelDrivenBaseAction;
import com.mythesis.ssh.model.Knowledge;
import com.mythesis.ssh.util.QueryHelper;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author anbang
 * @description 小知识Action类
 * @date 2016年1月31日 下午2:51:37
 */
@Controller("knowledgeAction")
@Scope("prototype")
public class KnowledgeAction extends ModelDrivenBaseAction<Knowledge> {
	/** 列表 */
	public String list() throws Exception {
		new QueryHelper(Knowledge.class, "k")//
			.addOrderProperty("k.id", false)//
			.preparePageBean(knowledgeService, pageNum, pageSize);
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		// 1、执行相关service的delete(model.getId())
		knowledgeService.delete(model.getId());
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {
		// 1、保存数据到数据库中,执行相关service的save(model)方法
		knowledgeService.save(model);
		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		// 1、找到对应的id的对象
		Knowledge knowledge = knowledgeService.getById(model.getId());
		// 2、将其放入到ValueStack对象的stack中
		ActionContext.getContext().put("knowledge", knowledge);
		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		knowledgeService.update(model);
		return "toList";
	}
}
