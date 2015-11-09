package com.study.ssh.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.study.ssh.base.BaseAction;
import com.study.ssh.domain.Application;
import com.study.ssh.domain.ApplicationTemplate;
import com.study.ssh.domain.ApproveInfo;
import com.study.ssh.domain.TaskView;
import com.study.ssh.util.QueryHelper;

@Controller("flowAction")
@Scope("prototype")
public class FlowAction extends BaseAction {
	private File upload;
	private String taskId;
	private Long applicationId;
	private Long applicationTemplateId;
	private String status;// 过滤分页的条件
	private String comment;// 用户接收审批意见
	private boolean approval;// 用于接收是否同意
	private String outcomes;// 多分支的时候使用

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * "起草申请"
	 * 
	 * @return
	 * @throws Exception
	 */
	public String applicationTemplateList() throws Exception {
		List<ApplicationTemplate> list = applicationTemplateService.findAll();
		ActionContext.getContext().put("applicationTemplateList", list);
		return "applicationTemplateList";
	}

	/**
	 * "单击某一表单模板"进入提交页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String submitUI() throws Exception {
		return "submitUI";
	}

	/**
	 * 提交成功进入到"我的申请查询"
	 * 
	 * @return
	 * @throws Exception
	 */
	public String submit() throws Exception {
		// 封装申请信息
		Application application = new Application();
		application.setApplicant(getCurrentUser());
		application.setApplicationTemplate(applicationTemplateService.getById(applicationTemplateId));
		application.setPath(saveUploadFile(upload));
		application.setApplyTime(new Date());
		application.setStatus(application.STATUS_RUNNING);
		// 格式为：{模板名称}_{申请人姓名}_{申请时间}
		application.setTitle(application.getApplicationTemplate().getName()//
				+ "_" + application.getApplicant().getName()//
				+ "_" + sdf.format(application.getApplyTime()));
		applicationService.submit(application);
		return "toMyApplicationList";
	}

	/**
	 * "我的申请查询"页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String myApplicationList() throws Exception {
		// 准备所有的表单申请数据
		List<ApplicationTemplate> list = applicationTemplateService.findAll();
		ActionContext.getContext().put("applicationTemplateList", list);
		// 准备分页数据，构建查询条件的技巧，是页面中有可能影响分页的属性
		new QueryHelper(Application.class, "a")//
				.addWhereCondition("a.applicant=?", getCurrentUser())//
				.addWhereCondition("a.applicationTemplate.id=?", applicationTemplateId)//
				.addWhereCondition(StringUtils.isNotBlank(status), "a.status=?", status)//
				.addOrderProperty("a.applyTime", false)//
				.preparePageBean(applicationService, pageNum, pageSize);
		return "myApplicationList";
	}

	/**
	 * "待我审批"页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String myTaskList() throws Exception {
		// 这里没有考虑分页
		List<TaskView> taskViews = applicationService.getMyTaskViewList(getCurrentUser());
		ActionContext.getContext().put("taskViewList", taskViews);
		return "myTaskList";
	}

	/**
	 * "审批处理"页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String approveUI() throws Exception {
		// 准备数据:获取指定任务活动中所有流出的连线名称
		Set<String> outComes = applicationService.getOutcomesByTaskId(taskId);
		ActionContext.getContext().put("outcomes", outComes);
		return "approveUI";
	}

	/**
	 * "审批处理"成功返回到"待我审批"页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String approve() throws Exception {
		// 封装审批信息
		ApproveInfo approveInfo = new ApproveInfo();
		// 表单传递过来的数据
		approveInfo.setApplication(applicationService.getById(applicationId));
		approveInfo.setApproval(approval);
		approveInfo.setComment(comment);

		approveInfo.setApprover(getCurrentUser());
		approveInfo.setApproveTime(new Date());
		// 调用业务方法(保存本次的审批信息，并办理完任务，维护申请的状态)
		applicationService.approve(approveInfo, taskId, outcomes);
		return "toMyTaskList";
	}

	/**
	 * "查看审批流转记录"
	 * 
	 * @return
	 * @throws Exception
	 */
	public String approveHistory() throws Exception {
		Application application = applicationService.getById(applicationId);
		ActionContext.getContext().put("approveInfos", application.getApproveInfos());
		return "approveHistory";
	}

	// =====================================================
	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Long getApplicationTemplateId() {
		return applicationTemplateId;
	}

	public void setApplicationTemplateId(Long applicationTemplateId) {
		this.applicationTemplateId = applicationTemplateId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isApproval() {
		return approval;
	}

	public void setApproval(boolean approval) {
		this.approval = approval;
	}

	public String getOutcomes() {
		return outcomes;
	}

	public void setOutcomes(String outcomes) {
		this.outcomes = outcomes;
	}

}
