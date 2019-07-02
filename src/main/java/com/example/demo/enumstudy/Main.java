package com.example.demo.enumstudy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/9/18
 * Time:16:12
 **/
public class Main {
    public static final  String a="1";
    /*
     *你好
     */

    private String age;

    public static void main(String[] args){
        System.out.println("username");
        String name="wz";
        int age=24;
        String X= String.format("name:%s,age:%d", name, age);
        String y= new StringBuilder().append("name:").append(name).append("age:").append(age).toString();
        System.out.println(X+" "+y);
    }

    private static void f1(List<String> a) {
        List<String> list=new ArrayList<>();
        User user=new User("ww");
        f2(list);
        f(list);
       String name="wz";
       int age=24;
       String X= String.format("name:%s,age:%d", name, age);
       String y= new StringBuilder().append("name:").append(name).append("age:").append(age).toString();
       System.out.println(X+" "+y);
    }

    private static void f2(List<String> list) {
    }


    private static void f(List<String> a) {
    }


    public User getUser(){
        User user=new User("name");
        boolean b;

        return user;
    }
}
