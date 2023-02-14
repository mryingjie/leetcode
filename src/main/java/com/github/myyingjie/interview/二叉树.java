package com.github.myyingjie.interview;

import com.github.myyingjie.leetcode.datastructure.BST;

import java.util.*;

/**
 * created by Yingjie Zheng at 2023/2/3 17:09
 */
public class 二叉树 {

    public static void main(String[] args) {
        Integer[] arr = {6,10, 15,2, 8, 1, 3, 7, 9};
        BST<Integer> integerBST = new BST<>(arr);
        // 前序遍历
        // preorderTraversal2(integerBST);
        // System.out.println(integerBST.preorderTraversal2());
        //
        //
        // System.out.println(integerBST.postorderTraversal());
        // System.out.println(integerBST.postorderTraversal2());
        //
        // System.out.println(integerBST.inorderTraversal());
        //
        //
        // 层序遍历

        // System.out.println(integerBST.seqTraverse());
        seqTraverse(integerBST);
        System.out.println(integerBST.seqTraverse2());

    }

    static void preorderTraversal(BST bst){
        BST.Node root = bst.getRoot();
        preorderTraversal(root);
    }

    private static void preorderTraversal(BST.Node root) {
        if(root==null){
            return;
        }
        System.out.println(root.getT());
        preorderTraversal(root.getLeft());
        preorderTraversal(root.getRight());
    }

    static void preorderTraversal2(BST bst){
        BST.Node root = bst.getRoot();
        Stack<BST.Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            BST.Node pop = stack.pop();
            System.out.println(pop.getT());


            if(pop.getRight() != null){
                stack.push(pop.getRight());
            }

            if(pop.getLeft() != null){
                stack.push(pop.getLeft());
            }
        }

    }

    static void seqTraverse(BST bst){
        BST.Node root = bst.getRoot();
        Queue<BST.Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            BST.Node poll = queue.poll();
            System.out.println(poll.getT());
            if(poll.getLeft() != null){
                queue.add(poll.getLeft());
            }
            if(poll.getRight() != null){
                queue.add(poll.getRight());
            }
        }


    }

}
