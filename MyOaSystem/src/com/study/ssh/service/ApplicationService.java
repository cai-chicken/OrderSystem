package com.study.ssh.service;

import java.util.List;
import java.util.Set;

import com.study.ssh.base.BaseDao;
import com.study.ssh.domain.Application;
import com.study.ssh.domain.ApproveInfo;
import com.study.ssh.domain.TaskView;
import com.study.ssh.domain.User;

public interface ApplicationService extends BaseDao<Application> {

	/**
	 * 保存"申请信息"，并且启动流程实例
	 * @param application
	 */
	void submit(Application application);

	/**
	 * "我的任务列表"
	 * @param currentUser
	 * @return
	 */
	List<TaskView> getMyTaskViewList(User currentUser);

	/**
	 * 获取指定任务活动中所有流出的连线名称
	 * @param taskId
	 * @return
	 */
	Set<String> getOutcomesByTaskId(String taskId);

	/**
	 * 调用业务方法(保存本次的审批信息，并办理完任务，维护申请的状态)
	 * @param approveInfo 本次的审批信息
	 * @param taskId 任务id
	 * @param outcomes 
	 */
	void approve(ApproveInfo approveInfo, String taskId, String outcomes);

}
