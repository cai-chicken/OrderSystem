package com.mythesis.ssh.front;

import java.util.List;
import java.util.Random;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mythesis.ssh.base.BaseAction;
import com.mythesis.ssh.base.ModelDrivenBaseAction;
import com.mythesis.ssh.front.model.MenuParam;
import com.mythesis.ssh.model.Comment;
import com.mythesis.ssh.model.Cuisine;
import com.mythesis.ssh.model.Menu;
import com.mythesis.ssh.model.Store;
import com.mythesis.ssh.util.QueryHelper;
import com.mythesis.ssh.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;

@Controller("indexAction")
@Scope("prototype")
public class IndexAction extends ModelDrivenBaseAction<MenuParam> {

	/**
	 * 首页
	 * @return
	 * @throws Exception
	 */
	public String toIndex() throws Exception {
		// 准备店名信息
		Store store = storeService.findByState();
		ActionContext.getContext().put("store", store);
		// 准备等待时长信息
		prepareMsg();
		// 准备五张本店特色菜的轮播图信息
		List<Menu> menus = menuService.getFiveSpecialMenu();
		ActionContext.getContext().put("menus", menus);
		// 准备所有的菜系信息
		List<Cuisine> cuisines = cuisineService.findAll();
		ActionContext.getContext().put("cuisines", cuisines);
		// 准备所有的菜单信息并考虑分页
		boolean asc = "asc".equals(model.getSort())?true:false;
		QueryHelper helper = new QueryHelper(Menu.class, "m")//
				.addWhereCondition(!StringUtil.isEmpty(model.getMenuName()), "m.name like ?", "%" + model.getMenuName() + "%")//
				.addWhereCondition(model.getCuisineId() != 0, "m.cuisine.id = ?", model.getCuisineId())//
				.addOrderProperty(!StringUtil.isEmpty(model.getSort()) && (!"0".equals(model.getSort())), "m.positiveRate", asc);//
		addPriceCondition(helper, model.getPrice1(), model.getPrice2()).preparePageBean(menuService, pageNum, pageSize);
		ActionContext.getContext().put("menuParam", model);
		return "index";
	}
	
	/**
	 * 下单成功页
	 * @return
	 * @throws Exception
	 */
	public String orderSuccess() throws Exception{
		return "orderSuccess";
	}
	
	/**
	 * 已点菜单页
	 * @return
	 * @throws Exception
	 */
	public String orderSuccessList() throws Exception {
		//准备等待时长信息
		prepareMsg();
		//准备已点的菜单信息
		/*List<Menu> orderSuccessList = menuService.getOrderSuccessList();
		ActionContext.getContext().put("list", orderSuccessList);*/
		new QueryHelper(Menu.class, "m")//
			.addWhereCondition("m.count > ?", 0)//
			.addWhereCondition("m.status IS NOT NULL", null)//
			.preparePageBean(menuService, pageNum, pageSize);
		return "orderSuccessList";
	}
	
	/**
	 * 菜单详情页
	 * @return
	 * @throws Exception
	 */
	public String menuDetail() throws Exception {
		//准备提示信息
		prepareMsg();
		//准备菜单信息
		Menu menu = menuService.getById(model.getMenuId());
		ActionContext.getContext().put("menu", menu);
		//对应该菜单的评论列表
		new QueryHelper(Comment.class, "c")//
			.addOrderProperty("c.commentTime", false)//
			.addWhereCondition("c.menu.id = ?", model.getMenuId())//
			.preparePageBean(commentService, pageNum, pageSize);
		return "menuDetail";
	}
	// -----------------------------------------------------------------------------
	private QueryHelper addPriceCondition(QueryHelper helper, Double sPrice, Double ePrice){
		//如果开始价格和结束价格相等，且都为0或为空，则相当于没有价格筛选
		if (sPrice == ePrice && (sPrice == null || sPrice == 0.0)) {
			
		} else if ((sPrice == null || sPrice == 0.0) && (ePrice != null && ePrice > 0)) {
			//如果开始价格为空，则价格小于结束价格
			helper.addWhereCondition("m.price <= ?", ePrice);
		} else if ((ePrice == null || ePrice == 0.0) && (sPrice != null && sPrice > 0)) {
			//如果结束价格为空，则价格大于开始价格
			helper.addWhereCondition("m.price >= ?", ePrice);
		} else if (sPrice != null && sPrice != 0.0 && ePrice != null && ePrice != 0.0) {
			//如果两价格不为空，则大于开始，小于结束
			Double temp = 0.0;
			if(sPrice > ePrice){
				temp = sPrice;
				sPrice = ePrice;
				ePrice = temp;
			}
			helper.addWhereCondition("m.price >= ?", sPrice).addWhereCondition("m.price <= ?", ePrice);
		}
		
		return helper;
	}
	
	/**
	 * 准备等待时长信息
	 */
	public void prepareMsg() {
		int count = menuService.getSuccessOrderCount();
		String msg = "当前没有顾客点餐，现在我们只为您服务。";
		if (count != 0) {
			Random random = new Random();
			int minute = 0;
			for (int i = 0; i < count; i++) {
				minute += random.nextInt(3) + 2;
			}
			msg = "您前面还有" + count + "位顾客在等待上菜，现在点餐，预计" + minute + "分钟后上菜，请耐心等待。";
		}
		ActionContext.getContext().put("msg", msg);
	}
}
