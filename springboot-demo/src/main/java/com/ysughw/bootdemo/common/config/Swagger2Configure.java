package com.ysughw.bootdemo.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Component
@EnableSwagger2
public class Swagger2Configure {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(createCustomRestApi())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ysughw.bootdemo"))
                .paths(PathSelectors.any())
                .build();
    }
    
    private ApiInfo createCustomRestApi(){
        /*return new ApiInfoBuilder()
        .title("Spring Boot中使用Swagger2构建RESTful APIs")
        .description("更多Spring Boot相关文章请关注：http://blog.didispace.com/")
        .termsOfServiceUrl("http://blog.didispace.com/")
        .contact("程序猿DD")
        .version("1.0")
        .build();*/
        @SuppressWarnings("deprecation")
        ApiInfo apiInfo = new ApiInfo("测试管理系统",//大标题
                "测试系统接口文档",//小标题
                "1.0",//版本
                "NO terms of service",
                "郭浩伟",//作者
                "人民教育出版社",//链接显示文字
                "http://www.pep.com.cn/"//网站链接
        );
        return apiInfo;
    }
}