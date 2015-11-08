package com.study.ssh.action;

import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.study.ssh.base.ModelDrivenBaseAction;
import com.study.ssh.domain.Privilege;
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
	private Long[] privilegeIds;// 用于回显已选的权限

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
		roleService.update(model);
		return "toList";
	}

	/** 设置权限页面 */
	public String setPrivilegeUI() throws Exception {
		// 准备当前岗位的数据
		Role role = roleService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(role);
		// 准备所有的权限数据
		List<Privilege> privileges = privilegeService.findAll();
		ActionContext.getContext().put("privilegeList", privileges);
		//给privilegeIds赋值
		if (role.getPrivileges() != null) {
			privilegeIds = new Long[role.getPrivileges().size()];
			int index = 0;
			for(Privilege privilege : role.getPrivileges()) {
				privilegeIds[index ++] = privilege.getId();
			}
		}
		return "privilegeUI";
	}

	/** 设置权限成功时，返回岗位岗位 */
	public String setPrivilege() throws Exception {
		Role role = roleService.getById(model.getId());
		role.setPrivileges(//
				new HashSet<Privilege>(privilegeService.getByIds(privilegeIds)));
		roleService.update(role);
		return "toList";
	}

	public Long[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(Long[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}

}
