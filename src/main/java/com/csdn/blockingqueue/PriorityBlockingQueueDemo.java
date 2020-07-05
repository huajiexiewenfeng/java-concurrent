package com.csdn.blockingqueue;

import java.util.Comparator;
import java.util.concurrent.*;

/**
 * {@link PriorityBlockingQueue} 示例
 *
 * @author ：xwf
 * @date ：Created in 2020\7\5 0005 15:02
 */
public class PriorityBlockingQueueDemo {
    public static void main(String[] args) {
        System.out.println("=====ArrayBlockingQueue=====");
        BlockingQueue<Node> arrayBlockingQueue = new ArrayBlockingQueue<>(10);
        arrayBlockingQueue.offer(new Node(2));
        arrayBlockingQueue.offer(new Node(3));
        arrayBlockingQueue.offer(new Node(1));
        for (int i = 0; i < 3; i++) {
            System.out.println(arrayBlockingQueue.poll().toString());
        }
        System.out.println("=====PriorityBlockingQueue=====");
        BlockingQueue<Node> priorityBlockingQueue = new PriorityBlockingQueue<>(10, new Node());
        priorityBlockingQueue.offer(new Node(2));
        priorityBlockingQueue.offer(new Node(3));
        priorityBlockingQueue.offer(new Node(1));
        for (int i = 0; i < 3; i++) {
            Node node = priorityBlockingQueue.poll();
            System.out.println(node.toString());
        }
    }

    static class Node implements Comparator<Node> {

        private Integer value;

        public Node() {
        }

        public Node(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        @Override
        public int compare(Node o1, Node o2) {
            return o1.getValue() - o2.getValue();
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }

    }

}
