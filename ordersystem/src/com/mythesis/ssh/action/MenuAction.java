package com.mythesis.ssh.action;

import java.io.File;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mythesis.ssh.base.ModelDrivenBaseAction;
import com.mythesis.ssh.model.Cuisine;
import com.mythesis.ssh.model.Menu;
import com.mythesis.ssh.util.QueryHelper;
import com.mythesis.ssh.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author anbang
 * @description 菜单Action类
 * @date 2016年1月31日 下午8:01:19
 */
@Controller("menuAction")
@Scope("prototype")
public class MenuAction extends ModelDrivenBaseAction<Menu> {
	private Long cuisineId;
	private File upload;// 文件上传
	private String uploadContentType;// 文件类型
	private String uploadFileName;// 文件名

	/** 列表 */
	public String list() throws Exception {
		// 准备菜系数据
		prepareCuisine();
		// 准备分页数据
		new QueryHelper(Menu.class, "m")//
				.addOrderProperty("m.id", false)//
				.addWhereCondition(!StringUtil.isEmpty(model.getName()), "m.name like ?", "%" + model.getName() + "%")// 菜名过滤
				.addWhereCondition(!StringUtil.isEmpty(model.getIsSpecial()), "m.isSpecial=?", model.getIsSpecial())// 是否特色菜过滤条件
				.addWhereCondition((cuisineId != null && 0 != cuisineId), "m.cuisine.id = ?", cuisineId)//
				.preparePageBean(menuService, pageNum, pageSize);
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		// 1、执行相关service的delete(model.getId())
		menuService.delete(model.getId());
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		prepareCuisine();
		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {
		// 1、保存数据到数据库中,执行相关service的save(model)方法
		if (cuisineId != null) {
			model.setCuisine(cuisineService.getById(cuisineId));
		}
		// 2、保存图片路径
		if (upload != null) {
			String imagePath = saveUploadFile(upload, uploadFileName);
			model.setImage(imagePath);
		} else {
			model.setImage("no image");
		}
		menuService.save(model);
		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		// 1、准备菜单数据
		Menu menu = menuService.getById(model.getId());
		ActionContext.getContext().put("menu", menu);
		// 2、准备菜系数据
		prepareCuisine();
		// 3、回显已选的菜系
		cuisineId = menu.getCuisine().getId();
		ActionContext.getContext().put("cuisineId", cuisineId);
		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		// 1、从数据库中找到对应的id的对象
		Menu menu = menuService.getById(model.getId());
		// 2、设置需要修改的属性
		menu.setNum(model.getNum());
		menu.setName(model.getName());
		menu.setPrice(model.getPrice());
		// 表示有重新上传文件
		if (upload != null) {
			// 删除老文件
			File file = new File(menu.getImage());
			if (file.exists()) {
				file.delete();
			}
			// 使用新文件
			String path = saveUploadFile(upload, uploadFileName);
			menu.setImage(path);
		}
		menu.setDescription(model.getDescription());
		menu.setIsSpecial(model.getIsSpecial());
		if (cuisineId != null) {
			menu.setCuisine(cuisineService.getById(cuisineId));
		}
		menuService.update(menu);
		// 3、更新到数据库中
		return "toList";
	}

	/**
	 * 准备菜系数据
	 */
	private void prepareCuisine() {
		List<Cuisine> cuisines = cuisineService.findAll();
		ActionContext.getContext().put("cuisineList", cuisines);
	}

	public Long getCuisineId() {
		return cuisineId;
	}

	public void setCuisineId(Long cuisineId) {
		this.cuisineId = cuisineId;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

}
