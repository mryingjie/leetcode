package com.github.myyingjie.leetcode.algorithm.dp;

import java.util.Arrays;

/**
 * created by Yingjie Zheng at 2019-09-19 14:58
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 *
 * 使用动态规划
 * 1、分析所有状态和行为 并写出状态转移方程
 *  状态:选取的硬币总额m  使用一个一维数组dp[m]表示 例如dp[10] 表示选取的硬币总额最大为10的时候最少的硬币数
 *  行为: 选取下一个硬币币值为ci 或 超出最大金额不选取 对应的状态变化为
 *  状态转移方程：
 *
 *  dp[m] = min(d[m],d[m-ci] + 1)//解释：当选取的金额最大为10时的硬币数量 = min(之前存储的最小数量,没有选择当前硬币的最小数量 + 1)
 *
 * 当 金额比硬币值小时，结果不存在用极大值表示，当用min函数时，极大值一定不会被选中 此处使用Integer.MAX_VALUE - 1是因为Integer.MAX_VALUE + 1会变成负数
 * 当 dp[m-ci] 不存在时 dp[m]一定不存在
 * 2、分析最基本的几种情况
 * dp[0] = 0;
 * 当ci = m时  dp[m] == 1
 *
 * 3、最终的结果:dp[m]
 *
 */
public class 抛硬币 {


    public static void main(String[] args) {
        int[] coins = {2, 4};
        System.out.println(coinChange(coins, 6));
    }

    public static int coinChange(int[] coins,int amount){
        int[] dp = new int[amount+1];
        Arrays.fill(dp,Integer.MAX_VALUE - 1);
        dp[0] = 0;
        for (int m = 1; m <= amount; m++) {
            for (int j = 0; j < coins.length; j++) {
                int ci = coins[j];
                if(ci > m){
                    continue;
                }
                if(ci == m){
                    dp[m] = 1;
                    break;
                }
                dp[m] = Math.min(dp[m-ci] + 1,dp[m]);
            }

        }


        return dp[amount] == Integer.MAX_VALUE - 1? -1 : dp[amount];
    }

}
