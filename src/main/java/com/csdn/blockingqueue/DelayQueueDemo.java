package com.csdn.blockingqueue;

import javax.swing.*;
import java.util.concurrent.*;

/**
 * {@link DelayQueue}
 *
 * @author ：xwf
 * @date ：Created in 2020\7\5 0005 16:08
 */
public class DelayQueueDemo {

    private static final long NANO_ORIGIN = System.nanoTime();

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<DelayedNode> delayQueue = new DelayQueue<>();
        delayQueue.offer(new DelayedNode(2, 4));
        delayQueue.offer(new DelayedNode(1, 2));
        for (int i = 0; i < 10; i++) {
            System.out.printf("时间[%d]\n", i + 1);
            Thread.sleep(1000);
            System.out.println(delayQueue.poll());
        }
    }

    static class DelayedNode implements Delayed {

        private Integer value;

        private long time;// 秒

        DelayedNode(Integer value, long time) {
            this.value = value;
            this.time = time;
        }

        public Integer getValue() {
            return value;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return time - unit.toSeconds(delayTime());
        }

        private static long delayTime() {
            return System.nanoTime() - NANO_ORIGIN;
        }

        @Override
        public int compareTo(Delayed o) {
            if (o instanceof DelayedNode) {
                return value - ((DelayedNode) o).getValue();
            } else {
                return 0;
            }
        }

        @Override
        public String toString() {
            return "DelayedNode{" +
                    "value=" + value +
                    '}';
        }
    }
}
