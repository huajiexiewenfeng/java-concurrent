package com.csdn.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁{@link ReentrantReadWriteLock}示例
 *
 * @author ：xwf
 * @date ：Created in 2020\7\25 0025 17:02
 */
public class ReadWriteLockDemo {

    private static final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    public static void main(String[] args) {
        new Thread(()->read()).start();
        new Thread(()->read()).start();
        new Thread(()->write()).start();
        new Thread(()->write()).start();
    }

    private static void read() {
        String threadName = Thread.currentThread().getName();
        readLock.lock();
        try {
            System.out.println(threadName + "-获取读锁(readLock)，读取数据...");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(threadName + "-释放读锁(readLock)");
            readLock.unlock();
        }
    }

    private static void write() {
        String threadName = Thread.currentThread().getName();
        writeLock.lock();
        try {
            System.out.println(threadName + "-获取写锁(writeLock)，写入数据...");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(threadName + "-释放写锁(writeLock)");
            writeLock.unlock();
        }
    }

}
