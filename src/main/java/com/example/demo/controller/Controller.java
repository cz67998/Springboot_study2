package com.example.demo.controller;

import com.example.demo.Service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/7/31
 * Time:10:35
 **/
@Slf4j
@org.springframework.stereotype.Controller
@RequestMapping("/th")
@EnableAsync
public class Controller {
    @Autowired
    private LoginService loginService;
    @GetMapping("/test1")
    public String test1(ModelMap map) {
        String message=loginService.getTest1();
        map.addAttribute("message",message);
        log.info("============>" + Thread.currentThread().getName());
        return "html/welcome";
    }

}
