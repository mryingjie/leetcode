package com.github.myyingjie.leetcode.interview;

import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * created by Yingjie Zheng at 2021/8/9 下午8:17
 *
 */
public class Demo4 {


    private static Queue<Object> message = new LinkedBlockingQueue<Object>();

    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,1,2000, TimeUnit.HOURS,new LinkedBlockingQueue<>(1000));
    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(new Producer(i)).start();
        }

        for (int i = 0; i < 3; i++) {
            new Thread(new Consumer()).start();
        }
    }


    private static class Producer implements Runnable {

        private int n;

        public Producer(int n) {
            this.n = n;
        }

        public void run() {
            for (int i = 0; i < n; i++) {
                message.add("message" + i);
            }
        }
    }

    private static class Consumer implements Runnable {

        public void run() {
            while (true) {
                Object poll = message.poll();
                while (Objects.isNull(poll)) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    poll = message.poll();
                }
                System.out.println("consumer :" + poll);
            }
        }
    }

}
