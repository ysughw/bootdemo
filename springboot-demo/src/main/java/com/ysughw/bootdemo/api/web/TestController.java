package com.ysughw.bootdemo.api.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysughw.bootdemo.api.entity.Book;
import com.ysughw.bootdemo.api.service.BookService;

@Controller
@RequestMapping(value="test")
@Api(value = "/test", description = "测试Controller")
public class TestController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private JdbcTemplate jdbcTemplate ;
	
    @ResponseBody
    @ApiOperation(value="书籍查询", notes="书籍列表")
    @RequestMapping(value="bookList",method = RequestMethod.POST)
    public List<Book> bookList (@RequestBody Book book){
        return bookService.findList(book);
    }
    
    @ResponseBody
    @ApiOperation(value="SpringJdbc测试", notes="SpringJdbc测试")
    @RequestMapping(value="testSpringJdbc",method = RequestMethod.POST)
    public List<Book> testSpringJdbc(){
    	List<Book> bookList = jdbcTemplate.query("select id,name from test_book", new Object[]{}, new BeanPropertyRowMapper<Book>(Book.class));
        return bookList;
    }
    
}
