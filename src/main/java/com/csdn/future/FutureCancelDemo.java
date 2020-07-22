package com.csdn.future;

import java.util.concurrent.*;

/**
 * Future 取消任务执行
 *
 * @author ：xwf
 * @date ：Created in 2020\7\12 0012 21:57
 */
public class FutureCancelDemo {

    static ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        // 当任务还没有开始执行
//        demo1();
        // 如果任务已经执行完
//        demo2();
        // 如果任务正在进行中
        demo3();
    }

    private static void demo1() {
        for (int i = 0; i < 1000; i++) {
            executorService.submit(new FutureTask());
        }

        Future<String> future = executorService.submit(new FutureTask());
        try {
            boolean cancel = future.cancel(false);
            System.out.println("Future 任务是否被取消：" + cancel);
            String res = future.get(2000, TimeUnit.MILLISECONDS);
            System.out.println("Future 线程返回值：" + res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }


    private static void demo2() {
        Future<String> future = executorService.submit(new FutureTask());
        try {
            Thread.sleep(1000);
            boolean cancel = future.cancel(false);
            System.out.println("Future 任务是否被取消：" + cancel);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

    private static void demo3() {
        Future<String> future = executorService.submit(new FutureInterruptTask());
        try {
            Thread.sleep(1000);
            boolean cancel = future.cancel(true);
            System.out.println("Future 任务是否被取消：" + cancel);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }


    static class FutureTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            return "正常返回";
        }
    }

    static class FutureInterruptTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("循环执行");
                Thread.sleep(500);
            }
            System.out.println("线程被中断");
            return "正常返回";
        }
    }
}
