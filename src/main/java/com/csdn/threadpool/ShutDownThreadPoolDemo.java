package com.csdn.threadpool;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 关闭线程池示例
 *
 * @author ：xwf
 * @date ：Created in 2020-6-28 11:22
 */
public class ShutDownThreadPoolDemo {

    private ExecutorService service = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        new ShutDownThreadPoolDemo().executeTask();
    }

    public void executeTask() {
        for (int i = 0; i < 100; i++) {
            service.submit(() -> {
                System.out.println(Thread.currentThread().getName() + "->执行");
            });
        }
    }

}
