package com.quiterr;

import java.util.concurrent.*;

import static java.lang.Thread.sleep;

/**
 * {@link Future} 代表异步计算的结果，这个接口提供了检查计算是否结束、等待计算、取消计算以及获取计算结果的方法。
 * 如果计算没有结束，get方法会阻塞。{@link FutureTask}是Future和Runnable的实现。
 *
 * <pre>
 * FutureTask<String> futureTask = new FutureTask<>(()-> searcher.search("keyword"));
 * executor.execute(futureTask);
 * </pre>
 *
 * @author huangchen
 * @date 2020/2/5
 */
public class App {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        ArchiveSearcher searcher = target -> {
            try {
                sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "this is search answer for keyword.";
        };

        Future<String> future = executor.submit(()-> searcher.search("keyword"));
        System.out.println("doing other things while searching.");
        try {
            while (!future.isDone()){
                System.out.println("still searching.");
                sleep(1000);
            }
            System.out.println(future.get());
        } catch (ExecutionException | InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
