package com.bookmanage.ssh.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookmanage.ssh.dao.BookDao;
import com.bookmanage.ssh.model.Book;
import com.bookmanage.ssh.service.BookService;
import com.bookmanage.ssh.util.QueryHelper;

@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService {
	@Resource
	BookDao bookDao;

	@Override
	public void deleteById(Long id) {
		bookDao.delete(id);
	}

	@Override
	public void save(Book model) {
		bookDao.save(model);
	}

	@Override
	public Book getById(Long id) {
		return bookDao.getById(id);
	}

	@Override
	public void update(Book model) {
		bookDao.update(model);
	}

	@Override
	public void preparePageBean(Class<Book> class1, String string, int pageNum, int pageSize) {
		new QueryHelper(class1, string)//
				.addOrderProperty("b.id", false)// 添加排序条件
				.preparePageBean(bookDao, pageNum, pageSize);
	}

}
