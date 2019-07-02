package com.example.demo.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/8/30
 * Time:16:49
 **/
@WebFilter(filterName = "filter",urlPatterns = "/context/*")
public class UrlFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("----------------------->过滤器被创建");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       //HttpServletRequest子类，ServletRequest父类
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        String requestUrl=request.getRequestURI();
        System.out.println("--------------------->过滤器：请求地址"+requestUrl);
       if(!requestUrl.contains("info")){
           servletRequest.getRequestDispatcher("/failed").forward(servletRequest,servletResponse);
       }else{
           filterChain.doFilter(servletRequest,servletResponse);
       }
    }

    @Override
    public void destroy() {
        System.out.println("----------------------->过滤器被销毁");
    }
}
