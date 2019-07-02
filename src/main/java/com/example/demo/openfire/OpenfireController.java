package com.example.demo.openfire;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/9/12
 * Time:15:55
 **/
@Controller
@RequestMapping("/op")
public class OpenfireController {
    @GetMapping("/test")
    @ResponseBody
    public long test(){
        MyXMPPTCPConnection.getInstance().getConnection();
       return 200;
    }
}
