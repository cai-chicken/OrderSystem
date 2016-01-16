package com.mythesis.ssh.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mythesis.ssh.base.ModelDrivenBaseAction;
import com.mythesis.ssh.model.Employee;
import com.mythesis.ssh.util.QueryHelper;
import com.mythesis.ssh.util.StringUtil;
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
		// 考虑分页,没有进行模糊查询处理
		// TODO
		new QueryHelper(Employee.class, "e")//
			.addOrderProperty("e.id", false)//
			.addWhereCondition(!StringUtil.isEmpty(model.getName()), "e.name like ?", model.getName())//员工名称过滤条件
			.addWhereCondition(!StringUtil.isEmpty(model.getAddress()), "e.address like ?", model.getAddress())//家庭地址过滤条件
			.addWhereCondition(!StringUtil.isEmpty(model.getSex()), "e.sex=?", model.getSex())//性别过滤条件
			.preparePageBean(employeeService, pageNum, pageSize);
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
		model.setPassword("1234");
		employeeService.save(model);
		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		// 1、找到对应的id的对象
		Employee employee = employeeService.getById(model.getId());
		// 2、将其放入到ValueStack对象的map中
		ActionContext.getContext().put("employee", employee);
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
