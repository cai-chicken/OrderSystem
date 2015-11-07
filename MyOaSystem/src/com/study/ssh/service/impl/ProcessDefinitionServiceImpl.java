package com.study.ssh.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;

import org.drools.lang.DRLParser.neg_operator_key_return;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessDefinitionQuery;
import org.jbpm.api.ProcessEngine;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.ssh.service.ProcessDefinitionService;
import com.study.ssh.util.LoggerManager;

@Service("processDefinitionService")
@Transactional
public class ProcessDefinitionServiceImpl implements ProcessDefinitionService {

	@Resource
	private ProcessEngine processEngine;
	
	@Override
	public List<ProcessDefinition> findAllLastVersion() {
		//找出所有的流程定义，并将最新版本放在最后
		List<ProcessDefinition> all = processEngine.getRepositoryService()//
				.createProcessDefinitionQuery()//
				.orderAsc(ProcessDefinitionQuery.PROPERTY_VERSION)//
				.list();
		//根据key，将所有版本放在map中，这样就得到了最新版本
		Map<String, ProcessDefinition> map = new HashMap<String, ProcessDefinition>();
		for(ProcessDefinition pd : all) {
			map.put(pd.getKey(), pd);
		}
		LoggerManager.printInfo(getClass(), "查找出所有最新版本的流程定义");
		return new ArrayList<ProcessDefinition>(map.values());
	}

	@Override
	public void deleteByKey(String key) {
		//根据key查出所有版本的流程定义
		List<ProcessDefinition> list = processEngine.getRepositoryService()//
				.createProcessDefinitionQuery()//
				.processDefinitionKey(key)///
				.list();
		//循环删除
		for (ProcessDefinition pd : list) {
			processEngine.getRepositoryService().deleteDeploymentCascade(pd.getDeploymentId());
		}
		LoggerManager.printInfo(getClass(), "已删除");
	}

	@Override
	public void deploy(ZipInputStream zipInputStream) {
		String deployId = processEngine.getRepositoryService()//
				.createDeployment()//
				.addResourcesFromZipInputStream(zipInputStream)//
				.deploy();
	}

	@Override
	public InputStream getProcessImgResourceAsStream(String id) {
		//根据id找到相应的流程定义对象
		ProcessDefinition pd = processEngine.getRepositoryService()//
				.createProcessDefinitionQuery()//
				.processDefinitionId(id)//
				.uniqueResult();
		//返回图片资源
		return processEngine.getRepositoryService()//
				.getResourceAsStream(pd.getDeploymentId(), pd.getImageResourceName());
	}

}
