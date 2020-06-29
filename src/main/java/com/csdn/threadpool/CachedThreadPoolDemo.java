package com.csdn.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ：xwf
 * @date ：Created in 2020-6-24 11:07
 */
public class CachedThreadPoolDemo {

   static ExecutorService executorService = Executors.newCachedThreadPool();//伸缩性，60s后回收

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            executorService.execute(()->{
                System.out.println(Thread.currentThread().getName()+"执行");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
