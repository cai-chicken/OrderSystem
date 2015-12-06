package com.study.ssm.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.study.ssm.po.User;
import com.study.ssm.service.UserService;

@Controller
public class UserController {
	@Resource
	private UserService userService;

	@RequestMapping("/queryUser.action")
	public ModelAndView queryUser() throws Exception {
		// 查找用户
		User user = userService.getUserById(10);

		// 创建modelAndView准备填充数据、设置视图
		ModelAndView modelAndView = new ModelAndView();

		// 填充数据
		modelAndView.addObject("user", user);
		// 视图
		modelAndView.setViewName("user/userList");

		return modelAndView;
	}
}
