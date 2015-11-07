package com.study.ssh.service;

import com.study.ssh.base.BaseDao;
import com.study.ssh.domain.Application;

public interface ApplicationService extends BaseDao<Application> {

	/**
	 * 保存"申请信息"，并且启动流程实例
	 * @param application
	 */
	void submit(Application application);

}
