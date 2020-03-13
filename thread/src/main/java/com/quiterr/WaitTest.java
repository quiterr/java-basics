package com.quiterr;

import java.util.Date;

/**
 * 测试下Object.wait()方法
 * 在持有对象锁的情况下，执行该对象的wait方法会放弃锁
 * 其他线程执行同一个对象的notify方法，原线程会变为激活状态
 * 但直到其他线程放弃对象锁时，原线程才能继续执行
 *
 * @author huangchen
 * @date 2020/2/24
 */
public class WaitTest {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            synchronized (WaitTest.class) {
                try {
                    System.out.println(new Date() + " Thread1 is running");
                    WaitTest.class.wait();
                    System.out.println(new Date() + " Thread1 ended");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            synchronized (WaitTest.class) {
                try {
                    System.out.println(new Date() + " Thread2 is running");
                    WaitTest.class.notify();
                    System.out.println(new Date() + " Thread2 call notify");
                    // Don't use sleep method to avoid confusing
                    for (long i = 0; i < 200000; i++) {
                        for (long j = 0; j < 100000; j++) {
                        }
                    }
                    System.out.println(new Date() + " Thread2 release lock");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            for (long i = 0; i < 200000; i++) {
                for (long j = 0; j < 100000; j++) {
                }
            }
            System.out.println(new Date() + " Thread2 ended");
        });

        // Don't use sleep method to avoid confusing
        for (long i = 0; i < 200000; i++) {
            for (long j = 0; j < 100000; j++) {
            }
        }
        thread2.start();
    }
}
