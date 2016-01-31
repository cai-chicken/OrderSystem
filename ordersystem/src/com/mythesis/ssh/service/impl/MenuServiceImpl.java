package com.mythesis.ssh.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mythesis.ssh.base.BaseDaoImpl;
import com.mythesis.ssh.model.Menu;
import com.mythesis.ssh.service.MenuService;

/**
 * @author anbang
 * @description 菜单Service实现类
 * @date 2016年1月31日 下午8:03:07
 */
@Service("menuService")
@Transactional
public class MenuServiceImpl extends BaseDaoImpl<Menu> implements MenuService {

}
