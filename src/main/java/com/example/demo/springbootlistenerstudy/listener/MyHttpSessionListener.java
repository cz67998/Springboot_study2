package com.example.demo.springbootlistenerstudy.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/8/28
 * Time:11:16
 **/
@WebListener
@Slf4j
public class MyHttpSessionListener implements HttpSessionListener, HttpSessionAttributeListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        //服务器一创建httpSession，就向list集合中添加HttpSession
        HttpSession httpSession = httpSessionEvent.getSession();
        ServletContext application = httpSession.getServletContext();
        // List<HttpSession> list=(List<HttpSession>)application.getAttribute("list");
        System.out.println("session创建添加了" + httpSession.getId());
        //list.add(httpSession);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession httpSession = httpSessionEvent.getSession();
        System.out.println("session销毁了" + httpSession.getId());
    }

    //当给session添加属性时
    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        HttpSession session = httpSessionBindingEvent.getSession();
        session.setAttribute("name", "wangzhou");
        log.info("--attributeAdded--");
        log.info("key----:" + httpSessionBindingEvent.getName());
        log.info("value---:" + httpSessionBindingEvent.getValue());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        log.info("--attributeRemoved--");
    }

    /**
     *  删除属性时执行
     * @param httpSessionBindingEvent
     */
    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
        log.info("--attributeReplaced--");
        log.info("key----:" + httpSessionBindingEvent.getName());
        log.info("value---:" + httpSessionBindingEvent.getValue());
    }
}
