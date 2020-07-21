package com.csdn.future;

import java.util.Random;
import java.util.concurrent.*;

/**
 * isDone 方法演示
 *
 * @author ：xwf
 * @date ：Created in 2020\7\12 0012 21:57
 */
public class FutureIsDoneDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> future = executorService.submit(new FutureTask());
        try {
            for (int i = 0; i < 3; i++) {
                Thread.sleep(1000);
                System.out.println("线程是否完成：" + future.isDone());
            }
            Integer res = future.get(2000, TimeUnit.MILLISECONDS);
            System.out.println("Future 线程返回值：" + res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    static class FutureTask implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            Thread.sleep(2000);
            throw new Exception("故意抛出异常");
        }
    }
}
