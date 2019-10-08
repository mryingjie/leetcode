package com.github.myyingjie.leetcode.datastructure;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

/**
 * created by Yingjie Zheng at 2019-09-10 17:02
 * 一个能够在O(1)时间内取得最大值 并且入队出队绝大部分的时间都是O(1)的队列
 * <p>
 * 使用两个最大值栈来实现一个队列，队列push时，将数据压入A栈中，Pop数据时，如果B栈为空，
 * 将A栈的数据Pop出来，压入B栈中，再Pop B栈的数据；
 * 当队列Pop时，如果B栈的数据不为空，则直接Pop B栈的数据
 */
public class MaxQueue<T extends Comparable> {

    private MaxStack<T> a;

    private MaxStack<T> b;

    private int size;

    {
        a = new MaxStack<>();
        b = new MaxStack<>();
    }

    /**
     * 增加一个元索
     */
    public T add(T t) {
        size++;
        return a.push(t);
    }

    /**
     * 移除并返回队列头部的元素    如果队列为空，则抛出一个NoSuchElementException异常
     */
    public T remove() {
        if (b.isEmpty()) {
            if (a.isEmpty()) {
                throw new NoSuchElementException();
            }
            while (!a.isEmpty()) {
                b.push(a.poll());
            }
        }
        size--;
        return b.poll();
    }

    /**
     * 移除并返问队列头部的元素    如果队列为空，则返回null
     */
    public T poll() {
        if (b.isEmpty()) {
            if (a.isEmpty()) {
                return null;
            }
            while (!a.isEmpty()) {
                b.push(a.poll());
            }
        }
        size--;
        return b.poll();
    }

    /**
     * 返回队列头部的元素   如果队列为空，则抛出一个NoSuchElementException异常
     */
    public T element() {
        if (b.isEmpty()) {
            if (a.isEmpty()) {
                throw new NoSuchElementException();
            }
            while (!a.isEmpty()) {
                b.push(a.poll());
            }
        }
        return b.peek();
    }

    /**
     * 返回队列头部的元素              如果队列为空，则返回null
     */
    public T peek() {
        if (b.isEmpty()) {
            if (a.isEmpty()) {
                return null;
            }
            while (!a.isEmpty()) {
                b.push(a.poll());
            }
        }
        return b.peek();
    }

    /**
     * 返回队列当前最大值 不移除元素
     */
    public T peekMax() {
        T t;
        try {
            t = a.peekMax();
        } catch (EmptyStackException ex) {
            return b.peekMax();
        }

        T t1;
        try {
            t1 = b.peekMax();
        } catch (EmptyStackException ex) {
            return a.peekMax();
        }
        return t.compareTo(t1) > 0 ? t : t1;
    }


    public int size(){
        return size;
    }


    public static void main(String[] args) {

        MaxQueue<Integer> maxQueue = new MaxQueue<>();
        maxQueue.add(2);
        maxQueue.add(1);
        System.out.println(maxQueue.peekMax());//2
        maxQueue.add(9);
        maxQueue.add(3);
        System.out.println(maxQueue.peekMax());//9

        maxQueue.add(2);
        maxQueue.add(3);
        System.out.println(maxQueue.peekMax());//9

        System.out.println(maxQueue.peekMax());//9


        Integer poll = maxQueue.poll();
        System.out.println(poll);//2
        System.out.println(maxQueue.peekMax());//9


        Integer poll1 = maxQueue.poll();
        System.out.println(poll1);//1
        System.out.println(maxQueue.peekMax());//9

        Integer poll2 = maxQueue.poll();
        System.out.println(poll2);//9
        System.out.println(maxQueue.peekMax());//3

    }

}
