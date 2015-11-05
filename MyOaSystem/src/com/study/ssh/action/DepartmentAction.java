package com.study.ssh.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.study.ssh.base.ModelDrivenBaseAction;
import com.study.ssh.domain.Department;
import com.study.ssh.util.DepartmentUtil;

/**
 * 部门管理
 * 
 * @author anbang
 *
 */
@SuppressWarnings("serial")
@Controller("departmentAction")
@Scope("prototype")
public class DepartmentAction extends ModelDrivenBaseAction<Department> {
	// 上级部门的id
	private Long parentId;

	/** 列表 */
	public String list() throws Exception {
		// 1、每次只显示父部门，而点击父部门名称时，则进入该部门对应的子部门列表
		List<Department> departments = null;
		if (parentId == null) {
			departments = departmentService.findTopList();
		} else {
			departments = departmentService.findChildren(parentId);
			Department parent = departmentService.getById(parentId);
			ActionContext.getContext().put("parent", parent);
		}
		// 2、将其放入到ValueStack对象的Map中
		ActionContext.getContext().put("departmentList", departments);
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		// 1、执行相关service的delete(model.getId())
		departmentService.delete(model.getId());
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		//准备所有部门的数据，为了有树状结构的显示，我们不用findAll()方法
		getDepartmentListByTree();
		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {
		// 1、保存数据到数据库中,执行相关service的save(model)方法
		model.setParent(departmentService.getById(parentId));
		departmentService.save(model);
		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		// 准备树状的部门数据
		getDepartmentListByTree();
		// 1、找到对应的id的对象
		Department department = departmentService.getById(model.getId());
		// 2、将其放入到ValueStack对象的stack中
		ActionContext.getContext().getValueStack().push(department);
		// 为了回显父部门的数据
		if (department.getParent() != null) {
			parentId = department.getParent().getId();
		}
		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		// 1、从数据库中找到对应的id的对象
		Department department = departmentService.getById(model.getId());
		// 2、设置需要修改的属性
		department.setName(model.getName());
		department.setDescription(model.getDescription());
		department.setParent(departmentService.getById(parentId));
		// 3、更新到数据库中
		departmentService.update(department);
		return "toList";
	}

	private void getDepartmentListByTree() {
		// 1准备所有部门的数据，为了有树状结构的显示，我们不用findAll()方法
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtil.findDeptByTree(topList);
		// 2放入到ValueStack对象的stack中
		ActionContext.getContext().put("departmentList", departmentList);
	}
	// ----------------------------------------------

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}
