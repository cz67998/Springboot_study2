package com.example.demo.controller;

import com.example.demo.Service.LoginService;
import com.example.demo.method.RunnableTask1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/6/27
 * Time:9:21
 **/
@Slf4j
@RestController
@EnableAsync
public class ThreadController {
    @Autowired
    LoginService loginService;
    /**
     * 同步处理
     *
     * @return
     */
    @GetMapping(value = "/test2",produces = {"text/html;charset=UTF-8"})
    public String test2() {
        loginService.getTest2();
        log.info(Thread.currentThread().getName() + "==========主线程名");
        return "同步,正在解析......";
    }

    /**
     * 异步方式1：线程池，创新线程
     *
     * @return
     */
    @GetMapping("/test3")
    public String test3() {
        //创建线程池
        ExecutorService service = Executors.newFixedThreadPool(5);
        RunnableTask1 task1 = new RunnableTask1();
        //Building building=new Building();
        service.execute(task1);
        log.info("=========》当前线程名：" + Thread.currentThread().getName());
        return "异步,正在解析......";
    }

    @GetMapping("/test1")
    public String test1() {
       loginService.getTest1();
        log.info("============>" + Thread.currentThread().getName());
        return "异步,正在解析......";
    }
}
