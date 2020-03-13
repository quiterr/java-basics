package com.quiterr;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author huangchen
 * @date 2020/3/13
 */
public class ScheduledThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        // 创建大小为5的线程池
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);

        Task worker1 = new Task("task-1");
        scheduledThreadPool.scheduleAtFixedRate(worker1, 0,5, TimeUnit.SECONDS);

        Task worker2 = new Task("task-2");
        scheduledThreadPool.scheduleAtFixedRate(worker2, 0,10, TimeUnit.SECONDS);

        Thread.sleep(1000000);

        System.out.println("Shutting down executor...");
        // 关闭线程池
        scheduledThreadPool.shutdown();
        boolean isDone;
        // 等待线程池终止
        do {
            isDone = scheduledThreadPool.awaitTermination(1, TimeUnit.DAYS);
            System.out.println("awaitTermination...");
        } while(!isDone);

        System.out.println("Finished all threads");
    }

    static class Task implements Runnable {

        private String name;

        public Task(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("name = " + name + ", startTime = " + new Date());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//        System.out.println("name = " + name + ", endTime = " + new Date());
        }

    }

}



