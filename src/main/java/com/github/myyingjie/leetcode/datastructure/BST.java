package com.github.myyingjie.leetcode.datastructure;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.*;

/**
 * created by Yingjie Zheng at 2019-09-30 13:48
 * 普通二叉查找树
 *
 * AVL树在普通二叉查找树上保证任意节点的左右子树高度之差<=1 条件苛刻删除插入比较耗时 但树高度最低查找是最快的
 *
 * 红黑树在普通二叉查找树上保证最高的子树高度不会大于最低的子树高度的二倍 较AVL树
 *
 * B+树
 */
@SuppressWarnings("unchecked")
public class BST<T extends Comparable> {

    public static void main(String[] args) {
        Integer[] arr = {6,10, 15,2, 8, 1, 3, 7, 9};
        BST<Integer> integerBST = new BST<>(arr);
        // 前序遍历
        System.out.println(integerBST.preorderTraversal());
        System.out.println(integerBST.preorderTraversal2());


        System.out.println(integerBST.postorderTraversal());
        System.out.println(integerBST.postorderTraversal2());

        System.out.println(integerBST.inorderTraversal());

    }

    private Node root;

    public BST(List<T> list) {
        this((T[]) list.toArray());
    }

    public BST(T[] arr) {
        for (T t : arr) {
            insert(t);
        }
    }

    public void insert(T t) {
        if (t == null) {
            return;
        }
        Node node = new Node().setT(t);
        if (root == null) {
            //根节点为空
            root = node;
        } else {
            //找到根节点 从根节点开始遍历
            Node index = root;

            while (true) {
                //当前节点与待插入的节点比较
                if (node.compareTo(index) < 0) {
                    //待插入节点小于当前节点  应该插入当前节点的左边
                    if (index.left == null) {
                        index.left = node;
                        break;
                    }
                    index = index.left;
                } else {
                    //插入当前节点的右边
                    if (index.right == null) {
                        index.right = node;
                        break;
                    }
                    index = index.right;
                }

            }

        }
    }

    /**
     * @return 树节点个数
     */
    public int size() {
        if (root != null) {
            return preorderTraversal().size();
        } else {
            return 0;
        }
    }


    /**
     * 删除节点
     */
    public boolean delete(T t) {
        //第一步找到当前节点 以及他的父节点 以及要删除的节点是他父节点的左孩子还是右孩子
        Node parent = null;
        Node current = root;
        boolean isLeft = false;
        while (t.compareTo(current.t) != 0) {
            if (t.compareTo(current.t) < 0) {
                //要删除的节点小于当前节点
                isLeft = true;
                parent = current;
                current = current.left;
            } else {
                //要删除的几点大于当前节点
                isLeft = false;
                parent = current;
                current = current.right;
            }
            if (current == null) {
                //没有找到相对应的节点
                return false;
            }

        }
        //如果要删除的节点是叶子节点
        if (current.left == null && current.right == null) {
            if (current == root) {
                root = null;
            } else {
                //将他的父节点对应的指针置为空
                if (isLeft) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
        }

        //如果要删除的节点只有左孩子
        if (current.right == null) {
            if (current == root) {
                root = current.left;
            } else {
                //将当前节点的父节点的得对应指针指向他的左孩子
                if (isLeft) {
                    parent.left = current.left;
                } else {
                    parent.right = current.left;
                }
            }

        }

        //如果要删除的节点只有右孩子
        if (current.left == null) {
            if (current == root) {
                root = current.right;
            } else {
                //将当前节点的父节点的得对应指针指向他的右孩子
                if (isLeft) {
                    parent.left = current.right;
                } else {
                    parent.right = current.right;
                }
            }
        }

        //如果要删除的节点有左孩子也有右孩子
        if (current.left != null && current.right != null) {
            //找到这个节点的右孩子为根的中序后继节点 即它右孩子中的最小值替换它
            Node node = replaceSuccessor(current.right, current);
            if (parent != null) {
                //要删除的节点不是根节点
                parent.right = node;
            } else {
                //要删除的节点是根节点
                root = node;
            }
            node.left = current.left;
            node.right = current.right;
        }


        return true;
    }

    /**
     * 删除并返回中序后继节点 保证这个根节点一定有左孩子
     */
    private Node replaceSuccessor(Node index, Node parent) {
        Node current = index.getLeft();
        Node successor = index;
        Node successorParent = parent;
        //找到中序后继结点以及他的父节点
        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.left;
        }
        //删除这个节点
        if (successor == index) {
            //这个节点本身就是中序后继节点
            successorParent.right = null;
        } else {
            successorParent.left = null;
        }

        return successor;
    }

    public T getMin() {
        return getMinBase(root);
    }

    private T getMinBase(Node root) {
        Node left = root.getLeft();
        if (left == null) {
            return root.t;
        }
        return getMinBase(left);
    }

    public T getMax() {
        return getMaxBase(root);
    }

    private T getMaxBase(Node root) {
        Node right = root.getRight();
        if (right == null) {
            return root.t;
        }
        return getMaxBase(right);
    }

    /**
     * 中序遍历
     */
    public List<T> inorderTraversal() {
        List<T> list = new ArrayList<>();
        inorderTraversalBase(root, list);
        return list;
    }

    private void inorderTraversalBase(Node root, List<T> list) {
        if (root == null) {
            return;
        }
        //先遍历左孩子然后遍历自己最后遍历右孩子
        inorderTraversalBase(root.left, list);
        list.add(root.t);
        inorderTraversalBase(root.right, list);
    }

    /**
     * 前序遍历
     */
    public List<T> preorderTraversal() {
        List<T> list = new ArrayList<>();
        preorderTraversalBase(root, list);
        return list;
    }

    private void preorderTraversalBase(Node root, List<T> list) {
        if (root == null) {
            return;
        }
        //先遍历自己然后遍历左孩子最后遍历右孩子
        list.add(root.t);
        preorderTraversalBase(root.left, list);
        preorderTraversalBase(root.right, list);
    }

    // 前序遍历 利用栈的迭代法
    public List<T> preorderTraversal2() {
        List<T> list = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            Node pop = stack.pop();
            list.add(pop.t);
            if(Objects.nonNull(pop.right)){
                stack.push(pop.right);
            }
            if(Objects.nonNull(pop.left)){
                stack.push(pop.left);
            }
        }

        return list;
    }




    /**
     * 后续遍历
     */
    public List<T> postorderTraversal() {
        List<T> list = new ArrayList<>();
        postorderTraversalBase(root, list);
        return list;
    }

    private void postorderTraversalBase(Node root, List<T> list) {
        if (root == null) {
            return;
        }
        //先遍历左孩子然后遍历右孩子 最后遍历自己
        postorderTraversalBase(root.left, list);
        postorderTraversalBase(root.right, list);
        list.add(root.t);
    }

    /**
     * 后续遍历
     */
    public List<T> postorderTraversal2() {
        List<T> list = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            Node pop = stack.pop();
            if(Objects.nonNull(pop.left)){
                stack.push(pop.left);
            }
            if(Objects.nonNull(pop.right)){
                stack.push(pop.right);
            }
            list.add(0,pop.t);
        }
        return list;
    }

    /**
     * 层序遍历
     */
    public List<T> seqTraverse(){
        List<T> list = new ArrayList<>();
        if(size()==0){
            return list;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            Node poll = queue.poll();
            if (poll != null){
                list.add(poll.t);
                queue.add(poll.left);
                queue.add(poll.right);
            }
        }
        return list;
    }


    @Data
    @Accessors(chain = true)
    private class Node implements Comparable<Node> {

        private Node left;

        private Node right;


        /**
         * 值
         */
        private T t;


        @Override
        public int compareTo(Node o) {
            return this.t.compareTo(o.t);
        }
    }
}
