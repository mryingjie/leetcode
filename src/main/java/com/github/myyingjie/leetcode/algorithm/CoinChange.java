package com.github.myyingjie.leetcode.algorithm;

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
 * 2、分析最基本的几种情况
 * dp[0] = 0;
 * 最差的情况 d[m] = m 目标是找到比最差的情况好的情况
 *
 * 3、最终的结果:dp[m]
 *
 */
public class CoinChange {


    public static void main(String[] args) {
        int[] coins = {2, 4};
        System.out.println(coinChange(coins, 8));
    }

    public static int coinChange(int[] coins,int amount){
        int[] dp = new int[amount + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = amount;
        }
        dp[0] = 0;
        for (int m = 1; m <= amount; m++) {
            for (int ci : coins) {
                if(ci > m) continue;
                dp[m] = Math.min(dp[m],dp[m-ci] + 1);
            }
        }
        return dp[amount] == amount? -1 : dp[amount];
    }

}
