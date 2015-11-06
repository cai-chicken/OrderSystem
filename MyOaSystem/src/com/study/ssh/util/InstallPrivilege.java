package com.study.ssh.util;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.study.ssh.domain.Privilege;
import com.study.ssh.domain.User;

/**
 * 用户安装权限数据
 * @author anbang
 *
 */
@Component
public class InstallPrivilege {

	@Resource
	private SessionFactory sessionFactory;
	
	@Transactional
	public void install() {
		Session session = sessionFactory.getCurrentSession();
		//1、保存一个管理员的账号
		User user = new User();
		user.setName("超级管理员");
		user.setPassword(DigestUtils.md5Hex("admin"));
		user.setLoginName("admin");
		session.save(user);
		//2、生成本系统所有的权限数据
		Privilege parent, children1, children2, children3, children4, children5;
		//对于"系统管理"
		parent = new Privilege("系统管理", null, null);
		children1 = new Privilege("岗位管理", "/role_list", parent);
		children2 = new Privilege("部门管理", "/department_list", parent);
		children3 = new Privilege("用户管理", "/user_list", parent);
		session.save(parent);
		session.save(children1);
		session.save(children2);
		session.save(children3);
		//对于"岗位管理"，不考虑UI后缀
		session.save(new Privilege("岗位添加", "/role_add", children1));
		session.save(new Privilege("岗位删除", "/role_delete", children1));
		session.save(new Privilege("岗位修改", "/role_edit", children1));
		session.save(new Privilege("岗位列表", "/role_list", children1));
		//对于"部门管理"
		session.save(new Privilege("部门列表", "/department_list", children2));
		session.save(new Privilege("部门删除", "/department_delete", children2));
		session.save(new Privilege("部门添加", "/department_add", children2));
		session.save(new Privilege("部门修改", "/department_edit", children2));
		//对于"用户管理"
		session.save(new Privilege("用户列表", "/user_list", children3));
		session.save(new Privilege("用户删除", "/user_delete", children3));
		session.save(new Privilege("用户添加", "/user_add", children3));
		session.save(new Privilege("用户修改", "/user_edit", children3));
		session.save(new Privilege("初始化密码", "/user_initPassword", children3));
		//对于"网上交流" --------------------------------------------------------------------
		parent = new Privilege("网上交流", null, null);
		children1 = new Privilege("论坛管理", "/forumManage_list", parent);
		children2 = new Privilege("论坛", "/forum_list", parent);
		session.save(parent);
		session.save(children1);
		session.save(children2);
		//对于"论坛管理"
		session.save(new Privilege("论坛添加", "/forumManage_add", children1));
		session.save(new Privilege("论坛删除", "/forumManage_delete", children1));
		session.save(new Privilege("论坛修改", "/forumManage_edit", children1));
		session.save(new Privilege("论坛列表", "/forumManage_list", children1));
		session.save(new Privilege("论坛上移", "/forumManage_moveUp", children1));
		session.save(new Privilege("论坛下移", "/forumManage_moveDown", children1));
		//对于"论坛"，后续补充
		//TODO
		// --------------------------------------------------------------------
		parent = new Privilege("审批流转", null, null);
		children1 = new Privilege("审批流程管理", "/processDefinition_list", parent);
		children2 = new Privilege("申请模板管理", "/template_list", parent);
		children3 = new Privilege("起草申请", "/flow_templateList", parent);
		children4 = new Privilege("待我审批", "/flow_myTaskList", parent);
		children5 = new Privilege("我的申请查询", "/flow_myApplicationList", parent);
		session.save(parent);
		session.save(children1);
		session.save(children2);
		session.save(children3);
		session.save(children4);
		session.save(children5);
		//TODO 里面详细的每个子权限，后续补充
	}
	
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		InstallPrivilege ip = (InstallPrivilege) ac.getBean("installPrivilege");
		ip.install();
		LoggerManager.printInfo(InstallPrivilege.class, "权限数据安装成功");
	}
}
