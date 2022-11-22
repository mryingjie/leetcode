package com.github.myyingjie.leetcode.thread;


import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * created by Yingjie Zheng at 2020/5/27 13:37
 */
public class HashSetAddThreadTest {

    public static void main(String[] args) throws InterruptedException {
        Set<String> objects = new HashSet<>();
        ConcurrentHashMap.KeySetView<Object, Boolean> set = ConcurrentHashMap.newKeySet();
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i <1000 ; i++) {
            int finalI = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                        objects.add("sjux " + (finalI % 10));
                        // objects.remove("sjux" + (finalI -1));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        Thread.sleep(1000);
        executorService.shutdown();
        System.out.println(objects);
        System.out.println(objects.size());

    }
}
