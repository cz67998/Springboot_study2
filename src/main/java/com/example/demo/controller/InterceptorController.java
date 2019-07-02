package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/8/27
 * Time:18:44
 **/
@Controller()
@RequestMapping("/ipc")
public class InterceptorController {
    @GetMapping("/jump")
    public String jump(){
        return "html/crossdomain";
    }
    @GetMapping("/test")
    @ResponseBody
    public long test(){
        return 200;
    }
    @PostMapping("/test")
    @ResponseBody
    public long te(){
        return 200;
    }
    @GetMapping("/posthandle")
    public String posthandle(ModelAndView modelAndView){
        long time=System.currentTimeMillis();
        modelAndView.addObject("executeTime",time);
        return "html/crossdomain";
    }
}
