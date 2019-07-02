package com.example.demo.moreThread.controller;

import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 这里模拟10万条数据，然后多线程处理
 * Created by IDEA
 * author:wangzhou
 * Data:2018/9/20
 * Time:12:17
 **/
@RestController
@RequestMapping("con")
@Slf4j
public class ConController {
   private   static int i=0;
    @GetMapping("test1")
    public String test1() {
        try {
            List<String> list = new ArrayList<>();
            List<String> list2 = new ArrayList<>();
            for (int i = 0; i < 100000; i++) {
                list.add("test:" + i);
            }
            int size = 250;
            int count = list.size() / size;
            if (count * size != list.size()) {
                count++;
            }
            System.out.println(count);
            int countNum = 0;
            final CountDownLatch countDownLatch = new CountDownLatch(count);
            //有一个固定大小的线程池
            ExecutorService executorService = Executors.newFixedThreadPool(8);
            //初始化8的线程池，newFixedThreadPool适用于稳定的并发
            ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(executorService);
            while (countNum < list.size()) {
                countNum += size;
                ConCallable conCallable = new ConCallable();
                //截取list的数据，分给不同的线程处理
                //ImmutableList不可变集合，顾名思义就是说集合是不可被修改的。集合的数据项是在创建的时候提供，并且在整个生命周期中都不可改变。
                //
                //　　为什么要用immutable对象？immutable对象有以下的优点：
                //　　　　1.对不可靠的客户代码库来说，它使用安全，可以在未受信任的类库中安全的使用这些对象
                //　　　　2.线程安全的：immutable对象在多线程下安全，没有竞态条件
                //　　　　3.不需要支持可变性, 可以尽量节省空间和时间的开销. 所有的不可变集合实现都比可变集合更加有效的利用内存 (analysis)
                //　　　　4.可以被使用为一个常量，并且期望在未来也是保持不变的
                //该方法返回的是父list的一个视图，从fromIndex（包含），到toIndex（不包含）。fromIndex=toIndex 表示子list为空
                conCallable.setList(ImmutableList.copyOf(list.subList(countNum - size, countNum < list.size() ? countNum : list.size())));

                //ListenableFuture顾名思义就是可以监听的Future，它是对java原生Future的扩展增强。
                // 我们知道Future表示一个异步计算任务，当任务完成时可以得到计算结果。如果我们希望一旦计算完成就拿到结果展示给用户或者做另外的计算，
                // 就必须使用另一个线程不断的查询计算状态。这样做，代码复杂，而且效率低下。使用ListenableFuture Guava帮我们检测Future是否完成了，
                // 如果完成就自动调用回调函数，这样可以减少并发程序的复杂度。
                //首先通过MoreExecutors类的静态方法listeningDecorator方法初始化一个ListeningExecutorService的方法，然后使用此实例的submit方法即可初始化ListenableFuture对象。

                ListenableFuture listenableFuture = listeningExecutorService.submit(conCallable);
                //通过Futures的静态方法addCallback给ListenableFuture添加回调函数
                Futures.addCallback(listenableFuture, new FutureCallback<List<String>>() {
                    @Override
                    public void onSuccess(List<String> list1) {
                        countDownLatch.countDown();
                        i++;
                        System.out.println(list1+"...list1"+i);
                        list2.addAll(list1);
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        countDownLatch.countDown();
                        log.info("处理出错：", throwable);

                    }
                });
            }
            countDownLatch.await(30, TimeUnit.MINUTES);
            log.info("符合条件的返回数据的个数为：" + list2.size());
            log.info("回调函数：" + list2.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "正在处理......";
    }
}
