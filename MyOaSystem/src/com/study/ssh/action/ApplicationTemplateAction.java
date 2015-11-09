package com.study.ssh.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import org.jbpm.api.ProcessDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.study.ssh.base.ModelDrivenBaseAction;
import com.study.ssh.domain.ApplicationTemplate;

@Controller("applicationTemplateAction")
@Scope("prototype")
public class ApplicationTemplateAction extends ModelDrivenBaseAction<ApplicationTemplate> {
	private File upload;
	private InputStream inputStream;

	/** 列表 */
	public String list() throws Exception {
		// 1、执行相关service的findAll()
		List<ApplicationTemplate> list = applicationTemplateService.findAll();
		// 2、将其放入到ValueStack对象的Map中
		ActionContext.getContext().put("applicationTemplateList", list);
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		// 1、执行相关service的delete(model.getId())
		applicationTemplateService.delete(model.getId());
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		// 1、准备所有流程定义
		// 2.1、从数据库中取出相应的数据
		List<ProcessDefinition> processDefinitions = processDefinitionService.findAllLastVersion();
		// 2.2、放入到ValueStack对象的stack中
		ActionContext.getContext().put("processDefinitionList", processDefinitions);
		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {
		// 1、保存数据到数据库中,执行相关service的save(model)方法
		String path = saveUploadFile(upload);
		model.setPath(path);
		applicationTemplateService.save(model);
		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		// 1、准备表单模板的数据
		ApplicationTemplate at = applicationTemplateService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(at);
		// 2、准备所有的流程定义数据
		List<ProcessDefinition> list = processDefinitionService.findAllLastVersion();
		ActionContext.getContext().put("processDefinitionList", list);
		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		// 1、从数据库中找到对应的id的对象
		ApplicationTemplate applicationTemplate = applicationTemplateService.getById(model.getId());
		// 2、设置需要修改的属性
		applicationTemplate.setName(model.getName());
		applicationTemplate.setProcessDefinitionKey(model.getProcessDefinitionKey());
		// 表示有重新上传文件
		if (upload != null) {
			// 删除老文件
			File file = new File(applicationTemplate.getPath());
			if (file.exists()) {
				file.delete();
			}
			// 使用新文件
			String path = saveUploadFile(upload);
			applicationTemplate.setPath(path);
		}
		applicationTemplateService.update(applicationTemplate);
		return "toList";
	}

	/** 下载 */
	public String download() throws Exception {
		// 准备下载的资源
		ApplicationTemplate applicationTemplate = applicationTemplateService.getById(model.getId());
		inputStream = new FileInputStream(applicationTemplate.getPath());
		// 准备文件名（解决乱码问题）
		String fileName = URLEncoder.encode(applicationTemplate.getName(), "utf-8");
		ActionContext.getContext().put("fileName", fileName);
		return "download";
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

}
