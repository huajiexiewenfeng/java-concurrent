package com.csdn.threadpool;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author ：xwf
 * @date ：Created in 2020-6-24 11:16
 */
public class ScheduledThreadPoolDemo {

    static ScheduledExecutorService service = Executors.newScheduledThreadPool(10);

    public static void main(String[] args) throws InterruptedException {
        service.schedule(() -> {
            System.out.println("定时线程执行1：" + LocalDateTime.now());
        }, 1, TimeUnit.SECONDS);


        service.scheduleAtFixedRate(() -> {
            System.out.println("定时线程执行2：" + LocalDateTime.now());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 1, 1, TimeUnit.SECONDS);

        service.scheduleWithFixedDelay(() -> {
            System.out.println("定时线程执行3：" + LocalDateTime.now());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 1, 1, TimeUnit.SECONDS);
    }
}
