package com.example.demo.ThreadActor;

/**
 * 关键人物
 * Created by IDEA
 * author:wangzhou
 * Data:2018/9/20
 * Time:18:45
 **/
public class KeyPersion extends  Thread{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"开始了战斗！");
        for(int i=0;i<10;i++){
            System.out.println(Thread.currentThread().getName()+"左突右击，攻击敌军。。。");
        }
        System.out.println(Thread.currentThread().getName()+"停止了战斗！");
    }
}
