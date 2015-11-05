package com.study.ssh.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.study.ssh.base.ModelDrivenBaseAction;
import com.study.ssh.domain.Role;

/**
 * 岗位管理
 * 
 * @author anbang
 *
 */
@SuppressWarnings("serial")
@Controller("roleAction")
@Scope("prototype")
public class RoleAction extends ModelDrivenBaseAction<Role> {
	/** 列表 */
	public String list() throws Exception {
		// 1、执行相关service的findAll()
		List<Role> roles = roleService.findAll();
		// 2、将其放入到ValueStack对象的Map中
		ActionContext.getContext().put("roleList", roles);
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		// 1、执行相关service的delete(model.getId())
		roleService.delete(model.getId());
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
		roleService.save(model);
		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		// 1、找到对应的id的对象
		Role role = roleService.getById(model.getId());
		// 2、将其放入到ValueStack对象的stack中
		ActionContext.getContext().getValueStack().push(role);
		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		// 1、从数据库中找到对应的id的对象
		// 2、设置需要修改的属性
		// 3、更新到数据库中
		
		roleService.update(model);
		return "toList";
	}
}
