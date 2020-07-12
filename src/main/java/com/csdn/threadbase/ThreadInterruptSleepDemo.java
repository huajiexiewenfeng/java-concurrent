package com.csdn.threadbase;

/**
 * 线程在 sleep 状态下中断示例
 *
 * @author ：xwf
 * @date ：Created in 2020\7\12 0012 13:02
 */
public class ThreadInterruptSleepDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new InterruptTask());
        thread.start();
        thread.sleep(500);
        thread.interrupt();
    }

    static class InterruptTask implements Runnable {

        @Override
        public void run() {
            int count = 0;
            while (!Thread.currentThread().isInterrupted()) {
                count++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("循环次数：" + count + "，线程终端，中断信号：" + Thread.currentThread().isInterrupted());
        }
    }
}
