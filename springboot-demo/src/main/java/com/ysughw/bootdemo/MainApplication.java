package com.ysughw.bootdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

//@EnableDiscoveryClient
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.ysughw.bootdemo"})
@MapperScan("com.ysughw.bootdemo.**.dao")
@ServletComponentScan
public class MainApplication{
    
    public static void main(String[] args){
        //SpringApplication app = new SpringApplication(MyApplication.class); 
          //app.addListeners(new MyApplicationEventListener());
        // app.run(args);
        // app.run(args);
        new SpringApplicationBuilder(MainApplication.class).web(true).run(args);
    }
}  
