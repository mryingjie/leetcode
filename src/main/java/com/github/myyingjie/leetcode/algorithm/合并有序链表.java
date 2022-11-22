package com.github.myyingjie.leetcode.algorithm;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * created by Yingjie Zheng at 2020-03-18 10:04
 */
public class 合并有序链表 {





    public static void main(String[] args) {
        Node<Integer> head = new Node<>(1);
        head.next = new Node<>(2);
        head.next.next = new Node<>(3);
        head.next.next.next = new Node<>(4);
        head.next.next.next.next = new Node<>(5);
        head.next.next.next.next.next = new Node<>(6);
        head.next.next.next.next.next.next = new Node<>(7);

        Node<Integer> node2 = new Node<>(1);
        node2.next = new Node<>(2);
        node2.next.next = new Node<>(3);
        node2.next.next.next = new Node<>(4);
        node2.next.next.next.next = new Node<>(5);
        node2.next.next.next.next.next = new Node<>(6);
        node2.next.next.next.next.next.next = new Node<>(7);
        head = mergite(head, node2);
        System.out.println();
    }


    public static Node<Integer> mergite(Node<Integer> node1, Node<Integer> node2) {
        if ( node1 == null || node2 == null) {
            return  node1 != null ?  node1 : node2;
        }
        //合并后单链表头结点
        Node<Integer> head =  node1.data < node2.data ?  node1 : node2;

        Node<Integer> cur1 = head ==  node1 ?  node1 : node2;
        Node<Integer> cur2 = head ==  node1 ? node2 :  node1;

        Node<Integer> pre = null;//cur1前一个元素
        Node<Integer> next = null;//cur2的后一个元素

        while (cur1 != null && cur2 != null) {
            //第一次进来肯定走这里
            if (cur1.data <= cur2.data) {
                pre = cur1;
                cur1 = cur1.next;
            } else {
                next = cur2.next;
                pre.next = cur2;
                cur2.next = cur1;
                pre = cur2;
                cur2 = next;
            }
        }
        pre.next = cur1 == null ? cur2 : cur1;
        return head;
    }


    static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return data.toString();

        }
    }

}
