package com.study.ssh.domain;

/**
 * 表单模板
 * 
 * @author anbang
 *
 */
public class ApplicationTemplate {
	private Long id;
	private String name;
	private String processDefinitionKey;// 所使用的流程定义的key
	private String path;// 文件在服务器端的存储路径

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}

	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
