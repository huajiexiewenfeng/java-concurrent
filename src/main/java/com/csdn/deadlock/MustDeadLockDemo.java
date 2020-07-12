package com.csdn.deadlock;

/**
 * 必然死锁示例
 *
 * @author ：xwf
 * @date ：Created in 2020-7-9 14:46
 */
public class MustDeadLockDemo {

    public static void main(String[] args) {
        Object lock1 = new Object();
        Object lock2 = new Object();
        new Thread(new DeadLockTask(lock1, lock2, true), "线程1").start();
        new Thread(new DeadLockTask(lock1, lock2, false), "线程2").start();

    }

    static class DeadLockTask implements Runnable {

        private boolean flag;
        private Object lock1;
        private Object lock2;

        public DeadLockTask(Object lock1, Object lock2, boolean flag) {
            this.lock1 = lock1;
            this.lock2 = lock2;
            this.flag = flag;
        }

        @Override
        public void run() {
            if (flag) {
                synchronized (lock1) {
                    System.out.println(Thread.currentThread().getName() + "->拿到锁1");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "->等待锁2释放...");
                    synchronized (lock2) {
                        System.out.println(Thread.currentThread().getName() + "->拿到锁2");
                    }
                }
            }
            if (!flag) {
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + "->拿到锁2");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "->等待锁1释放...");
                    synchronized (lock1) {
                        System.out.println(Thread.currentThread().getName() + "->拿到锁1");
                    }
                }
            }
        }
    }
}
