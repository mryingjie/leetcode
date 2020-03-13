package com.github.myyingjie.leetcode.algorithm;

/**
 * created by Yingjie Zheng at 2019-09-20 11:07
 * <p>
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * <p>
 * 输入:
 * [
 * [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 * <p>
 * <p>
 * 动态规划
 * 使用dp[i][j] 表示对应位置的最小路径总和
 * 动态转移方程
 * dp[i][j] = min(dp[i-1][j],dp[i][j-1]) + grid[i][j]
 * dp[0][0] = grid[0][0]
 * dp[0][j] = dp[0][j-1] + grid[0][j]
 * dp[i][0] = dp[i-1][0] + grid[i][0]
 */
public class 最短路径 {

    public static void main(String[] args) {
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        System.out.println(minPathSum(grid));
    }

    public static int minPathSum(int[][] grid) {
        if (grid == null) {
            return 0;
        }

        int rowNum = grid.length;
        int colNum = grid[0].length;
        int[][] dp = new int[rowNum][colNum];

        for (int row = 0; row < rowNum; row++) {
            for (int col = 0; col < colNum; col++) {
                if (row == 0 && col == 0) {
                    dp[0][0] = grid[0][0];
                } else if (row == 0) {
                    dp[0][col] = dp[0][col - 1] + grid[0][col];
                } else if (col == 0) {
                    dp[row][0] = dp[row - 1][0] + grid[row][0];
                } else {
                    dp[row][col] = Math.min(dp[row - 1][col], dp[row][col - 1]) + grid[row][col];
                }
            }
        }
        return dp[rowNum - 1][colNum - 1];
    }

}
