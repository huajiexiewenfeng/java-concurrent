package com.csdn.jmm;

/**
 * 可见性测试
 * @author ：xwf
 * @date ：Created in 2020\8\12 0012 22:22
 */
public class VisibilityDemo {
    private static boolean ready = false;
    private static int number;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready) {
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReaderThread().start();
        Thread.sleep(1000);
        ready = true;
        number = 43;
    }
}
