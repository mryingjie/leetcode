package com.github.myyingjie.leetcode.algorithm;

/**
 * created by Yingjie Zheng at 2019-09-16 15:36
 * 求最长有效括号的长度
 * <p>
 * 动态规划
 * 使用dp[i] 表示 以str[i]结尾的子字符串的最大有效括号的长度
 * 则
 * 当dp[i] = '('时 这个子串不是一个有效的括号 此时为dp[i] = 0
 * <p>
 * 当dp[i] = ')'时 这个子串可能包含更多有效的括号
 * 此时如果 str[i-1] = '(' 则dp[i] = dp[i-2] + 2
 * 此时如果 str[i-1] = ')'
 * 还需要判断 str[i - dp[i-1] -1] ='(' 还是')' 即判断对应于当前位置的另外一个位置是不是'('
 * 如果是 则此子dp[i] = (dp[i-1] + 2) + dp[i-dp[i-1]-2]
 * 如果不是 则dp[i] = 0
 */
public class LongestValidParentheses {

    public static void main(String[] args) throws Exception {

        System.out.println(longestValidParentheses(")()()()()("));
    }

    public static int longestValidParentheses(String s) throws Exception {
        if (s == null || s.length() <= 1) {
            return 0;
        }
        int N = s.length();
        int[] dp = new int[s.length()];
        dp[0] = 0;
        int max = 0;
        for (int i = 1; i < N; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                // dp[i] = dp[i-1];//非法括号
            } else if (c == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = dp[i - 2 < 0 ? 0 : i - 2] + 2;
                } else if (s.charAt(i - 1) == ')') {
                    if (i - dp[i - 1] - 1 < 0) {
                        dp[i] = 0;//非法括号
                        continue;
                    }
                    if (s.charAt(i - dp[i - 1] - 1) == '(') {
                        dp[i] = (dp[i - 1] + 2) + dp[i - dp[i - 1] - 2 < 0 ? 0 : i - dp[i - 1] - 2];
                    } else {
                        dp[i] = 0;//非法括号
                    }
                } else {
                    throw new RuntimeException("非法的字符");
                }
            } else {
                throw new RuntimeException("非法的字符");
            }
            max = Math.max(max, dp[i]);
        }


        return max;
    }

}
