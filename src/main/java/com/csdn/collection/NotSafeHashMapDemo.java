package com.csdn.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * 扩容期间取出值不准确
 * {@link HashMap} 线程不安全示例
 *
 * @author ：xwf
 * @date ：Created in 2020\6\30 0030 21:56
 */
public class NotSafeHashMapDemo {

    public static void main(String[] args) throws InterruptedException {
        final Map<Integer, String> map = new HashMap<>();
        final Integer targetKey = 1000;// 如果不出异常，可以增大这个值
        final String targetValue = "xwf";
        map.put(targetKey, targetValue);

        new Thread(() -> {
            IntStream.range(0, targetKey).forEach(key -> map.put(key, "someValue"));
        }).start();

        // 不断的循环取 targetKey 对应的值，预期值为 xwf
        while (true) {
            System.out.println("集合的大小："+map.size());// 查看 map 集合大小
            if (null == map.get(targetKey)) {// 如果取到的值为 null 表示在扩容的过程中，原来 targetKey 的值发生了变化
                throw new RuntimeException("HashMap is not thread safe.");
            } else {
                System.out.println(map.get(targetKey));
            }
        }
    }

}
