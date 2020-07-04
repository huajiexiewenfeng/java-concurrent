package com.csdn.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link HashMap} 链表转树示例
 *
 * @author ：xwf
 * @date ：Created in 2020-7-4 9:29
 */
public class HashMapTreeifyDemo {
    public static void main(String[] args) {
        Map<Key, Integer> map = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            map.put(new Key(), i);
        }

        // 断点打折这里
        System.out.println(map);
    }


    static class Key {
        @Override
        public int hashCode() {
            return 1;
        }
    }

}
