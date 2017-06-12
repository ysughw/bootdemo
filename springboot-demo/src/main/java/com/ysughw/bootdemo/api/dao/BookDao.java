package com.ysughw.bootdemo.api.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ysughw.bootdemo.api.entity.Book;

@Component
public interface BookDao {

	List<Book> findList(Book book);

}
