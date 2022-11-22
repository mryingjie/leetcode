package com.github.myyingjie.leetcode.thread;

import lombok.AllArgsConstructor;

import java.util.concurrent.ConcurrentHashMap;

/**
 * created by Yingjie Zheng at 2019-09-04 16:29
 *
 * 使用volatile设置全局标志位
 * 不使用volatile会因为内存可见性问题 导致每个线程里本地缓存的标志位都不变且不刷新然后死循环
 * 这里的标志位就相当于一把互斥锁，因此可以保证同一时刻只有一个线程在执行 打印并变更标志位
 * 这里注意必须先打印再变更标志位否则就会出现问题
 */
public class FooBarVolatile {

    private int n;



    private volatile int state = 1;

    public FooBarVolatile(int n) {
        this.n = n;
    }

    public static void main(String[] args) {
        FooBarVolatile fooBar = new FooBarVolatile(30);

        new Thread(new Foo1(fooBar)).start();
        new Thread(new Bar1(fooBar)).start();

    }

    public void foo() throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while (state == 2) {
            }
            Thread.sleep(100);

            System.out.print("foo");

            Thread.sleep(100);

            state = 2;

            Thread.sleep(100);


        }

    }

    public void bar() throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while (state == 1) {
            }
            Thread.sleep(100);

            System.out.print("bar");

            Thread.sleep(100);

            state = 1;

            Thread.sleep(100);


        }

    }

}


@AllArgsConstructor
class Foo1 implements Runnable {

    private FooBarVolatile fooBar;


    @Override
    public void run() {
        try {
            fooBar.foo();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

@AllArgsConstructor
class Bar1 implements Runnable {

    private FooBarVolatile fooBar;


    @Override
    public void run() {
        try {
            fooBar.bar();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}