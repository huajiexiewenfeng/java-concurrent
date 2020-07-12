package com.csdn.threadbase;

/**
 * 线程中断示例 使用 volatile 关键字
 *
 * @author ：xwf
 * @date ：Created in 2020\7\12 0012 13:02
 */
public class ThreadInterruptVolatileDemo {

    private static volatile boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new InterruptTask());
        thread.start();
        thread.sleep(1000);
        flag = true;
    }

    static class InterruptTask implements Runnable {

        @Override
        public void run() {
            int count = 0;
            while (!flag) {
                count++;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("循环次数：" + count + "，线程中断");
        }
    }
}
