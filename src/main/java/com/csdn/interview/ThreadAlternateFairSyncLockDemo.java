package com.csdn.interview;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 基于公平锁的线程交替示例
 * 缺陷超过2个之后，虽然也是交替执行，但是顺序会发生变化
 *
 * @author ：xwf
 * @date ：Created in 2020-6-30 9:24
 */
public class ThreadAlternateFairSyncLockDemo {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock(true);// 公平
        Task task = new Task(lock, 10);
        new Thread(task, "Thread-A").start();
        new Thread(task, "Thread-B").start();
        new Thread(task, "Thread-C").start();
    }

    static class Task implements Runnable {

        Lock lock;

        int count;

        public Task(Lock lock, int count) {
            this.lock = lock;
            this.count = count;
        }

        @Override
        public void run() {
            for (int i = 0; i < count; i++) {
                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + "-> 执行" + i);
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
