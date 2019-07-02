package com.example.demo.Configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/6/27
 * Time:10:49
 **/
@Configuration
@ComponentScan(basePackages = "com.example.demo.Configuration")
public class TestConfiguration {
    public  TestConfiguration(){
        System.out.println("TestConfiguration容器启动初始化。。。");
    }
    // @Bean注解注册bean,同时可以指定初始化和销毁方法
    // @Bean(name="testBean",initMethod="start",destroyMethod="cleanUp")
    /*@Bean
    @Scope("prototype")
    public TestBean testBean(){
        System.out.println("TestConfiguration注册bean。。。");
        return new TestBean();
    }*/
}
