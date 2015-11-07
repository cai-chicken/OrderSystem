package com.study.ssh.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.study.ssh.base.BaseAction;
import com.study.ssh.domain.Application;
import com.study.ssh.domain.ApplicationTemplate;
import com.study.ssh.util.QueryHelper;

@Controller("flowAction")
@Scope("prototype")
public class FlowAction extends BaseAction {
	private File upload;
	private String taskId;
	private Long applicationId;
	private Long applicationTemplateId;
	private String status;// 过滤分页的条件

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * "起草申请"
	 * 
	 * @return
	 * @throws Exception
	 */
	public String applicationTemplateList() throws Exception {
		List<ApplicationTemplate> list = applicationTemplateService.findAll();
		ActionContext.getContext().put("applicationTemplageList", list);
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
		//准备分页数据，构建查询条件的技巧，是页面中有可能影响分页的属性
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
		return "myTaskList";
	}

	/**
	 * "审批处理"页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String approveUI() throws Exception {
		return "approveUI";
	}

	/**
	 * "审批处理"成功返回到"待我审批"页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String approve() throws Exception {
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

}
