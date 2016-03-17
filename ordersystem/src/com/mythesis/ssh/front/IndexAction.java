package com.mythesis.ssh.front;

import java.util.Random;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mythesis.ssh.base.BaseAction;
import com.mythesis.ssh.model.Store;
import com.opensymphony.xwork2.ActionContext;

@Controller("indexAction")
@Scope("prototype")
public class IndexAction extends BaseAction {
	
	public String toIndex() throws Exception{
		//准备店名信息
		Store store = storeService.findByState();
		ActionContext.getContext().put("store", store);
		//准备等待时长信息
		int count = menuService.getSuccessOrderCount();
		String msg = "当前没有顾客点餐，我们现在只为您服务。";
		if (count != 0) {
			Random random = new Random();
			int minute = 0;
			for(int i=0; i<count; i++){
				minute += random.nextInt(5)+2;
			}
			msg = "您前面还有"+count+"位顾客在等待上菜，现在点餐，预计"+minute+"分钟后上菜，请耐心等待。";
		}
		ActionContext.getContext().put("msg", msg);
		return "index";
	}
}
