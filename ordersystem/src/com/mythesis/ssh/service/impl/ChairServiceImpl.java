package com.mythesis.ssh.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mythesis.ssh.base.BaseDaoImpl;
import com.mythesis.ssh.model.Chair;
import com.mythesis.ssh.service.ChairService;

/**
 * @author anbang
 * @description 桌椅Service实现类
 * @date 2016年1月31日 下午5:17:32
 */
@Service("chairService")
@Transactional
public class ChairServiceImpl extends BaseDaoImpl<Chair> implements ChairService {

}
