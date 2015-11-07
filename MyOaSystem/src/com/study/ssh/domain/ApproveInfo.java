package com.study.ssh.domain;

import java.util.Date;

/**
 * 审批信息
 * 
 * @author Administrator
 *
 */
public class ApproveInfo {
	private Long id;
	private Application application;// 申请的信息
	private String comment;// 审批意见
	private boolean approval;// 是否同意
	private User approver;// 审批人
	private Date approveTime;// 审批时间

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
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

	public User getApprover() {
		return approver;
	}

	public void setApprover(User approver) {
		this.approver = approver;
	}

	public Date getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}

}
