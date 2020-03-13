package com.github.myyingjie.leetcode.algorithm;

/**
 * created by Yingjie Zheng at 2019-09-25 15:49
 * 给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 * 见 ：https://leetcode-cn.com/problems/maximal-rectangle/
 *
 * 本题可以转化为{@link 矩形最大面积}的求解
 * 见 https://leetcode-cn.com/problems/maximal-rectangle/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-1-8/
 */
public class 只包含1的最大矩形面积 {

    public static void main(String[] args) {
        char[][] matrix ={
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        };
        System.out.println(maximalRectangle(matrix));
    }

    public static int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }
        矩形最大面积 maximalRectangle = new 矩形最大面积();
        int max = 0;
        int[] heights = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            char[] chars = matrix[i];
            //将当前heights 新增加一行
            for (int j = 0; j < heights.length; j++) {
                if(chars[j] == '1'){
                    heights[j] += 1;
                }else {
                    heights[j] = 0;
                }
            }
            //调用方法求柱状图的最大面子
            int maxArea2 = maximalRectangle.getMaxArea2(heights);
            max = Math.max(maxArea2, max);
        }


        return max;
    }

}
