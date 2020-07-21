package com.csdn.future;

import java.util.concurrent.*;

/**
 * Future 取消任务执行
 *
 * @author ：xwf
 * @date ：Created in 2020\7\12 0012 21:57
 */
public class FutureCancelDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(new FutureTask());
        try {
            future.cancel(false);
            String res = future.get(2000, TimeUnit.MILLISECONDS);
            System.out.println("Future 线程返回值：" + res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
    }

    static class FutureTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            Thread.sleep(3000);
            return "正常返回";
        }
    }
}
