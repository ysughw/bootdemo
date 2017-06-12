package com.ysughw.bootdemo.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysughw.bootdemo.api.dao.BookDao;
import com.ysughw.bootdemo.api.entity.Book;

@Service
public class BookService {
	
	@Autowired 
	private BookDao bookDao;
	
	public List<Book> findList(Book book) {
		return bookDao.findList(book);
	}

}
