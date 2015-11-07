package com.study.ssh.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.ssh.base.BaseDaoImpl;
import com.study.ssh.domain.Application;
import com.study.ssh.service.ApplicationService;

@Service("applicationService")
@Transactional
public class ApplicationServiceImpl extends BaseDaoImpl<Application> implements ApplicationService {
	@Resource
	private ProcessEngine processEngine;

	@Override
	public void submit(Application application) {
		//保存"申请信息"
		getSession().save(application);
		//将"申请信息"当做流程变量，启动实例
		Map<String, Object> variablesMap = new HashMap<String, Object>();
		variablesMap.put("application", application);
		String pdKey = application.getApplicationTemplate().getProcessDefinitionKey();
		ProcessInstance pi = processEngine.getExecutionService().startProcessInstanceById(pdKey);
		//办理完第一个任务"提交申请"
		Task task = processEngine.getTaskService()//
				.createTaskQuery()//查询出本流程实例中当前仅有的一个任务"提交申请"
				.processInstanceId(pi.getKey())//
				.uniqueResult();
		processEngine.getTaskService().completeTask(task.getId());
	}

}
