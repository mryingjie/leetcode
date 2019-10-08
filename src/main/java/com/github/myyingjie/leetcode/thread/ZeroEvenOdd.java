package com.github.myyingjie.leetcode.thread;


import lombok.AllArgsConstructor;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * created by Yingjie Zheng at 2019-09-04 14:04
 *
 * 相同的一个 ZeroEvenOdd 类实例将会传递给三个不同的线程：
 * 线程 A 将调用 zero()，它只输出 0 。
 * 线程 B 将调用 even()，它只输出偶数。
 * 线程 C 将调用 odd()，它只输出奇数。
 * 每个线程都有一个 printNumber 方法来输出一个整数。请修改给出的代码以输出整数序列 010203040506... ，其中序列的长度必须为 2n。
 *
 * 示例 1：
 * 输入：n = 2
 * 输出："0102"
 * 说明：三条线程异步执行，其中一个调用 zero()，另一个线程调用 even()，最后一个线程调用odd()。正确的输出为 "0102"。
 */

@AllArgsConstructor
public class ZeroEvenOdd {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(100, lock);

        new Thread(new Zero(zeroEvenOdd)).start();
        new Thread(new Even(zeroEvenOdd)).start();
        new Thread(new Odd(zeroEvenOdd)).start();
        System.out.println();

    }

    private int state = 0;
    private int current = 1;

    private int n;

    private Lock lock;

    private Condition zeroCondition;

    private Condition evenCondition;

    private Condition oddCondition;


    public ZeroEvenOdd(int n, Lock lock) {
        this.n = n;
        this.lock = lock;
        this.zeroCondition = lock.newCondition();
        this.evenCondition = lock.newCondition();
        this.oddCondition = lock.newCondition();
    }


    public void zero(IntConsumer printNumber) {
        lock.lock();
        try {
            while (current <= n) {
                if (state != 0) {
                    zeroCondition.await();
                }
                printNumber.accept(0);
                if (current % 2 == 0) {
                    state = 2;
                    evenCondition.signal();
                } else {
                    state = 1;
                    oddCondition.signal();
                }
                zeroCondition.await();
            }
            oddCondition.signal();
            evenCondition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void even(IntConsumer printNumber) {
        lock.lock();
        try {
            while (current <= n) {
                if (state != 2) {
                    evenCondition.await();
                } else {
                    printNumber.accept(current++);
                    state = 0;
                    zeroCondition.signal();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void odd(IntConsumer printNumber) {
        lock.lock();
        try {
            while (current <= n) {
                if (state != 1) {
                    oddCondition.await();
                } else {
                    printNumber.accept(current++);
                    state = 0;
                    zeroCondition.signal();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            lock.unlock();
        }
    }

}


@AllArgsConstructor
class Zero implements Runnable {

    ZeroEvenOdd zeroEvenOdd;


    @Override
    public void run() {
        zeroEvenOdd.zero(System.out::print);
    }
}


@AllArgsConstructor
class Even implements Runnable {

    ZeroEvenOdd zeroEvenOdd;


    @Override
    public void run() {
        zeroEvenOdd.even(System.out::print);
    }
}


@AllArgsConstructor
class Odd implements Runnable {

    ZeroEvenOdd zeroEvenOdd;


    @Override
    public void run() {
        zeroEvenOdd.odd(System.out::print);

    }
}





