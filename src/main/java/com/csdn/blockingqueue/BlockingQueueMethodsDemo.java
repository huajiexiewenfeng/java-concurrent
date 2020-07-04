package com.csdn.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * {@link BlockingQueue} 方法演示
 *
 * @author ：xwf
 * @date ：Created in 2020-7-4 10:52
 */
public class BlockingQueueMethodsDemo {

    public static void main(String[] args) {
        BlockingQueue<String> bQueue = new ArrayBlockingQueue(3);
        //addMethod(bQueue);
        //removeMethod(bQueue);
        //elementMethod(bQueue);
        //offerMethod(bQueue);
        pollMethod(bQueue);
        //peekMethod(bQueue);
        //putMethod(bQueue);
        //takeMethod(bQueue);

    }

    private static void addMethod(BlockingQueue<String> bQueue) {
        bQueue.add("1");
        bQueue.add("2");
        bQueue.add("3");
        bQueue.add("4");
    }

    private static void removeMethod(BlockingQueue<String> bQueue) {
        bQueue.add("1");
        bQueue.add("2");
        bQueue.remove();
        bQueue.remove();
        bQueue.remove();
    }

    private static void elementMethod(BlockingQueue<String> bQueue) {
        bQueue.add("1");
        bQueue.add("2");
        System.out.println("元素：" + bQueue.element());
        bQueue.remove();
        System.out.println("元素：" + bQueue.element());
        bQueue.remove();
        System.out.println("元素：" + bQueue.element());
    }

    private static void offerMethod(BlockingQueue<String> bQueue) {
        System.out.println(bQueue.offer("1"));
        System.out.println(bQueue.offer("2"));
        System.out.println(bQueue.offer("3"));
        System.out.println(bQueue.offer("4"));
        System.out.println("队列长度：" + bQueue.size());
    }

    private static void pollMethod(BlockingQueue<String> bQueue) {
        bQueue.offer("1");
        bQueue.offer("2");
        System.out.println("元素：" + bQueue.poll());
        System.out.println("元素：" + bQueue.poll());
        System.out.println("元素：" + bQueue.poll());
    }

    private static void peekMethod(BlockingQueue<String> bQueue) {
        bQueue.offer("1");
        bQueue.offer("2");
        System.out.println("元素：" + bQueue.peek());
        bQueue.poll();
        System.out.println("元素：" + bQueue.peek());
        bQueue.poll();
        System.out.println("元素：" + bQueue.peek());
    }

    private static void putMethod(BlockingQueue<String> bQueue) {
        try {
            bQueue.put("1");
            bQueue.put("2");
            bQueue.put("3");
            bQueue.put("4");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void takeMethod(BlockingQueue<String> bQueue) {
        try {
            System.out.println("元素：" + bQueue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
