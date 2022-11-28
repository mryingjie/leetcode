package com.github.myyingjie.interview;


/**
 * created by Yingjie Zheng at 2022/11/25 18:54
 * 
 * 1-2-3-4-5-6  ->   1-6-2-5-4-3
 * 
 * 1-2-3-4-5  -> 1-5-2-4-3
 * 
 * 确定链表中点， 将后半部分倒叙 然后将两个链表依次合并
 */
public class 对链表进行重新排序 {

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
        Node<Integer> nextOne = head;
        // 双指针 确定链表中点
        Node<Integer> nextTwo = head;
        
        
        while (nextTwo != null && nextTwo.next != null){
            nextOne = nextOne.next;
            nextTwo = nextTwo.next.next;
        }
        
        // 此时 nexOne就是中点 将已nextOnde为头的链表逆序
        Node<Integer> newHead = reverse(nextOne);
        
        // 依次合并
        
        Node<Integer> tmp1 = head.next;
        Node<Integer> tmp2 = newHead;
        Node<Integer> indexNode = head;
        indexNode.next = tmp2;
        indexNode = indexNode.next;
        boolean chooseTmp1 = true;
        while (tmp2!=null && tmp1 != null){
            if (chooseTmp1) {
                indexNode.next = tmp1;
                tmp1 = tmp1.next;
            }else {
                indexNode.next = tmp2;
                tmp2 = tmp2.next;
            }
            chooseTmp1 = !chooseTmp1;
            indexNode = indexNode.next;
        }
        
        return head;

    }
    
    public static Node<Integer> reverse(Node<Integer> head){
        Node<Integer> p1 = head;
        Node<Integer> p2 = head.next;
        Node<Integer> p3 = null;
        
        while (p2 != null){
            p3 = p2.next;
            p2.next = p1;
            p1=p2;
            p2=p3;
            
        }
        head.next = null;
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
