package com.example.demo.method;

import org.springframework.stereotype.Component;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/6/27
 * Time:10:58
 **/
@Component
public class TestBean {
    private String username;
    private String url;
    private String password;

    public void sayHello() {
        System.out.println("TestBean sayHello...");
    }

    public String toString() {
        return "username:" + this.username + ",url:" + this.url + ",password:" + this.password;
    }

    public void start() {
        System.out.println("TestBean 初始化。。。");
    }

    public void cleanUp() {
        System.out.println("TestBean 销毁。。。");
    }
}
