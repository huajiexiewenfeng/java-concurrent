package com.csdn.future;

import java.io.IOException;

/**
 * {@link Runnable} 接口
 *
 * @author ：xwf
 * @date ：Created in 2020\7\19 0019 22:28
 */
public class RunThrowExceptionDemo {

    /**
     * 普通方法可以在方法签名中抛出异常
     *
     * @throws IOException
     */
    public void normalMethod() throws IOException {
        throw new IOException();
    }

    class RunnableImpl implements Runnable {

        /**
         * run 方法内无法抛出 checked Exception，除非使用 try catch 进行处理
         */
        @Override
        public void run() {
            try {
                throw new IOException();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
