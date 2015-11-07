package com.study.ssh.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.ssh.base.BaseDaoImpl;
import com.study.ssh.domain.ApplicationTemplate;
import com.study.ssh.service.ApplicationTemlateService;

@Service("applicationTemplateService")
@Transactional
public class ApplicationTemplateServiceImpl extends BaseDaoImpl<ApplicationTemplate> implements ApplicationTemlateService {

}
