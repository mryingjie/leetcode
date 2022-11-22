package com.github.myyingjie.leetcode.algorithm.dp;

/**
 * created by Yingjie Zheng at 2019-09-30 11:21
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 *
 * 例如，给定三角形：
 *
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为11（即，2+3+5+1= 11）。
 *
 * 120. 三角形最小路径和
 * 
 * 标准等边三角形 底边和高度一定一样
 */
public class 三角形最小路径和_dp {

    public static void main(String[] args) {
        int[][] triangle = {
                {2},
                {3,4},
                {6,5,7},
                {4,1,8,3}
        };

        System.out.println(minimumTotal(triangle));
    }


    public static int minimumTotal(int[][] triangle) {
        if(triangle == null || triangle.length == 0){
            return 0;
        }
        int row = triangle.length;
        int[] dp = new int[row];
        
        
        //自底向上遍历
        //1、给最后一行赋值 三角形最后一行的值
        System.arraycopy(triangle[(row - 1)], 0, dp, 0, dp.length);

        //2、向上累加 每次选最小的
        for (int i = row - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j],dp[j+1]) + triangle[i][j];
            }
        }
        
        return dp[0];
    }


}
