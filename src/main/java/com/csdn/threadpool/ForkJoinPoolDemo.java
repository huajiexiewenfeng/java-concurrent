package com.csdn.threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 斐波那契数列
 *
 * @author ：xwf
 * @date ：Created in 2020-6-24 13:46
 */
public class ForkJoinPoolDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        for (int i = 0; i < 10; i++) {
            ForkJoinTask task = forkJoinPool.submit(new Fibonacci(i));
            System.out.println(task.get());
        }
    }

    static class Fibonacci extends RecursiveTask<Integer> {

        int n;

        public Fibonacci(int n) {
            this.n = n;
        }

        @Override
        protected Integer compute() {
            if (n <= 1) {
                return n;
            }
            Fibonacci fib1 = new Fibonacci(n - 1);
            fib1.fork();
            Fibonacci fib2 = new Fibonacci(n - 2);
            fib2.fork();
            return fib1.join() + fib2.join();
        }
    }

}
