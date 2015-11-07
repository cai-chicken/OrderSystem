package com.study.ssh.service;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.jbpm.api.ProcessDefinition;

public interface ProcessDefinitionService {

	List<ProcessDefinition> findAllLastVersion();

	void deleteByKey(String key);

	void deploy(ZipInputStream zipInputStream);

	InputStream getProcessImgResourceAsStream(String id);

}
