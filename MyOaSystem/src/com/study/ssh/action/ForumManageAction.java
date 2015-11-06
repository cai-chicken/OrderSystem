package com.study.ssh.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.study.ssh.base.ModelDrivenBaseAction;
import com.study.ssh.domain.Forum;

/**
 * 版块管理
 * 
 * @author anbang
 *
 */
@Controller("forumManageAction")
@Scope("prototype")
public class ForumManageAction extends ModelDrivenBaseAction<Forum> {
	/** 列表 */
	public String list() throws Exception {
		// 1、执行相关service的findAll()，需要重写findAll()按照position排序
		List<Forum> forums = forumService.findAll();
		// 2、将其放入到ValueStack对象的Map中
		ActionContext.getContext().put("forumList", forums);
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		// 1、执行相关service的delete(model.getId())
		forumService.delete(model.getId());
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		// 1、不需要回显数据，不做任何操作
		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {
		// 1、保存数据到数据库中,执行相关service的save(model)方法
		//这里需要手动设置position的值，我们写在业务方法里，重写save()方法。
		forumService.save(model);
		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		// 1、找到对应的id的对象
		Forum forum = forumService.getById(model.getId());
		// 2、将其放入到ValueStack对象的stack中
		ActionContext.getContext().getValueStack().push(forum);
		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		//没有特殊属性需要修改，所以直接update(model)
		forumService.update(model);
		return "toList";
	}
	
	/** 上移 */
	public String moveUp() throws Exception {
		forumService.moveUp(model.getId());
		return "toList";
	}
	
	/** 下移 */
	public String moveDown() throws Exception {
		forumService.moveDown(model.getId());
		return "toList";
	}
}
