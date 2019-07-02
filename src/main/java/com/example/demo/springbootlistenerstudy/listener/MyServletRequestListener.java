package com.example.demo.springbootlistenerstudy.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/8/27
 * Time:18:54
 **/
//@WebListener注解为声明此类为Listener，
// 无需再进行配置，唯一注意的是，使用注解的方式声明Listener时，
// 需要再main函数类上添加@ServletComponentScan（basePackages = "此处写明类地址，格式为包名+类名"）
//basePackages里写的是监听的范围
@WebListener
public class MyServletRequestListener implements ServletRequestListener, ServletRequestAttributeListener {
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        System.out.println("---------------------------->请求销毁");
        //servletRequestEvent.getServletContext();
        //servletRequestEvent.getServletRequest();
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        System.out.println("---------------------------->请求创建");
    }

    /**
     * 监听request对象中属性的改变
     *
     * @param servletRequestAttributeEvent
     */
    @Override
    public void attributeAdded(ServletRequestAttributeEvent servletRequestAttributeEvent) {
        //添加属性时执行
        HttpServletRequest request=(HttpServletRequest)servletRequestAttributeEvent.getServletRequest();
        System.out.println("增加request--->"+request.getAttribute("id"));
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent servletRequestAttributeEvent) {
        //删除属性时执行
        HttpServletRequest request = (HttpServletRequest) servletRequestAttributeEvent.getServletRequest();
        System.out.println("删除request--->"+request.getAttribute("id"));
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent servletRequestAttributeEvent) {
       //修改属性时执行
        HttpServletRequest request = (HttpServletRequest) servletRequestAttributeEvent.getServletRequest();
        System.out.println("替换request--->"+request.getAttribute("id"));
    }
}
