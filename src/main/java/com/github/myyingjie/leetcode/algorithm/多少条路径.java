package com.github.myyingjie.leetcode.algorithm;

/**
 * created by Yingjie Zheng at 2019-09-20 10:14
 * <p>
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角。
 * 问总共有多少条不同的路径？
 * <p>
 * 明显的动态规划
 * 1、用dp[i][j] 表示到达横坐标为i 纵坐标为j的位置时的所有可能的路径数 假设坐标都从0开始
 * <p>
 * 2、最终结果是求dp[m-1][n-1]的值
 * <p>
 * 3、状态转移方程
 * 当i>0 && j>0时
 * dp[i][j] = dp[i-1][j] + dp[i][j-1]
 * 当i>0 && j ==0时
 * dp[i][0] = 1
 * 当i = 0时
 * dp[0][j] = 1
 */
public class 多少条路径 {

    public static void main(String[] args) {
        System.out.println(uniquePaths(4, 4));

        int[][] map = {
                {0,0,0},
                {0,1,0},
                {0,0,0}
        };

        int[][] map2 = {{0,0}};
        System.out.println(uniquePathsWithObstacles(map2));
    }


    public static int uniquePaths(int m, int n) {
        if (m == 1 || n == 1) return 1;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0 || i == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 有障碍物
     * 输入:
     * [
     * [0,0,0],
     * [0,1,0],
     * [0,0,0]
     * ]
     * 其中1代表障碍物
     * <p>
     * 本题解法与上题一样 状态转移方程也一样
     * 不同的在于如果发现障碍物则到达该位置的情况有0种即不可能到
     *
     * 优化如果可以修改map  可以直接将map当做dp来使用
     */
    public static int uniquePathsWithObstacles(int[][] map) {
        if (map == null || map.length == 0) {
            return 0;
        }
        int n = map.length;
        int m = map[0].length;
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) {
                    if (map[0][0] == 1) {
                        return 0;
                    } else {
                        dp[0][0] = 1;
                    }
                } else {
                    if (map[i][j] != 1) {
                        if (i == 0) {
                            dp[i][j] = dp[0][j - 1];
                        } else if (j == 0) {
                            dp[i][j] = dp[i - 1][0];
                        } else {
                            dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                        }
                    }
                }

            }
        }


        return dp[n - 1][m - 1];
    }


}
