package com.mythesis.ssh.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mythesis.ssh.base.ModelDrivenBaseAction;
import com.mythesis.ssh.model.Cuisine;
import com.mythesis.ssh.util.QueryHelper;
import com.mythesis.ssh.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author anbang
 * @description 菜系Action
 * @date 2016年1月31日 下午5:11:07
 */
@Controller("cuisineAction")
@Scope("prototype")
public class CuisineAction extends ModelDrivenBaseAction<Cuisine> {
	/** 列表 */
	public String list() throws Exception {
		new QueryHelper(Cuisine.class, "c")//
			.addOrderProperty("c.id", false)//
			.addWhereCondition(!StringUtil.isEmpty(model.getName()), "c.name like ?", "%"+model.getName()+"%")//
			.preparePageBean(cuisineService, pageNum, pageSize);
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		// 1、执行相关service的delete(model.getId())
		cuisineService.delete(model.getId());
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {
		// 1、保存数据到数据库中,执行相关service的save(model)方法
		cuisineService.save(model);
		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		// 1、找到对应的id的对象
		Cuisine cuisine = cuisineService.getById(model.getId());
		// 2、将其放入到ValueStack对象的stack中
		ActionContext.getContext().put("cuisine", cuisine);
		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		// 1、从数据库中找到对应的id的对象
		// 2、设置需要修改的属性
		// 3、更新到数据库中
		cuisineService.update(model);
		return "toList";
	}
}
