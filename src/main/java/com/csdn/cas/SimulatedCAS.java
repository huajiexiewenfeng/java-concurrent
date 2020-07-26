package com.csdn.cas;

/**
 * 模拟 CAS 操作，等价代码
 *
 * @author ：xwf
 * @date ：Created in 2020\7\26 0026 15:23
 */
public class SimulatedCAS {

    private int value;// 共享变量 x 的值

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;// 旧值
        if (oldValue == expectedValue) {// 比较：如果旧值等于预期值
            value = newValue;// 交换：设置共享变量 x = 新值
        }
        return value;
    }
}
