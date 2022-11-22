package com.github.myyingjie.leetcode.thread;


import java.util.concurrent.*;

/**
 * created by Yingjie Zheng at 2020-03-14 12:33
 */
public class FirstOut {

    public static void main(String[] args) {
        String connent = "sasaa";

        try {
            System.out.println(trant(connent));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {

        }

    }

    public static String trant(String connent) throws ExecutionException, InterruptedException {
        ExecutorService ex = Executors.newFixedThreadPool(3);
        try {
            Future<String> baidu = ex.submit(() -> baidu(connent));
            Future<String> google = ex.submit(() -> google(connent));
            Future<String> youdao = ex.submit(() -> youdao(connent));

            while (true) {
                if (baidu.isDone()) {
                    return baidu.get();
                }
                if (google.isDone()) {
                    return google.get();
                }
                if (youdao.isDone()) {
                    return youdao.get();
                }
            }
        } finally {
            ex.shutdownNow();
        }

    }

    private static String baidu(String connent) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "baidu"+connent;
    }


    private static String google(String connent) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "google"+connent;
    }

    private static String youdao(String connent) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "youdao"+connent;
    }
}
