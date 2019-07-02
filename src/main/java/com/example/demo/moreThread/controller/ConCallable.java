package com.example.demo.moreThread.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * 线程任务处理类
 * Created by IDEA
 * author:wangzhou
 * Data:2018/6/27
 * Time:15:45
 **/
public class ConCallable implements Callable {
    private List<String> list;
    @Override
    public Object call() throws Exception {
        List<String> listRe = new ArrayList<>();
        for(int i = 0;i < list.size();i++){
            //含有‘4599’的字符串都返回
            if(list.get(i).contains("4599")){
                listRe.add(list.get(i));
            }
        }
        return listRe;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
