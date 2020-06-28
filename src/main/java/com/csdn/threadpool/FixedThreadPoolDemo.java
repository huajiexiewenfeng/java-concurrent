package com.csdn.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 固定线程的线程池示例
 *
 * @author ：xwf
 * @date ：Created in 2020-6-28 16:26
 */
public class FixedThreadPoolDemo {

    static ExecutorService executorService = Executors.newFixedThreadPool(3);

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "-> 执行");
            });
        }
        // 关闭线程池
        executorService.shutdown();
    }

}
