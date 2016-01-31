package com.mythesis.ssh.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mythesis.ssh.base.BaseDaoImpl;
import com.mythesis.ssh.model.Cuisine;
import com.mythesis.ssh.service.CuisineService;

/**
 * @author anbang
 * @description 菜系Service实现类
 * @date 2016年1月31日 下午5:13:10
 */
@Service("cuisineService")
@Transactional
public class CuisineServiceImpl extends BaseDaoImpl<Cuisine> implements CuisineService {

}
