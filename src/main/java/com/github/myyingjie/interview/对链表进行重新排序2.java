package com.github.myyingjie.interview;


/**
 * created by Yingjie Zheng at 2022/11/25 18:54
 * 
 * 1-2-3-4-5-6  ->   1-6-2-5-3-4
 * 
 * 1-2-3-4-5  -> 1-5-2-4-3
 * 
 * 确定链表中点， 将后半部分倒叙 然后将两个链表依次合并
 */
public class 对链表进行重新排序2 {

    public static void main(String[] args) {
        Node<Integer> head = new Node<Integer>(1);
        head.next = new Node<>(2);
        head.next.next = new Node<>(3);
        head.next.next.next = new Node<>(4);
        head.next.next.next.next = new Node<>(5);
        // head.next.next.next.next.next = new Node<>(6);
        head = reSort(head);
        Node<Integer> node = head;
        System.out.println(head.data);
        do {
            node = node.next;
            System.out.println(node.data);
        } while (node.next != null);
        
        
    }
    
    public static Node<Integer> reSort(Node<Integer> head){
       // 确定中点
        Node<Integer> node1 = head;
        Node<Integer> node2 = head;

        while (node2 != null && node2.next != null){
            node1 = node1.next;
            node2 = node2.next.next;
        }

        // node1即为中点
        Node<Integer> mid = node1;
        // 链表逆序
        node1 = reverse(node1);

        // 依次合并
        Node<Integer> node = head;
        Node<Integer> p1 = head.next;
        Node<Integer> p2 = node1;
        boolean flag = false;
        while (p1 != mid || p2 != null){
            if(p1 == mid){
                node.next = p2;
                p2 = p2.next;
                node = node.next;
                continue;
            }

            if(p2 == null){
                node.next = p1;
                p1 = p1.next;
                node = node.next;
                continue;
            }

            if (flag) {
                node.next = p1;
                p1 = p1.next;
                node = node.next;
            }else {
                node.next = p2;
                p2 = p2.next;
                node = node.next;
            }

            flag = !flag;
        }
        return head;
    }

    private static Node<Integer> reverse(Node<Integer> node1) {
        Node<Integer> p1 = node1;
        Node<Integer> p2 = node1.next;
        Node<Integer> p3 = null;
        while (p2 != null){
            p3 = p2.next;
            p2.next = p1;
            p1 = p2;
            p2 = p3;
        }
        node1.next = null;
        return p1;

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
