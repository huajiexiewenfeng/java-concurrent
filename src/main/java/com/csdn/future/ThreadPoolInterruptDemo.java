package com.csdn.future;

import java.util.concurrent.*;

/**
 * 测试线程池中，Runnable 和 Callable 异常情况
 *
 * @author ：xwf
 * @date ：Created in 2020\9\6 0006 16:08
 */
public class ThreadPoolInterruptDemo {
    static ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
//        methodRunnable();
        methodCallable();
    }

    private static void methodCallable() {
        Future<?> submit = executorService.submit(new CallableInterruptTask());
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        submit.cancel(true);
        try {
            Object o = submit.get();
            System.out.println(o);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void methodRunnable() {
        Future<?> submit = executorService.submit(new RunnableInterruptTask());
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        submit.cancel(true);
    }

    static class RunnableInterruptTask implements Runnable {

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("循环执行");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("线程被中断");
        }
    }

    static class CallableInterruptTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("循环执行");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    throw e;
                }
            }
            System.out.println("线程被中断");
            return "线程被中断";
        }
    }
}
