package com.github.myyingjie.leetcode.thread;

import java.util.concurrent.*;

/**
 * created by Yingjie Zheng at 2020-03-14 12:43
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {

        try {
            System.out.println(trant("asbs"));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    public static String trant(String content) throws ExecutionException, InterruptedException {
        ExecutorService ex = Executors.newFixedThreadPool(3);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        BlockingDeque<String> queue = new LinkedBlockingDeque<>();
        ex.submit(new Baidu(countDownLatch, queue,content));
        ex.submit(new Baidu(countDownLatch, queue,content));
        ex.submit(new Baidu(countDownLatch, queue,content));
        countDownLatch.await();
        ex.shutdownNow();
        return queue.getFirst();
    }

    private static String baidu(String content) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "baidu"+content;
    }


    private static String google(String content) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "google"+content;
    }

    private static String youdao(String content) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "youdao"+content;
    }

    static class Baidu implements Runnable{

        private CountDownLatch countDownLatch;

        private BlockingQueue<String> queue;

        private String content;

        public Baidu(CountDownLatch countDownLatch, BlockingQueue queue, String content) {
            this.countDownLatch = countDownLatch;
            this.queue = queue;
            this.content = content;
        }

        @Override
        public void run() {
            String baidu = baidu(content);
            queue.add(baidu);
            countDownLatch.countDown();
        }
    }
   static class Youdao implements Runnable{

        private CountDownLatch countDownLatch;

        private BlockingQueue<String> queue;

        private String content;

        public Youdao(CountDownLatch countDownLatch, BlockingQueue queue, String content) {
            this.countDownLatch = countDownLatch;
            this.queue = queue;
            this.content = content;
        }

        @Override
        public void run() {
            String baidu = youdao(content);
            queue.add(baidu);
            countDownLatch.countDown();
        }
    }
    static  class Google implements Runnable{

        private CountDownLatch countDownLatch;

        private BlockingQueue<String> queue;

        private String content;

        public Google(CountDownLatch countDownLatch, BlockingQueue queue, String content) {
            this.countDownLatch = countDownLatch;
            this.queue = queue;
            this.content = content;
        }

        @Override
        public void run() {
            String baidu = google(content);
            queue.add(baidu);
            countDownLatch.countDown();
        }
    }

}
