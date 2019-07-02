package com.example.demo.springbootlistenerstudy.listener.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/8/27
 * Time:19:43
 **/
@Controller
@RequestMapping("/context")
public class ContextListerController {
    @GetMapping("/test")
    @ResponseBody
    public long test(HttpServletRequest request, HttpServletResponse response){
        ServletContext servletContext=request.getSession().getServletContext();
        String dept=servletContext.getAttribute("context").toString();
        System.out.println(dept);
        return 200;
    }
    @GetMapping("/demo")
    @ResponseBody
    public  long demo(HttpServletRequest request){
        HttpSession session=request.getSession(true);//创建session的创建
        session.setAttribute("username","TOM");//给session添加属性属性name： username,属性 value：TOM
        session.setAttribute("password","tommmm");//添加属性 name: password; value: tommmm
        session.setMaxInactiveInterval(60*30);//以秒为单位，即在没有活动1分钟后，session将失效
        System.out.println(session.getId()+"   "+request.getSession().getAttribute("username"));
        return 200;
    }
    @GetMapping("/jump")
    public String jump(HttpServletRequest request){
        ServletContext servletContext=request.getSession().getServletContext();
        String dept=servletContext.getAttribute("context").toString();
        System.out.println(dept);
        System.out.println(request.getSession().getAttribute("username"));
        return "html/welcome";
    }
}
