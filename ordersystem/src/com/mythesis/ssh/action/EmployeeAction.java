package com.mythesis.ssh.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mythesis.ssh.base.ModelDrivenBaseAction;
import com.mythesis.ssh.model.Employee;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * @author anbang
 * @description 员工对应的Action类
 * @date 2016年1月10日 上午11:19:44
 */
@Controller("employeeAction")
@Scope("prototype")
public class EmployeeAction extends ModelDrivenBaseAction<Employee> {
	/** 列表 */
	public String list() throws Exception {
		// 1、执行相关service的findAll()
		List<Employee> employees = employeeService.findAll();
		// 2、将其放入到ValueStack对象的Map中
		ActionContext.getContext().put("employeeList", employees);
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		// 1、执行相关service的delete(model.getId())
		employeeService.delete(model.getId());
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		// TODO
		// 1、准备所有的角色数据
		// 1.1、从数据库中取出相应的数据
		// 1.2、放入到ValueStack对象的stack中
		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {
		// 1、保存数据到数据库中,执行相关service的save(model)方法
		employeeService.save(model);
		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		// 1、找到对应的id的对象
		Employee employee = employeeService.getById(model.getId());
		// 2、将其放入到ValueStack对象的stack中
		ActionContext.getContext().getValueStack().push(employee);
		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		// 1、从数据库中找到对应的id的对象
		// 2、设置需要修改的属性
		// 3、更新到数据库中
		employeeService.update(model);
		return "toList";
	}
}
