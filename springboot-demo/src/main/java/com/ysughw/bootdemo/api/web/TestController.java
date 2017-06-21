package com.ysughw.bootdemo.api.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysughw.bootdemo.api.entity.Book;
import com.ysughw.bootdemo.api.entity.PageDto;
import com.ysughw.bootdemo.api.entity.Topic;
import com.ysughw.bootdemo.api.service.BookService;
import com.ysughw.bootdemo.api.utils.FileSpider;
import com.ysughw.bootdemo.api.utils.JsonMapper;

@Controller
@RequestMapping(value="test")
@Api(value = "/test", description = "测试Controller")
public class TestController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private JdbcTemplate jdbcTemplate ;

	private String rootPath = "http://106.3.131.43/QaRes/";
	
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
    
    @ResponseBody
    @ApiOperation(value="fetchFile测试", notes="fetchFile测试")
    @RequestMapping(value="fetchFile",method = RequestMethod.POST)
    public List<Topic> fetchFile(){
    	List<Topic> topicList = jdbcTemplate.query("SELECT CIdx as Id,Ctext as name from d_qcontent where MTIdx = 4 and Ctext like '%.mp3'", new Object[]{}, new BeanPropertyRowMapper<Topic>(Topic.class));
        System.out.println(topicList.size());
        for (Topic topic : topicList) {
			System.out.println("topicId==="+topic.getId());
			String fileName = topic.getName();
			String strArr[] = fileName.split("-");
			String path = strArr[0];
			System.out.println(path);
			String fullUrl = rootPath + path + "/" + fileName;
			System.out.println(fullUrl);
			FileSpider.download(fullUrl , "D:/pepTopic/file/"+fileName);
		}
        System.out.println("=======end==========");
    	return topicList;
    }
    
    @ResponseBody
    @ApiOperation(value="findTopic测试", notes="findTopic测试")
    @RequestMapping(value="/detail/{id}",method = RequestMethod.POST)
    public void findTopic(@PathVariable("id") String topicId){
    	String sql = "SELECT  id,options,body,answer,resolve,remark  from p_topic where id = ? or parent_id = ? ";
		List<Topic> topicList = jdbcTemplate.query(sql, new Object[] {topicId,topicId} , new BeanPropertyRowMapper<Topic>(Topic.class));
		System.out.println(topicList.size());
    }
    
    @ResponseBody
    @ApiOperation(value="aaa测试", notes="aaa测试")
    @RequestMapping(value="/aaa",method = RequestMethod.POST)
    public void aaa(@RequestBody PageDto<Topic> pageParam){
		System.out.println(pageParam);
    }
    
    @ResponseBody
    @ApiOperation(value="converTopic测试", notes="converTopic测试")
    @RequestMapping(value="converTopic",method = RequestMethod.POST)
    public void converTopic(){
    	List<Topic> topicList = jdbcTemplate.query("SELECT id,options,body,answer,resolve,remark from p_topic where options like '%options%'", new Object[]{}, new BeanPropertyRowMapper<Topic>(Topic.class));
    	int errNum = 0;
    	try{
    		System.out.println(topicList.size());
    		for (Topic topic : topicList) {
    			final String topicId = topic.getId();
    			final String options = topic.getOptions();
    			System.out.println("topicId==="+topicId);
    			try{
    				HashMap<String, Object> oldMap = (HashMap<String, Object>) JsonMapper.fromJsonString(options, HashMap.class);
	    			List<HashMap<String, Object>> optionArr = (List) oldMap.get("options");
	    			if(optionArr == null || optionArr.size()==0){
	    				System.out.println("==========continue=========");
	    				continue;
	    			}else{
	    				HashMap<String, Object> newMap = new HashMap<String, Object>();
	    				newMap.put("viewModel", "ABCD");
	    				newMap.put("data", optionArr.get(0).get("ABCDS"));
	    				final String newOption = JsonMapper.toJsonString(newMap);
	    				jdbcTemplate.update("update p_topic set options = ? where id = ?",new PreparedStatementSetter(){
	    				       public void setValues(PreparedStatement ps) throws SQLException {
	    				           ps.setString(1,newOption);
	    				           ps.setString(2,topicId);
	    				       }
	    				});	    			
	    			}
    			}catch(Exception e){
    				System.out.println("fromJson Error == topicId==="+topicId);
    				System.out.println("ErrorNum =="+errNum++);
    				e.printStackTrace();
    				continue;
    			}
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	//batchUpdate(topicList);
        System.out.println("=======end==========");
    }
    
  //批量操作    适合于增、删、改操作  
    public int[] batchUpdate(final List<Topic> topicList) {  
          
        int[] updateCounts = jdbcTemplate.batchUpdate(  
                "update p_topic set options = ? where id = ?",  
                new BatchPreparedStatementSetter() {  
                        @Override  
                        public void setValues(PreparedStatement ps, int i) throws SQLException {  
                            ps.setString(1, ((Topic)topicList.get(i)).getOptions());  
                            ps.setString(2, ((Topic)topicList.get(i)).getId());  
                        }  
                          
                        @Override  
                        public int getBatchSize() {  
                            return topicList.size();  
                        }  
                }   
        );  
          
        return updateCounts;  
    }  
    
}
