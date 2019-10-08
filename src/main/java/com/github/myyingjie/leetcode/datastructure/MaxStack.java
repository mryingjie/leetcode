package com.github.myyingjie.leetcode.datastructure;

import java.util.Stack;

/**
 * created by Yingjie Zheng at 2019-09-10 17:06
 * 在O(1)时间内取最大值的堆栈
 *
 * 使用两个堆栈来保存数据，其中一个保存正常的数据，另一个保存最大值，最大值堆栈在压栈前需要比较待压栈的元素与栈顶元素的大小，
 * 如果比栈顶大，那么是一个新的最大值，应该压入栈，否则保持当前最大值不变，也就是不压栈。
 * 弹出数据时，如果弹出的值和最大值栈的栈顶元素相同，说明最大值被弹出，此时最大值栈也应该跟着出栈，这样可以保持最大值的更新
 */
public class MaxStack<T extends Comparable>{

    private Stack<T> maxStack;

    private Stack<T> dataStack;

    private int size;


    {
        maxStack = new Stack<>();
        dataStack = new Stack<>();
    }

    public boolean isEmpty(){
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    public T push(T t) {
        //取栈顶值  不出栈
        if (!maxStack.isEmpty()) {
            if (maxStack.peek().compareTo(t) <= 0) {
                maxStack.push(t);
            }

        } else {
            maxStack.push(t);
        }
        size ++;
        return dataStack.push(t);
    }

    public T poll() {
        T pop = dataStack.pop();

        if (pop != null && pop.equals(maxStack.peek())) {
            maxStack.pop();
        }
        size --;
        return pop;
    }

    public T peekMax() {
        return maxStack.peek();
    }

    public T peek(){
        return dataStack.peek();
    }

    public int size(){
        return size;
    }


    public static void main(String[] args) {

        MaxStack<Integer> maxStack = new MaxStack<>();
        maxStack.push(2);
        maxStack.push(3);
        maxStack.push(2);
        System.out.println(maxStack.peekMax());//3


        maxStack.push(1);
        System.out.println(maxStack.peekMax()); //3
        maxStack.push(4);
        System.out.println(maxStack.peekMax()); //4
        maxStack.push(5);
        System.out.println(maxStack.peekMax()); //5

        maxStack.push(2);
        maxStack.push(1);
        System.out.println(maxStack.peekMax()); //5

        maxStack.push(9);
        System.out.println(maxStack.peekMax()); //9

        maxStack.push(4);
        maxStack.push(3);
        System.out.println(maxStack.peekMax()); //9

        System.out.println("==========================");
        Integer poll = maxStack.poll();
        System.out.println(poll);//3
        System.out.println(maxStack.peek());//4
        System.out.println(maxStack.peekMax());//9


        Integer poll1 = maxStack.poll();
        System.out.println(poll1);//4
        System.out.println(maxStack.peek());//9
        System.out.println(maxStack.peekMax());//9

        Integer poll2 = maxStack.poll();
        System.out.println(poll2);//9
        System.out.println(maxStack.peek());//1
        System.out.println(maxStack.peekMax());//5

        Integer poll3 = maxStack.poll();
        System.out.println(poll3);//1
        System.out.println(maxStack.peek());//2
        System.out.println(maxStack.peekMax());//5

    }

}
