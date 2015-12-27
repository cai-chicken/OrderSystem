package com.bookmanage.ssh.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.bookmanage.ssh.base.ModelDrivenBaseAction;
import com.bookmanage.ssh.model.Book;
import com.opensymphony.xwork2.ActionContext;

@Controller("bookAction")
@Scope("prototype")
public class BookAction extends ModelDrivenBaseAction<Book> {
	/** 列表 */
	public String list() throws Exception {
		
		// 不考虑分页
		// 1、执行相关service的findAll()
		// List<Book> books = bookService.findAll();
		// 2、将其放入到ValueStack对象的Map中
		// ActionContext.getContext().put("bookList", books);

		// 进行分页
		bookService.preparePageBean(Book.class, "b", pageNum, pageSize);
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		// 1、执行相关service的delete(model.getId())
		bookService.deleteById(model.getId());
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		// 1、不需要回显数据，不做任何操作
		// 2、需要回显数据，准备数据
		// 2.1、从数据库中取出相应的数据
		// 2.2、放入到ValueStack对象的stack中
		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {
		// 1、保存数据到数据库中,执行相关service的save(model)方法
		bookService.save(model);
		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		// 1、找到对应的id的对象
		Book book = bookService.getById(model.getId());
		// 2、将其放入到ValueStack对象的stack中
		ActionContext.getContext().getValueStack().push(book);
		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		// 1、从数据库中找到对应的id的对象
		// 2、设置需要修改的属性
		// 3、更新到数据库中
		bookService.update(model);
		return "toList";
	}
}
