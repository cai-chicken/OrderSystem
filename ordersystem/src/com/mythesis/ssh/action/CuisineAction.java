package com.mythesis.ssh.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mythesis.ssh.base.ModelDrivenBaseAction;
import com.mythesis.ssh.model.Cuisine;

/**
 * @author anbang
 * @description 菜系Action
 * @date 2016年1月31日 下午5:11:07
 */
@Controller("cuisineAction")
@Scope("prototype")
public class CuisineAction extends ModelDrivenBaseAction<Cuisine> {

}
