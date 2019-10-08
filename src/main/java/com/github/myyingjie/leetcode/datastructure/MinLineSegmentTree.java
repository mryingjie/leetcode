package com.github.myyingjie.leetcode.datastructure;


import com.github.myyingjie.common.Tuple2;


/**
 * created by Yingjie Zheng at 2019-09-24 11:10
 * 最小线段树 见：
 * https://zhuanlan.zhihu.com/p/34150142
 */
public class MinLineSegmentTree<T extends Comparable> {

    private Node[] nodes;

    /**
     * 逻辑极大值
     */
    private T max;

    public MinLineSegmentTree(T[] arr, T max) {
        //存储线段树的数组
        nodes = new Node[(arr.length << 1) + 2];
        build(0, arr);
        this.max = max;
    }

    //构造一颗线段树，传入下标
    private void build(int index, T[] base) {
        //取出该下标下的节点
        Node node = nodes[index];
        //根节点需要手动创建
        if (node == null) {
            nodes[index] = new Node(0, base.length - 1);
            node = nodes[index];
        }
        //如果这个线段的左端点等于右端点则这个点是叶子节点
        if (node.start == node.end) {
            node.data = base[node.start];
            node.index = node.start;
        } else {
            //否则递归构造左右子树
            int leftIndex = (index << 1) + 1;
            int rightIndex = (index << 1) + 2;
            int mid = (node.start + node.end) >> 1;
            //左孩子线段
            nodes[leftIndex] = new Node(node.start, mid);
            //右孩子线段
            nodes[rightIndex] = new Node(mid + 1, node.end);

            build(leftIndex, base);
            build(rightIndex, base);
            //这个节点的值等于左右孩子中较小的那个
            if (nodes[leftIndex].data.compareTo(nodes[rightIndex].data) <= 0) {
                node.data = nodes[leftIndex].data;
                node.index = nodes[leftIndex].index;
            } else {
                node.data = nodes[rightIndex].data;
                node.index = nodes[rightIndex].index;
            }

        }

    }

    /**
     * @param start 待查询区间的起始索引 其实是构建nodes数组的原数组的下标
     * @param end   待查询区间的最终索引 其实是构建nodes数组的原数组的下标
     * @return 待查询区间的最小值
     */
    public T queryMinValue(int start, int end) {
        return queryMin(0, start, end).getX();
    }

    /**
     * @param start 待查询区间的起始索引 其实是构建nodes数组的原数组的下标
     * @param end   待查询区间的最终索引 其实是构建nodes数组的原数组的下标
     * @return 待查询区间的最小值对应的原数组的索引值
     */
    public Integer queryMinIndex(int start, int end) {
        return queryMin(0, start, end).getY();
    }

    /**
     * @param start 待查询区间的起始索引 其实是构建nodes数组的原数组的下标
     * @param end   待查询区间的最终索引 其实是构建nodes数组的原数组的下标
     * @return 待查询区间的最小值以及对应的原数组的索引值
     */
    public Tuple2<T, Integer> queryMin(int start, int end) {
        return queryMin(0, start, end);
    }


    /**
     * @param index 当前的根节点 如果是递归第一次则为0  这里的index其实指的是nodes数组的下标
     * @param start 待查询区间的起始索引 其实是构建nodes数组的原数组的下标
     * @param end   待查询区间的最终索引 其实是构建nodes数组的原数组的下标
     * @return 待查询区间的最小值
     */
    private Tuple2<T, Integer> queryMin(int index, int start, int end) {
        Node node = nodes[index];
        //当前区间和待查询区间没有交集，那么返回一个极大值
        if (node.start > end || node.end < start) {
            return new Tuple2<>(max, node.index);
        }
        //如果当前的区间被包含在待查询的区间内则返回当前区间的最小值
        if (node.start >= start && node.end <= end) {
            return new Tuple2<>((T) node.data, node.index);
        }

        //如果当前区间包含待查询区间的一部分则递归查询左子树和右子树
        int leftIndex = (index << 1) + 1;
        int rightIndex = (index << 1) + 2;
        Tuple2<T, Integer> leftMin = queryMin(leftIndex, start, end);
        Tuple2<T, Integer> rightMin = queryMin(rightIndex, start, end);
        if (leftMin.getX().compareTo(rightMin.getX()) <= 0)
            return leftMin;
        else
            return rightMin;
    }

    public static void main(String[] args) {
        Integer[] arr = {5, 9, 7, 4, 6, 1};
        MinLineSegmentTree<Integer> tree = new MinLineSegmentTree<>(arr, Integer.MAX_VALUE);
        System.out.println(tree.queryMinValue(1, 4));
        System.out.println(tree.queryMinIndex(1, 4));
        tree.updateElement(2, 2);
        System.out.println(tree.queryMinValue(1, 4));
        System.out.println(tree.queryMinIndex(1, 4));

    }

    /**
     * @param index 原数组的下标
     * @param value 更新后的值
     */
    public void updateElement(int index, T value) {
        //找到这个代表这个值的叶子节点 然后更新叶子节点和它所有的父节点
        updateElement(0, index, value);
    }


    private void updateElement(int root, int index, T value) {
        Node node = nodes[root];
        if (node.start == index && node.end == index) {
            //当前节点就是叶子节点
            node.data = value;
            return;
        }
        int mid = (node.start + node.end) >> 1;
        int left = (root << 1) + 1;
        int right = (root << 1) + 2;
        if (index <= mid) {
            //在当前节点的左孩子节点
            updateElement(left, index, value);
        } else {
            //在当前节点的右孩子节点
            updateElement(right, index, value);
        }
        //更新当前节点的值。和对应的原数组的索引值
        if (nodes[left].data.compareTo(nodes[right].data) > 0) {
            node.data = nodes[right].data;
            node.index = nodes[right].index;
        } else {
            node.data = nodes[left].data;
            node.index = nodes[left].index;
        }

    }

    /**
     * 节点
     */
    private class Node<T extends Comparable> {
        /**
         * 区间左端点
         */
        int start;

        /**
         * 区间右端点
         */
        int end;

        /**
         * 节点权值 可以是最小值也可以是最大值
         */
        T data;

        /**
         * 当前数组的最小值或最大值对应的原数组的下标
         */
        int index;


        //构造方法中传入左端点和右端点
        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public String toString() {
            return start + "-" + end;
        }

    }


}
