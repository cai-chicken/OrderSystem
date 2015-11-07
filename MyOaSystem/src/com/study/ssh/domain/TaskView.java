package com.study.ssh.domain;

import org.jbpm.api.task.Task;

public class TaskView {
	//任务
	private Task task;
	//申请信息
	private Application application;

	public TaskView(Task task, Application application) {
		this.task = task;
		this.application = application;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

}
