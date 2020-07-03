package com.csdn.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Stream;

/**
 * 阻塞队列-有界{@link ArrayBlockingQueue}示例
 *
 * @author ：xwf
 * @date ：Created in 2020-7-3 9:21
 */
public class BlockingQueueDemo {

    static ArrayBlockingQueue<String> abq = new ArrayBlockingQueue(3);

    public static void main(String[] args) {
        // 生产者
        for (int i = 0; i < 3; i++) {
            new Thread(() -> producer(), "producerThread" + i).start();
        }
        // 消费者
        for (int i = 0; i < 3; i++) {
            new Thread(() -> consumer(), "consumerThread" + i).start();
        }
    }

    private static void consumer() {
        while (true) {
            try {
                String msg = abq.take();
                System.out.println(Thread.currentThread().getName() + " ->receive msg:" + msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void producer() {
        for (int i = 0; i < 100; i++) {
            try {
                abq.put("[" + i + "]");
                System.out.println(Thread.currentThread().getName() + " ->send msg:" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
