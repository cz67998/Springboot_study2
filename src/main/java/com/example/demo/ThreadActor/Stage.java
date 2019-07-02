package com.example.demo.ThreadActor;

/**
 * 舞台
 * Created by IDEA
 * author:wangzhou
 * Data:2018/9/20
 * Time:17:16
 **/
public class Stage extends Thread {
    public void run() {
        System.out.println("历史大剧，隋唐演义即将上演！");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("大幕徐徐拉开");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("话说隋朝末年，起义军起兵反隋朝");
        ArmyRunnable armyTaskOfSuiDynasty = new ArmyRunnable();
        ArmyRunnable armyTaskOfRevolt = new ArmyRunnable();
        //Runnable接口创建线程
        Thread armyOfSuiDynasty = new Thread(armyTaskOfSuiDynasty, "隋军");
        Thread armyOfSuiRevolt = new Thread(armyTaskOfRevolt, "起义军");
        //启动线程
        armyOfSuiDynasty.start();
        armyOfSuiRevolt.start();
        //舞台线程休眠，大家专心看军队线程
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("正当双方激战正酣，半路杀出个程咬金");
        Thread mrCheng=new KeyPersion();
        mrCheng.setName("程咬金");
        System.out.println("程咬金的理想就是结束战争，使百姓安居乐业！");

        //改变keepRunning，停止军队线程
        armyTaskOfSuiDynasty.keepRunning = false;
        armyTaskOfRevolt.keepRunning = false;
        //stop突然强行关闭，这种方式已废弃，不要使用、、不能见到线程完全结束的通知，而进行一些清理工作
        //比方说操作数据库时，不能及时关闭与数据库的连接
//        armyOfSuiDynasty.stop();
////        armyOfSuiRevolt.stop();
        try {
            //使其它线程等待当前线程终止
            //armyOfSuiRevolt.join();
            Thread.sleep(2000);//交战双方把资源让出来，让英雄登场
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mrCheng.start();

        try {
            //使其他线程等待join线程的结束，而不会让 System.out.println("战争结束，人民安居乐业，程将军实现了积极的人生梦想，为人民作出了贡献");
            //在mrCheng结束前就执行了
            mrCheng.join();//万众瞩目，所有线程等待程将军完成历史使命
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("战争结束，人民安居乐业，程将军实现了积极的人生梦想，为人民作出了贡献");
        System.out.println("谢谢观看，再见！");
        System.out.println("stage线程结束");

    }

    public static void main(String[] args) {
        new Stage().start();
    }
}
