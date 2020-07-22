package com.csdn.threadbase;

import java.util.concurrent.*;

/**
 * 线程实现方式示例
 *
 * @author ：xwf
 * @date ：Created in 2020-7-6 10:13
 */
public class ThreadImplementDemo {

    public static void main(String[] args) {
        // 第一种 实现 {@link Runnable} 接口
        new Thread(new RunnableThread()).start();
        // 第二种 继承 {@link Thread}
        new ExtendsThread().start();
        // 第三种 线程池创建
        threadPoolDemo();
        // 第四种 实现 {@link Callable} 接口
        callableDemo();
        // 其他
        othersDemo();
    }

    private static void othersDemo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("匿名内部类");
            }
        }).start();

        new Thread(() -> {
            System.out.println("lambda 语法");
        }).start();
    }

    private static void callableDemo() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<String> future = executorService.submit(new CallableTask());
        try {
            System.out.println(future.get(2, TimeUnit.SECONDS));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void threadPoolDemo() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(() -> {
            System.out.println(Thread.currentThread().getName() + "->第三种：线程池创建");
        });
    }

    /**
     * 实现 {@link Runnable} 接口
     */
    static class RunnableThread implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "->第一种：实现 Runnable 接口实现线程");
        }

    }

    /**
     * 继承 {@link Thread}
     */
    static class ExtendsThread extends Thread {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "->第二种：继承 Thread 类型实现线程");
        }

    }

    static class CallableTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            return Thread.currentThread().getName() + "->第四种：实现 Callable 接口";
        }
    }

}
