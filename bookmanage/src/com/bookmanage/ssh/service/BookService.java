package com.bookmanage.ssh.service;

import com.bookmanage.ssh.model.Book;

public interface BookService {

	/*List<Book> findAll();*/

	void deleteById(Long id);

	void save(Book model);

	Book getById(Long id);

	void update(Book model);

	void preparePageBean(Class<Book> class1, String string, int pageNum, int pageSize);

}
