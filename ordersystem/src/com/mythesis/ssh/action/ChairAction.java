package com.mythesis.ssh.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mythesis.ssh.base.ModelDrivenBaseAction;
import com.mythesis.ssh.model.Chair;
import com.mythesis.ssh.util.QueryHelper;
import com.opensymphony.xwork2.ActionContext;

@Controller("chairAction")
@Scope("prototype")
public class ChairAction extends ModelDrivenBaseAction<Chair> {

	/** 列表 */
	public String list() throws Exception {
		new QueryHelper(Chair.class, "c")//
			.addOrderProperty("c.id", false)//
			.preparePageBean(chairService, pageNum, pageSize);
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		// 1、执行相关service的delete(model.getId())
		chairService.delete(model.getId());
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {
		// 1、保存数据到数据库中,执行相关service的save(model)方法
		chairService.save(model);
		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		// 1、找到对应的id的对象
		Chair chair = chairService.getById(model.getId());
		// 2、将其放入到ValueStack对象的stack中
		ActionContext.getContext().put("chair", chair);
		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		// 1、从数据库中找到对应的id的对象
		// 2、设置需要修改的属性
		// 3、更新到数据库中
		chairService.update(model);
		return "toList";
	}
	
	/**
	 * 修改指定桌椅的可用状态
	 * @return
	 * @throws Exception
	 */
	public String changeState() throws Exception{
		Chair chair = chairService.getById(model.getId());
		if (chair != null) {
			if (chair.getIsGood() == 0) {
				chair.setIsGood(1);
			} else {
				chair.setIsGood(0);;
			}
			chairService.update(chair);
		}
		return "toList";
	}
}
