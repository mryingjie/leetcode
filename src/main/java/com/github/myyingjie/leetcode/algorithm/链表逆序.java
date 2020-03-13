package com.github.myyingjie.leetcode.algorithm;

import java.util.LinkedList;

/**
 * created by Yingjie Zheng at 2020-03-12 10:12
 * 链表逆序 https://mp.weixin.qq.com/s/MR_qAbonFqGF_ljeWUC26w
 */
public class 链表逆序 {

    public static void main(String[] args) {
        Node<Integer> head = new Node<Integer>(1);
        head.next = new Node<>(2);
        head.next.next = new Node<>(3);
        head.next.next.next = new Node<>(4);
        head.next.next.next.next = new Node<>(5);
        head.next.next.next.next.next = new Node<>(6);
        head.next.next.next.next.next.next = new Node<>(7);
        head = reverseLinkedList(head);
        Node<Integer> node = head;
        System.out.println(head.data);
        do {
            node = node.next;
            System.out.println(node.data);
        } while (node.next != null);

    }


    public static <T> Node<T> reverseLinkedList(Node<T> head) {
        Node<T> a = head;
        Node<T> b = a.next;
        Node<T> c = null;


        while (b != null) {
            c = b.next;

            b.next = a;
            // c.next = b;

            a = b;
            b = c;
        }

        head.next = null;
        head = a;

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
