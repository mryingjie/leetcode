package com.github.myyingjie.leetcode.interview;

import lombok.SneakyThrows;

import java.util.concurrent.locks.ReentrantLock;

/**
 * created by Yingjie Zheng at 2021/8/5 上午11:40
 * <p>
 * <p>
 * 3只猴子抢着分1000只桃子，每只抢到的猴子分去剩余桃子的``一半。使用多线程模拟这一过程,和最终每个猴子获得桃子数量
 */
public class 多线程demo {

    private static volatile int total = 1000;


    public static void main(String[] args) {
        doTask(3);

    }

    public static void doTask(int n) {

        ReentrantLock lock = new ReentrantLock();

        for (int i = 0; i < n; i++) {
            new Thread(new Task(lock)).start();
        }
    }


    private static class Task implements Runnable {

        private int n;

        private ReentrantLock lock;

        public Task(ReentrantLock lock) {
            this.lock = lock;
        }

        @SneakyThrows
        public void run() {
            while (true) {
                try {
                    // 模拟 否则大概率总是一个线程执行
                    Thread.sleep(100);
                    lock.lock();
                    if (total > 0) {
                        if (total == 1) {
                            this.n += 1;
                            total = 0;
                        } else {
                            int k = total / 2;
                            this.n += k;
                            total = total - k;
                        }
                    } else {
                        break;
                    }
                } finally {
                    lock.unlock();
                }
            }
            System.out.println(String.format("抢到%d个", this.n));

        }
    }

}
