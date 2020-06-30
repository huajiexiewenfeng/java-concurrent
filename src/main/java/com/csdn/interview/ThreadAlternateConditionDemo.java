package com.csdn.interview;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 基于 {@link Condition} 线程交替执行示例
 * 缺陷超过2个之后，虽然也是交替执行，但是顺序会发生变化
 *
 * @author ：xwf
 * @date ：Created in 2020-6-30 8:48
 */
public class ThreadAlternateConditionDemo {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();// 默认非公平
        Condition condition = lock.newCondition();
        ConditionThread conditionThread = new ConditionThread(lock, condition, 100);
        new Thread(conditionThread, "Thread-A").start();
        new Thread(conditionThread, "Thread-B").start();
        new Thread(conditionThread, "Thread-C").start();
        new Thread(conditionThread, "Thread-D").start();
    }

    static class ConditionThread implements Runnable {

        Lock lock;

        Condition condition;

        int count;

        public ConditionThread(Lock lock, Condition condition, int count) {
            this.lock = lock;
            this.condition = condition;
            this.count = count;
        }

        @Override
        public void run() {
            for (int i = 0; i < count; i++) {
                try {
                    lock.lock();
                    condition.signal();
                    System.out.println(Thread.currentThread().getName() + "-> 执行");
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

}
