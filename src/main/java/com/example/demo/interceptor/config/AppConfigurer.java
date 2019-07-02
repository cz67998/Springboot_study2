package com.example.demo.interceptor.config;


import com.example.demo.interceptor.Interceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/8/27
 * Time:17:19
 **/
@Configuration
public class AppConfigurer extends WebMvcConfigurationSupport {
    @Bean
    public HandlerInterceptor getMyInterceptor() {
        return new Interceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册ecm系统操作日志拦截器(凡加了自定义注解的方法都会添加用户操作日志),拦截所有请求
        registry.addInterceptor(getMyInterceptor()).addPathPatterns("/**");
        // 注册ecm系统请求拦截器(不拦截登录、注销、回调请求，其他都拦截)
        registry.addInterceptor(new Interceptor()).addPathPatterns("/**");
        // 连接器里没有@Autowire或者@Resource注入的属性就这么用
        super.addInterceptors(registry);
    }
}
