package com.mythesis.ssh.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mythesis.ssh.base.BaseDaoImpl;
import com.mythesis.ssh.model.Knowledge;
import com.mythesis.ssh.service.KnowledgeService;

/**
 * @author anbang
 * @description 小知识Service实现类
 * @date 2016年1月31日 下午2:53:40
 */
@Service("knowledgeService")
@Transactional
public class KnowledgeServiceImpl extends BaseDaoImpl<Knowledge> implements KnowledgeService {

}
