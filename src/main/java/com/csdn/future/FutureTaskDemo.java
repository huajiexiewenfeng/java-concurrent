package com.csdn.future;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * {@link FutureTask} 示例
 *
 * @author ：xwf
 * @date ：Created in 2020\7\21 0021 22:49
 */
public class FutureTaskDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask(new Task());
        new Thread(futureTask).start();
        Integer res = futureTask.get();
        System.out.println("FutureTask 返回结果：" + res);
    }

    static class Task implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            return new Random().nextInt(10);
        }
    }
}
