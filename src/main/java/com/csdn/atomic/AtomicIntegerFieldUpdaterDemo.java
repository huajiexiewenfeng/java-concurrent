package com.csdn.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * {@link AtomicIntegerFieldUpdater} 示例
 *
 * @author ：xwf
 * @date ：Created in 2020-8-6 12:12
 */
public class AtomicIntegerFieldUpdaterDemo {

    static Score math = new Score();
    static Score computer = new Score();

    public static AtomicIntegerFieldUpdater<Score> scoreUpdater = AtomicIntegerFieldUpdater
            .newUpdater(Score.class, "score");

    public static void main(String[] args) throws InterruptedException {
        for (int j = 0; j < 1000; j++) {
            new Thread(() -> {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                math.score++;
                scoreUpdater.getAndIncrement(computer);
            }).start();
        }

        Thread.sleep(3000);

        System.out.println("循环 1000 后，math 的值 = " + math.score);
        System.out.println("循环 1000 后，computer 的值 = " + computer.score);
    }

    static class Score {
        volatile int score = 0;
    }

}
