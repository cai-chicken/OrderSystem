package com.bookmanage.ssh.dao.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookmanage.ssh.base.BaseDaoImpl;
import com.bookmanage.ssh.dao.BookDao;
import com.bookmanage.ssh.model.Book;

@Service("bookDao")
@Transactional
public class BookDaoImpl extends BaseDaoImpl<Book> implements BookDao {

}
