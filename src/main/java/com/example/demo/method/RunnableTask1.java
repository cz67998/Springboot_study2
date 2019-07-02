package com.example.demo.method;

import com.example.demo.Service.Building;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/6/27
 * Time:10:04
 **/
public class RunnableTask1  implements Runnable{
    @Override
    public void run() {
        Building building = new Building();
        synchronized (building) {
            try {
                for (int i = 1; i <= 100; i++) {
                    System.out.println(Thread.currentThread().getName() + "----------异步：>" + i);
                    building.wait(200);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }}
