package com.csdn.threadsafe;

/**
 * i++ 线程安全问题示例
 *
 * @author ：xwf
 * @date ：Created in 2020-7-28 11:49
 */
public class AddWrongDemo {

    static int i = 0;

    public static void main(String[] args) throws InterruptedException {

        for (int j = 0; j < 1000; j++) {
            new Thread(() -> {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }).start();
        }

        Thread.sleep(3000);

        System.out.println("循环 1000 后，i 的值 = " + i);
    }
}
