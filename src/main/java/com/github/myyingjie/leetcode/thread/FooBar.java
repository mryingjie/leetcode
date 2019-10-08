package com.github.myyingjie.leetcode.thread;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * created by Yingjie Zheng at 2019-09-04 15:56
 * 两个不同的线程将会共用一个 FooBar 实例。其中一个线程将会调用 foo() 方法，另一个线程将会调用 bar() 方法。
 * <p>
 * 示例 1:
 * 输入: n = 1
 * 输出: "foobar"
 * 解释: 这里有两个线程被异步启动。其中一个调用 foo() 方法, 另一个调用 bar() 方法，"foobar" 将被输出一次。
 */
@Data
public class FooBar {

    private int n;

    private Lock lock = new ReentrantLock();

    private Condition c = lock.newCondition();



    private int state = 1;

    public FooBar(int n) {
        this.n = n;
    }

    public static void main(String[] args) {
        FooBar fooBar = new FooBar(3);

        new Thread(new Foo(fooBar)).start();
        new Thread(new Bar(fooBar)).start();

    }

    public void foo() {
        lock.lock();
        try {
            for (int i = 0; i < n; i++) {
                if (state != 1) {
                    c.await();
                }
                System.out.print("foo");
                state = 2;
                c.signal();
                c.await();
            }
            c.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void bar() {
        lock.lock();
        try {
            for (int i = 0; i < n; i++) {
                if (state != 2) {
                    c.await();
                }
                System.out.print("bar");
                state = 1;
                c.signal();
                c.await();
            }
            c.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

@AllArgsConstructor
class Foo implements Runnable {

    private FooBar fooBar;


    @Override
    public void run() {
        fooBar.foo();

    }
}

@AllArgsConstructor
class Bar implements Runnable {

    private FooBar fooBar;


    @Override
    public void run() {
        fooBar.bar();
    }
}