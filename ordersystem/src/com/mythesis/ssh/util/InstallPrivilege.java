package com.mythesis.ssh.util;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mythesis.ssh.model.Employee;
import com.mythesis.ssh.model.Privilege;


/**
 * @author anbang
 * @description 用于安装权限数据，全文只执行一次
 * @date 2016年1月16日 下午2:08:44
 */
@Component
public class InstallPrivilege {

	@Resource
	private SessionFactory sessionFactory;
	
	@Transactional
	public void install() {
		Session session = sessionFactory.getCurrentSession();
		//1、保存一个管理员的账号
		Employee user = new Employee();
		user.setName("超级管理员");
		user.setPassword(DigestUtils.md5Hex("admin"));
		user.setLoginName("admin");
		session.save(user);
		//2、生成本系统所有的权限数据
		Privilege parent, children1, children2, children3, children4;
		//对于"系统管理"
		parent = new Privilege("系统管理", null, null);
		children1 = new Privilege("员工管理", "/employee_list", parent);
		children2 = new Privilege("角色管理", "/role_list", parent);
		children3 = new Privilege("个人资料", "/employee_showInfo", parent);
		session.save(parent);
		session.save(children1);
		session.save(children2);
		session.save(children3);
		//对于"员工管理"，不考虑UI后缀
		session.save(new Privilege("新增员工", "/employee_add", children1));
		session.save(new Privilege("编辑员工", "/employee_edit", children1));
		session.save(new Privilege("删除员工", "/employee_delete", children1));
		session.save(new Privilege("员工列表", "/employee_list", children1));
		//对于"角色管理"
		session.save(new Privilege("角色列表", "/role_list", children2));
		session.save(new Privilege("角色删除", "/role_delete", children2));
		session.save(new Privilege("角色添加", "/role_add", children2));
		session.save(new Privilege("角色修改", "/role_edit", children2));
		//对于"菜单管理" --------------------------------------------------------------------
		parent = new Privilege("菜单管理", null, null);
		children1 = new Privilege("菜单管理", "/menu_list", parent);
		children2 = new Privilege("菜系管理", "/cuisine_list", parent);
		children3 = new Privilege("评论管理", "/comment_list", parent);
		children4 = new Privilege("下单成功", "/menu_orderSuccess", parent);
		session.save(parent);
		session.save(children1);
		session.save(children2);
		session.save(children3);
		session.save(children4);
		//对于"菜单管理"
		session.save(new Privilege("添加菜单", "/menu_add", children1));
		session.save(new Privilege("删除菜单", "/menu_delete", children1));
		session.save(new Privilege("菜单修改", "/menu_edit", children1));
		session.save(new Privilege("菜单列表", "/menu_list", children1));
		//对于"菜系管理"
		session.save(new Privilege("添加菜系", "/cuisine_add", children2));
		session.save(new Privilege("删除菜系", "/cuisine_delete", children2));
		session.save(new Privilege("菜系修改", "/cuisine_edit", children2));
		session.save(new Privilege("菜系列表", "/cuisine_list", children2));
		//对于"下单成功"
		session.save(new Privilege("修改烹饪状态", "/order_status", children4));
		//对于"统计管理" --------------------------------------------------------------------
		parent = new Privilege("统计管理", null, null);
		children1 = new Privilege("菜单销售情况", "/count_menuStatus", parent);
		children2 = new Privilege("菜系销售情况", "/count_cuisineStatus", parent);
		children3 = new Privilege("财产收入情况", "/count_ownershipIncome", parent);
		session.save(parent);
		session.save(children1);
		session.save(children2);
		session.save(children3);
		//对于"其它管理" --------------------------------------------------------------------
		parent = new Privilege("其它管理", null, null);
		children1 = new Privilege("本店信息", "/store_list", parent);
		children2 = new Privilege("桌椅管理", "/chair_list", parent);
		children3 = new Privilege("知识库管理", "/knowledge_list", parent);
		session.save(parent);
		session.save(children1);
		session.save(children2);
		session.save(children3);
		//对于"本店信息管理"
		session.save(new Privilege("本店信息列表", "/store_list", children1));
		session.save(new Privilege("编辑本店信息", "/store_edit", children1));
		session.save(new Privilege("删除本店信息", "/store_delete", children1));
		session.save(new Privilege("新增本店信息", "/store_add", children1));
		session.save(new Privilege("启用本店信息", "/store_use", children1));
		//对于"桌椅管理"
		session.save(new Privilege("桌椅列表", "/chair_list", children2));
		session.save(new Privilege("删除桌椅", "/chair_delete", children2));
		session.save(new Privilege("添加桌椅", "/chair_add", children2));
		session.save(new Privilege("桌椅修改", "/chair_edit", children2));
		//对于"知识库管理"
		session.save(new Privilege("知识库列表", "/knowledge_list", children3));
		session.save(new Privilege("删除知识库", "/knowledge_delete", children3));
		session.save(new Privilege("添加知识库", "/knowledge_add", children3));
		session.save(new Privilege("知识库修改", "/knowledge_edit", children3));
	}
	
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		InstallPrivilege ip = (InstallPrivilege) ac.getBean("installPrivilege");
		ip.install();
		LoggerManager.printInfo(InstallPrivilege.class, "权限数据安装成功");
	}
}
