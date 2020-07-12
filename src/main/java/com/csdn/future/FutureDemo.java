package com.csdn.future;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author ：xwf
 * @date ：Created in 2020\7\12 0012 21:57
 */
public class FutureDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> future = executorService.submit(new FutureTask());
        try {
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
            return new Random().nextInt(10);
        }
    }
}
