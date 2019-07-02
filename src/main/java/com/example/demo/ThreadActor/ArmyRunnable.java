package com.example.demo.ThreadActor;

/**
 * 军队线程，模拟作战双方的行为
 * Created by IDEA
 * author:wangzhou
 * Data:2018/9/20
 * Time:16:43
 **/
public class ArmyRunnable implements Runnable {
    //volatile,java关键字，保证了线程可以正确读取其他线程的写入的值
    //由其它线程写入keeprunning的值，可以在外部线程对这个线程进行控制
    //不用volatile，由于可见性问题线程将不能正确读取传入的keeprunning
    //可见性ref JMM,happens-before原则
    volatile boolean keepRunning=true;
    @Override
    public void run() {

        while (keepRunning) {
          for(int i=0;i<5;i++){
              System.out.println(Thread.currentThread().getName()+"第["+i+"]次，发起了攻击");
              //让出处理器时间，下次该谁进攻还不一定，给别的线程机会运行
              Thread.yield();
          }
        }
        System.out.println(Thread.currentThread().getName()+"结束了战斗");
    }
}
