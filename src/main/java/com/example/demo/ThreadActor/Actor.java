package com.example.demo.ThreadActor;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/9/20
 * Time:16:12
 **/
public class Actor extends Thread{
    public void run(){
        System.out.println(getName()+"是一个演员！");
        int count=0;
        boolean keepRunning=true;
        while (keepRunning){
            //true的话不停的运行线程
            System.out.println(getName()+"登台演出："+(++count));
            if(count==100){
                keepRunning=false;
            }
            //当count%10==0时让线程停止，
            if(count%10==0){
                try {
                    Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println(getName()+"的演出结束了！");
    }
    public static void main(String[] args){
     Thread actor=new Actor();
     actor.setName("Mr.Wangzhou");
     actor.start();
       // Thread actress=new Thread(new Actress(),"Mr.zz");
     Thread actress=new Thread(new Actress());
     actress.setName("Mr.zz");
     actress.start();
    }
}
class Actress implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"是一个演员！");
        int count=0;
        boolean keepRunning=true;
        while (keepRunning){
            //true的话不停的运行线程
            System.out.println(Thread.currentThread().getName()+"登台演出："+(++count));
            if(count==100){
                keepRunning=false;
            }
            //当count%10==0时让线程停止，
            if(count%10==0){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println(Thread.currentThread().getName()+"的演出结束了！");
    }
}
