package com.csdn.threadsafe;

import java.util.HashMap;
import java.util.Map;

/**
 * 发布和初始化导致线程安全问题示例
 *
 * @author ：xwf
 * @date ：Created in 2020\7\28 0028 21:14
 */
public class InitWrongDemo {
    private Map<Integer, String> users;

    public InitWrongDemo() {
        new Thread(() -> {
            users = new HashMap<>();
            users.put(1, "user1");
            users.put(2, "user2");
            users.put(3, "user3");
            users.put(4, "user4");
        }).start();
    }

    public Map<Integer, String> getUsers() {
        return users;
    }

    public static void main(String[] args) throws InterruptedException {
        InitWrongDemo demo = new InitWrongDemo();
        // Thread.sleep(500);
        String name = demo.getUsers().get(1);
        System.out.println(name);
    }
}
