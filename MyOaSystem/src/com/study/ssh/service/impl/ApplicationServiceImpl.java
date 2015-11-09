package com.study.ssh.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.ssh.base.BaseDaoImpl;
import com.study.ssh.domain.Application;
import com.study.ssh.domain.ApproveInfo;
import com.study.ssh.domain.TaskView;
import com.study.ssh.domain.User;
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
		ProcessInstance pi = processEngine.getExecutionService().startProcessInstanceByKey(pdKey, variablesMap);
		//办理完第一个任务"提交申请"
		Task task = processEngine.getTaskService()//
				.createTaskQuery()//查询出本流程实例中当前仅有的一个任务"提交申请"
				.processInstanceId(pi.getKey())//
				.uniqueResult();
		processEngine.getTaskService().completeTask(task.getId());
	}

	@Override
	public List<TaskView> getMyTaskViewList(User currentUser) {
		//查询我的工作任务，因为jbpm框架的主键类型是String，所以这里我们使用loginName作为其主键
		String loginName = currentUser.getLoginName();
		List<Task> tasks = processEngine.getTaskService()//
				.createTaskQuery()//
				.assignee(loginName)//
				.list();
		//找到每个任务对应的"申请信息"，这个"申请信息"是我们启动流程实例时的流程变量
		List<TaskView> resultList = new ArrayList<TaskView>();
		for (Task task:tasks) {
			Application application = (Application) processEngine.getTaskService()//
					.getVariable(task.getId(), "application");
			resultList.add(new TaskView(task, application));
		}
		return resultList;
	}

	@Override
	public Set<String> getOutcomesByTaskId(String taskId) {
		return processEngine.getTaskService().getOutcomes(taskId);
	}

	@Override
	public void approve(ApproveInfo approveInfo, String taskId, String outcomes) {
		//1、保存本次的审批信息
		getSession().save(approveInfo);
		//2、办理完本次任务
		Task task = processEngine.getTaskService().getTask(taskId);
		if (outcomes == null) {
			processEngine.getTaskService().completeTask(taskId);
		} else {
			processEngine.getTaskService().completeTask(taskId, outcomes);
		}
		//取出所属的流程是咧，如果取出的为null，则说明流程实例执行完成了，已经完成了历史记录
		ProcessInstance pi = processEngine.getExecutionService().findProcessInstanceById(task.getExecutionId());
		//3、维护申请状态
		Application application = approveInfo.getApplication();
		if (!approveInfo.isApproval()) {
			//如果本次办理任务不同意，则直接结束流程实例，申请的状态为:未通过
			if (pi != null) {
				processEngine.getExecutionService().endProcessInstance(task.getExecutionId(), ProcessInstance.STATE_ENDED);
			}
			application.setStatus(Application.STATUS_REJECTED);
		} else {
			//如果本次办理任务同意，则判断是否是最后一个环节，如果是则申请状态为:已通过，否则是默认的：执行中
			if (pi == null) {
				application.setStatus(Application.STATUS_APPROVED);
			}
		}
		getSession().update(application);
	}

}
