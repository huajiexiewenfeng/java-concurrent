package com.csdn.collection;

/**
 * i++ 或者 ++i 线程不安全示例
 *
 * @author ：xwf
 * @date ：Created in 2020\6\30 0030 21:01
 */
public class NotSafeThreadIncrementDemo {

    static int i = 0;
    static int j = 0;

    public static void main(String[] args) {
        for (int k = 0; k < 100; k++) {
            new Thread(() -> {
                i++;
                ++j;
            }).start();
        }
        System.out.printf("变量 i 的值：[%d]\n变量 j 的值：[%d]\n", i, j);
    }
}
