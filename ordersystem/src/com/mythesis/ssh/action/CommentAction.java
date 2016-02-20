package com.mythesis.ssh.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mythesis.ssh.base.ModelDrivenBaseAction;
import com.mythesis.ssh.model.Comment;
import com.mythesis.ssh.model.Menu;
import com.mythesis.ssh.util.QueryHelper;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author anbang
 * @description 评论Action类
 * @date 2016年2月2日 下午8:34:08
 */
@Controller("commentAction")
@Scope("prototype")
public class CommentAction extends ModelDrivenBaseAction<Comment> {
	private Long menuId;

	public String list() throws Exception {
		// 准备菜单数据
		List<Menu> menus = menuService.findAll();
		ActionContext.getContext().put("menuList", menus);
		// 准备分页数据
		new QueryHelper(Comment.class, "c")//
				.addOrderProperty("c.id", false)//
				.addWhereCondition((menuId != null && 0 != menuId), "c.menu.id = ?", menuId)//
				.addWhereCondition((model.getStar() != 0), "c.star = ?", model.getStar())//
				.preparePageBean(commentService, pageNum, pageSize);
		ActionContext.getContext().put("mId", menuId);
		return "list";
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

}
