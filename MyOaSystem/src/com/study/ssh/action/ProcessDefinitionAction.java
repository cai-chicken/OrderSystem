package com.study.ssh.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.jbpm.api.ProcessDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.study.ssh.base.BaseAction;

@Controller("processDefinitionAction")
@Scope("prototype")
public class ProcessDefinitionAction extends BaseAction {
	private File upload;// 文件上传
	private InputStream inputStream;// 文件下载
	private String key;
	// 流程定义的id
	private String id;

	/** 列表 */
	public String list() throws Exception {
		List<ProcessDefinition> lists = processDefinitionService.findAllLastVersion();
		ActionContext.getContext().put("processDefinitionList", lists);
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		processDefinitionService.deleteByKey(key);
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		return "addUI";
	}

	/** 添加 */
	public String add() throws Exception {
		ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(upload));
		try {
			//部署流程定义
			processDefinitionService.deploy(zipInputStream);
		} finally {
			if (zipInputStream != null) {
				zipInputStream.close();
				zipInputStream = null;
			}
		}
		return "toList";
	}

	/** 查看流程图 */
	public String downloadImgResource() throws Exception {
		inputStream = processDefinitionService.getProcessImgResourceAsStream(id);
		return "downloadImgResource";
	}

	// -------------------------------------------------------------

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
