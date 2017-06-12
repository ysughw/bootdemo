package com.ysughw.bootdemo.common.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.client.RestTemplate;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
public class MybatisConfiguration {
    
	@Value("${spring.datasource.url}")  
    private String dbUrl;  
      
    @Value("${spring.datasource.username}")  
    private String username;  
      
    @Value("${spring.datasource.password}")  
    private String password;  
      
    @Value("${spring.datasource.driver-class-name}")  
    private String driverClassName;  
      
    @Value("${spring.datasource.initialSize}")  
    private int initialSize;  
      
    @Value("${spring.datasource.max-idle}")  
    private int minIdle;  
      
    @Value("${spring.datasource.maxActive}")  
    private int maxActive;  
      
    @Value("${spring.datasource.maxWait}")  
    private int maxWait;  
      
    @Value("${spring.datasource.time-between-eviction-runs-millis}")  
    private int timeBetweenEvictionRunsMillis;  
      
    @Value("${spring.datasource.min-evictable-idle-time-millis}")  
    private int minEvictableIdleTimeMillis;  
      
    @Value("${spring.datasource.validationQuery}")  
    private String validationQuery;  
      
    @Value("${spring.datasource.test-while-idle}")  
    private boolean testWhileIdle;  
      
    @Value("${spring.datasource.test-on-borrow}")  
    private boolean testOnBorrow;  
      
    @Value("${spring.datasource.filters}")  
    private String filters;  
    
    //DataSource配置
    @Bean
    public DataSource dataSource() {
    	 DruidDataSource datasource = new DruidDataSource();  
         
         datasource.setUrl(this.dbUrl);  
         datasource.setUsername(username);  
         datasource.setPassword(password);  
         datasource.setDriverClassName(driverClassName);  
           
         //configuration  
         datasource.setInitialSize(initialSize);  
         datasource.setMinIdle(minIdle);  
         datasource.setMaxActive(maxActive);  
         datasource.setMaxWait(maxWait);  
         datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);  
         datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);  
         datasource.setValidationQuery(validationQuery);  
         datasource.setTestWhileIdle(testWhileIdle);  
         datasource.setTestOnBorrow(testOnBorrow);
         try {  
             datasource.setFilters(filters);  
         } catch (SQLException e) {  
         }
         return datasource;  
    }
    
     //提供SqlSeesion
    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        DataSource ds = dataSource() ;
        ds.getConnection();
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(ds);
        
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
 
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mapper/*.xml"));
        
        /*PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        Properties p = new Properties();
         p.setProperty("offsetAsPageNum", "true");
         p.setProperty("rowBoundsWithCount", "true");
         p.setProperty("reasonable", "true");
         paginationInterceptor.setProperties(p);
        SQLInterceptor sqlInterceptor = new SQLInterceptor();
        
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{ paginationInterceptor});*/

        return sqlSessionFactoryBean.getObject();
        
    }
    
    @Bean
    public RestTemplate restTemplate() {
       return new RestTemplate();
    }
    
    @Bean
    public LocalValidatorFactoryBean initValidator() {
        return new LocalValidatorFactoryBean();
    }
    
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
