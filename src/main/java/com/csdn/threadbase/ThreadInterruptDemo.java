package com.csdn.threadbase;

/**
 * 线程中断示例
 *
 * @author ：xwf
 * @date ：Created in 2020\7\12 0012 13:02
 */
public class ThreadInterruptDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new InterruptTask());
        thread.start();
        thread.sleep(1);
        thread.interrupt();
    }

    static class InterruptTask implements Runnable {

        @Override
        public void run() {
            int count = 0;
            while (!Thread.currentThread().isInterrupted()) {
                count++;
            }
            System.out.println("循环次数：" + count + "，线程终端，中断信号：" + Thread.currentThread().isInterrupted());

        }
    }
}
