package com.example.demo.springbootlistenerstudy.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/8/30
 * Time:15:11
 **/
@WebListener
public class MyServletContextAttributeListener implements ServletContextAttributeListener {
    /**
     * 添加属性时执行
     * @param servletContextAttributeEvent
     */
    @Override
    public void attributeAdded(ServletContextAttributeEvent servletContextAttributeEvent) {
        ServletContext servletContext=servletContextAttributeEvent.getServletContext();
        String name =servletContextAttributeEvent.getName();
        Object value=servletContextAttributeEvent.getValue();
        System.out.println(servletContext+"范围内添加了名为"+name+"值为"+value+"的属性");
    }

    /**
     * 修改属性时执行
     * @param servletContextAttributeEvent
     */
    @Override
    public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent) {
        ServletContext servletContext=servletContextAttributeEvent.getServletContext();
        String name =servletContextAttributeEvent.getName();
        Object value=servletContextAttributeEvent.getValue();
        System.out.println(servletContext+"范围内添加了名为"+name+"值为"+value+"的属性被删除了");
    }

    /**
     * 删除属性时执行
     * @param servletContextAttributeEvent
     */
    @Override
    public void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent) {
        ServletContext servletContext=servletContextAttributeEvent.getServletContext();
        String name =servletContextAttributeEvent.getName();
        Object value=servletContextAttributeEvent.getValue();
        System.out.println(servletContext+"范围内添加了名为"+name+"值为"+value+"的属性被替换了");
    }
}
