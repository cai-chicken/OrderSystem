package com.mythesis.ssh.action;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mythesis.ssh.base.ModelDrivenBaseAction;
import com.mythesis.ssh.model.Chair;

@Controller("chairAction")
@Scope("prototype")
public class ChairAction extends ModelDrivenBaseAction<Chair>{
	
	@Resource
	SessionFactory sessionFactory;
	
	public String list() throws Exception {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Chair table = new Chair();
		table.setNum("table001");
		table.setIsGood(1);
		session.save(table);
		transaction.commit();
		session.close();
		return "list";
	}
}
