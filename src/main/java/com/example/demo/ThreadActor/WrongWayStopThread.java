package com.example.demo.ThreadActor;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/9/21
 * Time:9:46
 **/
public class WrongWayStopThread  extends  Thread{
    public static void main(String[] args) throws InterruptedException {
   WrongWayStopThread wrongWayStopThread=new WrongWayStopThread();
        System.out.println("开始线程。。。");
        wrongWayStopThread.start();
        Thread.sleep(3000);
        System.out.println("Interrupting thread...");
        wrongWayStopThread.interrupt();//interrupt方法并不会使线程停下来
        Thread.sleep(3000);
        System.out.println("线程停止");
    }

    @Override
    public void run() {
        //while (true)就停止不了
      while(!this.isInterrupted()){//判断线程是否处于interrupt状态
          System.out.println("线程开启。。。");
          long time=System.currentTimeMillis();
          while ((System.currentTimeMillis()-time<1000)){
              //减少屏幕输出的空循环
          }
          //如果线程调用了sleep方法陷入阻塞，会抛出InterruptedException，中断状态被清除而不是被设置。
          // 而this.isInterrupted()=false
//          try {
//              Thread.sleep(1000);
//          } catch (InterruptedException e) {
//              e.printStackTrace();
//          }
      }
    }
}
