package com.csdn.threadbase;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author ：xwf
 * @date ：Created in 2020\7\12 0012 18:02
 */
public class WaitNotifyDemo {

    static Queue<String> buffer = new LinkedList<>();

    public static void main(String[] args) throws InterruptedException {

        Object lock = new Object();

        new Thread(new Consumer(lock), "consumer-thread").start();

        for (int i = 0; i < 10; i++) {
            new Thread(new Producer("元素" + i, lock), "producer-thread").start();
        }

    }

    static class Producer implements Runnable {

        private String number;

        private Object lock;

        public Producer(String number, Object lock) {
            this.number = number;
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock) {
                buffer.add(number);
                lock.notify();
                System.out.println(Thread.currentThread().getName() + "-> 添加" + number);
            }
        }
    }

    static class Consumer implements Runnable {

        private Object lock;

        public Consumer(Object lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock) {
                while (buffer.isEmpty()) {
                    try {
                        System.out.println(Thread.currentThread().getName() + "-> 等待...");
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + "-> 消费元素：" + buffer.poll());
            }
        }
    }
}
