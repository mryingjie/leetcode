package com.github.myyingjie.leetcode.algorithm.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * created by Yingjie Zheng at 2019-09-27 09:34
 * 115. 不同的子序列
 * 给定一个字符串 S 和一个字符串 T，计算在 S 的子序列中 T 出现的个数。
 * <p>
 * 一个字符串的一个子序列是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE"是"ABCDE"的一个子序列，而 "AEC" 不是）
 */
@SuppressWarnings("all")
public class 字符串子序列出现的次数 {

    public static void main(String[] args) {
        System.out.println(numDistinct3("ABCABDC", "ABC"));
    }

    /**
     * 动态规划
     * (一）状态
     * dp[i][j]表示s1的前i个字符的子序列中，包含多少个s2的前 j 个字符子串
     * <p>
     * （二）转移方程
     * 如果s1的第 i 个字符和s2的第 j 个字符不同的话，
     * 意味着s1的第 i 个字符，必不参与组成子序列
     * dp[i][j] = dp[i - 1][j]
     * （例如 abc, ab) dp[3][2] = dp[2][2]
     * 如果s1的第 i 个字符和s2的第 j 个字符相同的话，
     * 意味着s1的第 i 个字符，可以参与组成子序列，也可不参与组成子序列
     * dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j]
     * (例如 abcc, abc) dp[4][3] = dp[3][2] + dp[3][3]
     * <p>
     * （三）初始化
     * s1的前 i 个字符和s2的前 0 个字符，意味着 s1 中子序列等于空串的个数都是 1
     * dp[i][0] = 1
     * <p>
     * （四）结果
     * dp[s.length()][t.length()]
     */
    public static int numDistinct1(String s, String t) {
        if (s == null || t == null) {
            return 0;
        }
        if (t.length() == 0) {
            return 1;
        }
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int i = 0, l = s.length(); i <= l; i++) {
            for (int j = 0, n = t.length(); j <= n; j++) {
                if (j == 0) {
                    dp[i][j] = 1;
                } else if (i == 0) {
                    dp[i][j] = 0;
                } else if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    //s的最后那个字符可以参与子序列 也可以不参与子序列
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    //s的最后那个字符必定不参数子序列
                    dp[i][j] = dp[i - 1][j];
                }

            }
        }
        return dp[s.length()][t.length()];
    }

    /**
     * 优化动态规划的空间复杂度
     *
     */
    public static int numDistinct3(String s, String t) {
        if (s == null || t == null) {
            return 0;
        }
        if (t.length() == 0) {
            return 1;
        }

        int[] dp_ = new int[s.length() + 1];
        int[] pre = new int[s.length()+ 1];
        for (int j = 0, n = t.length(); j <= n; j++){

            for (int i = 0, l = s.length(); i <= l; i++) {

                if(j == 0){
                    pre[i] = 1;
                }else if(i == 0){
                    dp_[i] = 0;
                } else {
                    int tmp = dp_[i-1];
                    if(t.charAt(j-1) == s.charAt(i-1)){
                        dp_[i] = tmp + pre[i-1];
                    }else {
                        dp_[i] = tmp;
                    }
                    pre[i-1] = tmp;
                }

            }
        }



        return dp_[s.length()];
    }

    //双指针 递归
    public static int numDistinct2(String s, String t) {
        if (s == null || t == null) {
            return 0;
        }
        if (t.length() == 0) {
            return 1;
        }
        Map<String, Integer> map = new HashMap<>();
        return numDistinct2(s, 0, t, 0, map);
    }

    private static int numDistinct2(String s, int s_start, String t, int t_start, Map<String, Integer> cache) {
        //当t为空 只有一种情况 将s的所有字符删除
        if (t_start == t.length()) {
            return 1;
        }
        //当s为空时  没有一个符合条件的
        if (s_start == s.length()) {
            return 0;
        }
        String key = s_start + "|" + t_start;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        int count;
        if (s.charAt(s_start) == t.charAt(t_start)) {
            //当字符相等时 s可以选择使用这个字符也可以不选择这个字符 选择的话t也要下移一个索引
            count = numDistinct2(s, s_start + 1, t, t_start + 1, cache) + numDistinct2(s, s_start + 1, t, t_start, cache);
        } else {
            //当字符不等 s必定不能选择这个字符 t的索引不变
            count = numDistinct2(s, s_start + 1, t, t_start, cache);
        }
        cache.put(key, count);
        return count;
    }


}
