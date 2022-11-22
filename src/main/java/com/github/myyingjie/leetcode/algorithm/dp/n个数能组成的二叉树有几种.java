package com.github.myyingjie.leetcode.algorithm.dp;

/**
 * created by Yingjie Zheng at 2019-09-26 10:29
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 * <p>
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 * <p>
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 * <p>
 * 思路 1、动态规划
 * dp[0] = 1;
 * dp[1] = 1;
 * dp[2] = dp[1]*dp[0] + dp[0]*dp[1] = 2
 * dp[3] = dp[2]*dp[0] + dp[1]*dp[1]  + dp[0]*dp[2] = 5
 * dp[4] = dp[3]*dp[0] + dp[2]*d[1] + dp[1]*dp[2] + dp[0]*dp[3] = 14
 * <p>
 * 2、根据思路1的答案可以发现规律 其实这个数列叫作Catalan numbers，也叫Segner numbers。其第n项为
 * (2n)!/[n!(n+1)!] 或 Cn = 2*(2n+1)Cn-1 / n+2
 */
public class n个数能组成的二叉树有几种 {

    public static void main(String[] args) {
        System.out.println(numTrees1(4));
    }

    public static int numTrees1(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int right = i - 1;
            int left = 0;
            int sum = 0;
            int mid = i >> 1;
            if (i % 2 == 0) {
                //偶数 只需算一半乘2 即可
                while (right >= (mid)) {
                    sum += dp[right] * dp[left];
                    right--;
                    left++;
                }
                dp[i] = sum << 1;
            } else {
                //奇数
                while (right > (mid)) {
                    sum += dp[right] * dp[left];
                    right--;
                    left++;
                }
                sum = sum << 1;
                sum += dp[mid] * dp[i >> 1];
                dp[i] = sum;
            }


        }

        return dp[n];

    }

    public static int numTrees2(int n) {
        long c = 1;
        for (int i = 0; i < n; i++) {
            c = (((i << 1) + 1) << 1) * c / (i + 2);
        }
        return (int) c;

    }
}
