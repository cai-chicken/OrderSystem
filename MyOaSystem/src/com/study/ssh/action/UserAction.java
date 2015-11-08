package com.study.ssh.action;

import java.util.HashSet;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.study.ssh.base.ModelDrivenBaseAction;
import com.study.ssh.domain.Department;
import com.study.ssh.domain.Role;
import com.study.ssh.domain.User;
import com.study.ssh.util.DepartmentUtil;
import com.study.ssh.util.QueryHelper;

@Controller("userAction")
@Scope("prototype")
public class UserAction extends ModelDrivenBaseAction<User> {
	private Long departmentId;
	private Long[] roleIds;
	
	/** 列表 */
	public String list() throws Exception {
//		// 1、执行相关service的findAll()
//		List<User> users = userService.findAll();
//		// 2、将其放入到ValueStack对象的Map中
//		ActionContext.getContext().put("userList", users);
		
		//考虑分页
		new QueryHelper(User.class, "u")
			.preparePageBean(userService, pageNum, pageSize);
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		// 1、执行相关service的delete(model.getId())
		userService.delete(model.getId());
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		//准备树状的部门数据
		getDepartmentListByTree();
		//准备所有的岗位信息
		List<Role> roles = roleService.findAll();
		ActionContext.getContext().put("roleList", roles);
		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {
		// 1、给model设置部门属性
		Department department = departmentService.getById(departmentId);
		model.setDepartment(department);
		// 2、给model设置岗位信息
		List<Role> roles = roleService.getByIds(roleIds);
		model.setRoles(new HashSet<Role>(roles));
		// 3、初始化密码
		model.setPassword(DigestUtils.md5Hex("1234"));
		// 4、保存对象
		userService.save(model);
		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		// 1、准备树状的部门数据
		getDepartmentListByTree();
		// 2、准备岗位数据
		List<Role> roles = roleService.findAll();
		ActionContext.getContext().put("roleList", roles);
		// 3、准备需要修改的用户数据
		User user = userService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(user);
		// 4、回显部门
		if (user.getDepartment() != null) {
			departmentId = user.getDepartment().getId();
		}
		// 5、回显岗位
		if (user.getRoles().size() > 0) {
			roleIds = new Long[user.getRoles().size()];
			int index = 0;
			for(Role role : user.getRoles()) {
				roleIds[index ++] = role.getId();
			}
		}
		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		// 1、从数据库中找到对应的id的对象
		// 2、设置需要修改的属性
		// 3、更新到数据库中
		model.setDepartment(departmentService.getById(departmentId));
		model.setRoles(new HashSet<Role>(roleService.getByIds(roleIds)));
		userService.update(model);
		return "toList";
	}
	
	/** 初始化密码 */
	public String initPassword() throws Exception {
		//我们要知道修改的是哪个用户的密码
		User user = userService.getById(model.getId());
		user.setPassword(DigestUtils.md5Hex("1234"));
		userService.update(user);
		return "toList";
	}
	
	/**登录页面*/
	public String loginUI() throws Exception {
		return "loginUI";
	}
	
	/**登录成功时，转到首页*/
	public String login() throws Exception {
		User user = userService.findByNameAndPwd(model.getLoginName(), model.getPassword());
		if (user == null) {
			addFieldError("error", "用户名或密码错误");
			return "loginUI";
		} else {
			ActionContext.getContext().getSession().put("user", user);
			return "toIndex";
		}
	}
	
	/**退出*/
	public String logoutUI() throws Exception {
		ActionContext.getContext().getSession().remove("user");
		return "logoutUI";
	}
	
	private void getDepartmentListByTree() {
		// 1准备所有部门的数据，为了有树状结构的显示，我们不用findAll()方法
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtil.findDeptByTree(topList);
		// 2放入到ValueStack对象的stack中
		ActionContext.getContext().put("departmentList", departmentList);
	}

	//----------------------------------------
	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}	
}
