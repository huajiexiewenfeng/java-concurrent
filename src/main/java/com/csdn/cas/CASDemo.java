package com.csdn.cas;

/**
 * @author ：xwf
 * @date ：Created in 2020\7\26 0026 15:28
 */
public class CASDemo {

    private volatile int value = 100;// 共享变量 x 的值

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        String threadName = Thread.currentThread().getName();
        int oldValue = value;// 旧值
        if (oldValue == expectedValue) {// 比较：如果旧值等于预期值
            value = newValue;// 交换：设置共享变量 x = 新值
            System.out.printf("线程[%s] CAS 成功,value[%d]->[%d] \n", threadName, oldValue, newValue);
        } else {
            System.out.printf("线程[%s] CAS 失败 \n", threadName);
        }
        return value;
    }

    static class CASTask implements Runnable {

        private CASDemo casDemo;
        private int expectedValue;
        private int newValue;

        public CASTask(CASDemo casDemo, int expectedValue, int newValue) {
            this.casDemo = casDemo;
            this.expectedValue = expectedValue;
            this.newValue = newValue;
        }

        @Override
        public void run() {
            casDemo.compareAndSwap(100, 150);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CASDemo casDemo = new CASDemo();
        Thread t1 = new Thread(new CASTask(casDemo, 100, 150), "Thread-1");
        Thread t2 = new Thread(new CASTask(casDemo, 100, 200), "Thread-2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("CAS 之后 value 的值：" + casDemo.value);
    }
}
