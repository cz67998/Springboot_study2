package com.example.demo.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/6/27
 * Time:9:23
 **/
@Service
@Slf4j
public class LoginService {
    private static int temp_msg;
    /**
     * 同步方法
     */
    public void getTest2() {
        String currentMessage=null;
        Building building = new Building();
        synchronized (building) {
            try {
                for (int i = 1; i <= 100; i++) {
                    log.info(Thread.currentThread().getName() + "----------同步：>" + i);
                    building.wait(200);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * springboot自带的异步方法
     * @return
     */
    @Async
    public String getTest1() {
        Building building = new Building();
        temp_msg=0;
        synchronized (building) {
            try {
                for (int i = 1; i <= 100; i++) {
                    log.info(Thread.currentThread().getName() + "----------异步：>" + i);
                    building.wait(200);
                    temp_msg=i;
                }
                return "执行异步任务完毕";
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return Thread.currentThread().getName() + "执行完毕"+"【】【】"+temp_msg;
    }
}
