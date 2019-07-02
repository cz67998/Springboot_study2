package com.example.demo.filter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/8/30
 * Time:17:28
 **/
@Controller
@RequestMapping
public class UrlFilterController {
   @GetMapping("/failed")
   public Map<String,String> requestFailed(){
       Map<String,String> map=new HashMap<>();
       map.put("code","-1");
       map.put("msg","请求错误");
       return map;
   }

   //@GetMapping("/success/info")



}
