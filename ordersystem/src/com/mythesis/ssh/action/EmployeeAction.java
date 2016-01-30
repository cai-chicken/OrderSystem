package com.mythesis.ssh.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mysql.jdbc.Util;
import com.mythesis.ssh.base.ModelDrivenBaseAction;
import com.mythesis.ssh.model.Employee;
import com.mythesis.ssh.model.Privilege;
import com.mythesis.ssh.model.Role;
import com.mythesis.ssh.util.LoggerManager;
import com.mythesis.ssh.util.QueryHelper;
import com.mythesis.ssh.util.StringUtil;
import com.mythesis.ssh.util.Utils;
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
	private Long[] roleIds;
	private String oldPwd;//旧密码
	/** 列表 */
	public String list() throws Exception {
		// 考虑分页,没有进行模糊查询处理
		// TODO
		new QueryHelper(Employee.class, "e")//
			.addOrderProperty("e.id", false)//
			.addWhereCondition(!StringUtil.isEmpty(model.getName()), "e.name like ?", "%"+model.getName()+"%")//员工名称过滤条件
			.addWhereCondition(!StringUtil.isEmpty(model.getAddress()), "e.address like ?", "%"+model.getAddress()+"%")//家庭地址过滤条件
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
		// 1、准备所有的角色数据
		// 1.1、从数据库中取出相应的数据
		List<Role> roles = roleService.findAll();
		// 1.2、放入到ValueStack对象的stack中
		ActionContext.getContext().put("roleList", roles);
		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {
		// 1、保存数据到数据库中,执行相关service的save(model)方法
		model.setPassword(DigestUtils.md5Hex("1234"));//密码使用MD5加密
		model.setRoles(new HashSet<Role>(roleService.getByIds(roleIds)));//设置角色
		employeeService.save(model);
		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		// 1、找到要修改的员工对象
		Employee employee = employeeService.getById(model.getId());
		ActionContext.getContext().put("employee", employee);
		// 2、准备权限数据
		List<Role> roles = roleService.findAll();
		ActionContext.getContext().put("roleList", roles);
		// 3、回显用户的角色信息
		if (employee.getRoles() != null) {
			roleIds = new Long[employee.getRoles().size()];
			int index = 0;
			for(Role role:employee.getRoles()){
				roleIds[index++] = role.getId();
			}
		}
		ActionContext.getContext().put("roleIds", roleIds);
		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		// 1、从数据库中找到对应的id的对象
		Employee employee = employeeService.getById(model.getId());
		// 2、设置需要修改的属性
		employee.setNum(model.getNum());
		employee.setName(model.getName());
		employee.setLoginName(model.getLoginName());
		employee.setSex(model.getSex());
		employee.setBirthday(model.getBirthday());
		employee.setAddress(model.getAddress());
		employee.setPhoneNumber(model.getPhoneNumber());
		// 3、更新到数据库中
		employee.setRoles(new HashSet<Role>(roleService.getByIds(roleIds)));//设置角色
		employeeService.update(employee);
		return "toList";
	}
	
	public String loginUI() throws Exception {
		return "loginUI";
	}
	
	/** 登录*/
	public String login() throws Exception {
		String loginName = model.getLoginName();
		String password = DigestUtils.md5Hex(model.getPassword());
		Employee employee = employeeService.findByLoginNameAndPwd(loginName, password);
		if (employee == null) {
			Utils.displayErrorInfo("errorInfo", "用户名或密码错误");
			return "loginUI";
		} else {
			//将用户数据保存到Session中
			ActionContext.getContext().getSession().put("employeeLogin", employee);
			//得到当前登录用户的顶级权限
//			List<Privilege> privileges = employeeService.getTopPrivilegesByEmployee(employee);
			List<Privilege> privileges = privilegeService.getTopPrivileges();
//			for(Privilege privilege:privileges){
//				System.out.println("顶级权限id:"+privilege.getId()+",顶级权限name:"+privilege.getName());
//			}
			ActionContext.getContext().getSession().put("privilegeList", privileges);
			//组成一定格式的json数据
			return "index";
		}
	}
	
	/**
	 * 退出
	 */
	public String logout() throws Exception {
		Utils.removeCurrentEmployee();
		return "loginUI";
	}
	
	/**
	 * 显示个人信息
	 */
	public String showInfo() throws Exception{
		Employee employee = Utils.getCurrentEmployee();
		if (employee == null) {
			Utils.displayErrorInfo("errorInfo", "登录信息过期，请重新登录。");
			return "loginUI";
		} else {
			List<Role> roles = new ArrayList<Role>(employee.getRoles());
			ActionContext.getContext().put("roleList", roles);
			return "showInfo";
		}
	}
	
	/**
	 * 修改密码
	 */
	public String modifyPwd() throws Exception{
		//检测旧密码是否输入正确
		String oldPwdMd5 = DigestUtils.md5Hex(oldPwd);
		if (Utils.isExistsCurrentEmployee()) {
			Employee employee = employeeService.getById(model.getId());
			if (oldPwdMd5.equals(employeeService.getOldPwd(employee.getId()))) {
				String newPwdMd5 = DigestUtils.md5Hex(model.getPassword());
				employee.setPassword(newPwdMd5);
				System.out.println("当前对象的信息--------------》》》》》》"+employee);
				employeeService.update(employee);
				return "toShowInfo";
			} else {
				Utils.displayErrorInfo("errorPwd", "您输入的旧密码不正确，请重新输入!");
				return "toModifyPwdUI";
			} 
		} else {
			Utils.displayErrorInfo("errorIngo", "登录信息过期，请重新登录！");
			return "loginUI";
		}
	}
	/**修改密码页面*/
	public String modifyPwdUI() throws Exception{
		if (Utils.isExistsCurrentEmployee()) {
			return "modifyPwdUI";
		} else {
			Utils.displayErrorInfo("errorInfo", "登录信息过期，请重新登录。");
			return "loginUI";
		}
	}

	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}
	
}
