package com.github.myyingjie.leetcode.algorithm;

import com.github.myyingjie.common.Tuple2;
import com.github.myyingjie.leetcode.datastructure.MinLineSegmentTree;

import java.util.Arrays;

/**
 * created by Yingjie Zheng at 2019-09-25 10:40
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * <p>
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 * 见：https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
 */
public class 矩形最大面积 {

    public static void main(String[] args) {
        矩形最大面积 largestRectangleArea = new 矩形最大面积();
        int[] heights = {0,9};
        System.out.println(largestRectangleArea.largestRectangleArea(heights));

    }

    public int largestRectangleArea(int[] heights) {
        return getMaxArea2(heights);
    }


    /**
     * 解法1 利用快排的思想将原数组按选定的最小的柱子划分成左右两部分以及包含选定的柱子的部分
     * 1、最大矩形区域在不包含选定柱子的左半区域当中。
     * 2、最大矩形区域在不包含选定柱子的右半区域当中。
     * 3、最大矩形区域包含选定柱子的区域
     * 而包含选定的最小柱子的部分 其面积最大值必然是这个柱子的高度乘以数组的长度
     * 剩余两部分的最大值通过递归求得
     * <p>
     * 这里就涉及到在区间数组中寻找最小值的问题了。  可以利用一个数据结构[线段树]可以完美解决这个问题
     *
     * @see MinLineSegmentTree
     */
    public int getMaxArea1(int[] heights) {

        Integer[] integers = Arrays.stream(heights).boxed().toArray(Integer[]::new);
        //构建线段树
        MinLineSegmentTree<Integer> tree = new MinLineSegmentTree<>(integers, Integer.MAX_VALUE);
        return getMaxArea1(heights, tree, 0, heights.length - 1);

    }

    public int getMaxArea1(int[] heights, MinLineSegmentTree<Integer> tree, int start, int end) {
        if (start == end) {
            return heights[start];
        }
        if (start > end) {
            return Integer.MIN_VALUE;
        }

        Tuple2<Integer, Integer> minTuple = tree.queryMin(start, end);
        Integer index = minTuple.getY();
        Integer value = minTuple.getX();

        //计算三部分的最大面积
        //包含最小柱子的
        int area1 = value * (end - start + 1);
        //左半部分
        int area2 = getMaxArea1(heights, tree, start, index - 1);
        //右半部分
        int area3 = getMaxArea1(heights, tree, index + 1, end);

        return Math.max(area1, Math.max(area2, area3));
    }

    /**
     * 第二种解法
     * 以每个柱子为中心 向前向后寻找第一个比它矮的柱子，则两端的最后一个比它高的柱子索引之差为宽度 这个柱子的高为长度，面积就是包含了这个柱子的最大面积。
     * 遍历所有柱子并求最大的。
     * <p>
     * 优化点 可以每次求得某个柱子的左边第一个比它小的柱子后 将其索引i记录下来
     * 因为这个柱子的下一个柱子如果比这个柱子小 则索引为i的柱子之前的所有柱子都会比大 可以直接跳跃到i的位置进行比较  反之右边的也一样
     */
    public int getMaxArea2(int[] heights) {
        int maxArea = 0;

        //求每个柱子左边第一个小于它的柱子的索引
        int[] leftLessMin = new int[heights.length];
        int[] rightLessMin = new int[heights.length];
        for (int i = 0; i < heights.length; i++) {
            int height = heights[i];
            if(height == 0){
                //如果高度为0 则不用求宽度了 因为面积始终未0
                continue;
            }
            //求每个柱子左边第一个小于它的柱子的索引
            int left = i - 1;
            while (left >= 0 && heights[left] >= height) {
                if (left == i - 1) {
                    left = leftLessMin[left] - 1;
                } else {
                    left--;
                }
            }
            leftLessMin[i] = left + 1;
        }
        for (int i = heights.length - 1; i >= 0; i--) {
            int height = heights[i];
            //求每个柱子右边第一个小于它的柱子的索引
            if(height == 0){
                continue;
            }
            int right = i + 1;
            while (right <= heights.length - 1 && heights[right] >= height) {
                if (right == i + 1) {
                    right = rightLessMin[right] + 1;
                } else {
                    right++;
                }
            }
            rightLessMin[i] = right - 1;
        }


        //遍历所有柱子 求面积最大值
        for (int i = 0; i < heights.length; i++) {
            int height = heights[i];
            int left = leftLessMin[i];
            int right = rightLessMin[i];
            maxArea = Math.max(maxArea, (right - left + 1) * height);
        }

        return maxArea;
    }


}
