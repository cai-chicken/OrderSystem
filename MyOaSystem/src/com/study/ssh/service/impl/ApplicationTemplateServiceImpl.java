package com.study.ssh.service.impl;

import java.io.File;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.ssh.base.BaseDaoImpl;
import com.study.ssh.domain.ApplicationTemplate;
import com.study.ssh.service.ApplicationTemlateService;

@Service("applicationTemplateService")
@Transactional
public class ApplicationTemplateServiceImpl extends BaseDaoImpl<ApplicationTemplate>
		implements ApplicationTemlateService {
	@Override
	public void delete(Long id) {
		ApplicationTemplate applicationTemplate = this.getById(id);
		// 删除数据库中的记录
		getSession().delete(applicationTemplate);
		// 删除本地文件
		File file = new File(applicationTemplate.getPath());
		if (file.exists()) {
			file.delete();
		}
	}
}
