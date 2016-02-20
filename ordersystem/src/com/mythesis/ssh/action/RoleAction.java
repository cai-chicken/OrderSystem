package com.mythesis.ssh.action;

import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mythesis.ssh.base.ModelDrivenBaseAction;
import com.mythesis.ssh.model.Privilege;
import com.mythesis.ssh.model.Role;
import com.mythesis.ssh.util.QueryHelper;
import com.mythesis.ssh.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author anbang
 * @description 角色Action类
 * @date 2016年1月16日 下午8:05:28
 */
@Controller("roleAction")
@Scope("prototype")
public class RoleAction extends ModelDrivenBaseAction<Role> {
	private Long[] privilegeIds;
	/** 列表 */
	public String list() throws Exception {
		//准备分页数据
		new QueryHelper(Role.class, "r")//
			.addWhereCondition(!StringUtil.isEmpty(model.getName()), "r.name like ?", "%"+model.getName()+"%")//角色名称过滤条件
			.preparePageBean(roleService, pageNum, pageSize);
		ActionContext.getContext().put("r", model);
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
		//准备所有的权限数据
		List<Privilege> privileges = privilegeService.findAll();
		//放到值栈中的Map中
		ActionContext.getContext().put("privilegeList", privileges);
		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {
		// 1、保存数据到数据库中,执行相关service的save(model)方法
		model.setPrivileges(new HashSet<Privilege>(privilegeService.getByIds(privilegeIds)));
		roleService.save(model);
		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		// 1、找到对应的id的对象
		Role role = roleService.getById(model.getId());
		// 2、将其放入到ValueStack对象的stack中
		ActionContext.getContext().put("role", role);
		
		//给privilegeIds赋值
		if (role.getPrivileges() != null) {
			privilegeIds = new Long[role.getPrivileges().size()];
			int index = 0;
			for(Privilege privilege : role.getPrivileges()) {
				privilegeIds[index ++] = privilege.getId();
			}
		}
		ActionContext.getContext().put("privilegeIds", privilegeIds);
		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		// 1、从数据库中找到对应的id的对象
		// 2、设置需要修改的属性
		// 3、更新到数据库中
		model.setPrivileges(new HashSet<Privilege>(privilegeService.getByIds(privilegeIds)));
		roleService.update(model);
		return "toList";
	}

	public Long[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(Long[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}
	
}
