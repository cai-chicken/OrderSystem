package com.mythesis.ssh.action;

import java.io.File;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mythesis.ssh.base.ModelDrivenBaseAction;
import com.mythesis.ssh.model.Store;
import com.mythesis.ssh.util.LoggerManager;
import com.mythesis.ssh.util.QueryHelper;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author anbang
 * @description 本店信息的Action类
 * @date 2016年1月27日 下午8:45:26
 */
@Controller("storeAction")
@Scope("prototype")
public class StoreAction extends ModelDrivenBaseAction<Store> {
	private File upload;// 文件上传
	private String uploadContentType;// 文件类型
	private String uploadFileName;// 文件名

	/** 列表 */
	public String list() throws Exception {
		// 准备分页数据
		new QueryHelper(Store.class, "s")//
				.addOrderProperty("s.state", false)// 按是否启用降序
				.preparePageBean(storeService, pageNum, pageSize);
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		// 1、执行相关service的delete(model.getId())
		storeService.delete(model.getId());
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {
		// 1、保存数据到数据库中,执行相关service的save(model)方法
		if (upload != null) {
			String imagePath = saveUploadFile(upload, uploadFileName);
			model.setImage(imagePath);
		} else {
			model.setImage("no image");
		}
		storeService.save(model);
		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		// 1、找到对应的id的对象
		Store store = storeService.getById(model.getId());
		// 2、将其放入到ValueStack对象的stack中
		ActionContext.getContext().put("store", store);
		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		// 1、从数据库中找到对应的id的对象
		Store store = storeService.getById(model.getId());
		// 2、设置需要修改的属性
		store.setName(model.getName());
		store.setDescription(model.getDescription());
		String state = model.getState();
		//如果是启用，则先把原来为启用状态的修改为不启用，因为自始至终，只有一个启用状态
		if ("1".equals(state)) {
			// 将原来状态为1的本店记录修改为0
			Store oldStore = storeService.findByState();
			if (oldStore != null) {
				//如果找到了，则修改
				oldStore.setState("0");
				storeService.update(oldStore);
			}
		}
		store.setState(model.getState());
		// TODO
		// 表示有重新上传文件
		if (upload != null) {
			// 删除老文件
			File file = new File(store.getImage());
			if (file.exists()) {
				file.delete();
			}
			// 使用新文件
			String path = saveUploadFile(upload, uploadFileName);
			store.setImage(path);
		}
		// 3、更新到数据库中
		storeService.update(store);
		return "toList";
	}

	/**
	 * 修改使用状态
	 * 
	 * @return
	 * @throws Exception
	 */
	public String changeState() throws Exception {
		// 将原来状态为1的本店记录修改为0
		Store oldStore = storeService.findByState();
		if (oldStore != null) {
			//如果找到了，则修改
			oldStore.setState("0");
			storeService.update(oldStore);
		}
		// 将制定id的本店消息记录的使用状态修改为1
		Store store = storeService.getById(model.getId());
		store.setState("1");
		storeService.update(store);
		LoggerManager.printInfo(getClass(), "修改状态成功");
		return "toList";
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
