package com.github.myyingjie.leetcode.algorithm;

/**
 * created by Yingjie Zheng at 2019-09-20 14:39
 */
public class 最小编辑距离 {

    public static void main(String[] args) {
        String word1 = "horse";
        String word2 = "ros";
        System.out.println(minDistance(word1, word2));
    }

    /**
     * 将word1 转换为 word2所需要的最少操作次数 增加一个字符  删除一个字符 替换一个字符
     * 输入: word1 = "horse", word2 = "ros"
     * 输出: 3
     * 解释:
     * horse -> rorse (将 'h' 替换为 'r')
     * rorse -> rose (删除 'r')
     * rose -> ros (删除 'e')
     * <p>
     * 两个字符串的操作  如果使用动态规划的算法一般需要一个二维数组 下标i和j分别代表两个字符串的字符下标
     * 比如以上例子中的dp[1][2] 表示word1中的"ho" 转换成word2中的"ros" 需要的最少步骤
     * <p>
     * 状态转换
     * word1[i] == word2[j]
     * dp[i][j] = dp[i-1][j-1]  什么也不做
     * 如果dp[i] != dp[j]
     * dp[i][j] = Min(dp[i-1][j],dp[i][j-1],dp[i-1][j-1]) + 1
     * 其中dp[i-1][j]代表i相较于j多了一个字符则删除  dp[i][j-1]代表i与j相比少了一个字符则插入  dp[i-1][j-1] 代表替换最后的字符
     * <p>
     * dp[0][0] 表示都为空字符串 而且dp[0][0]=0
     */
    public static int minDistance(String word1, String word2) {
        if (word1 == null && word2 == null) {
            return 0;
        } else if (word1 == null || word1.length() == 0) {
            return word2.length();
        } else if (word2 == null || word2.length() == 0) {
            return word1.length();
        } else {
            int[][] dp = new int[word1.length() + 1][word2.length() + 1];

            for (int i = 0, w1 = word1.length() + 1; i < w1; i++) {

                for (int j = 0, w2 = word2.length() + 1; j < w2; j++) {
                    if (i == 0) {
                        dp[i][j] = j;
                    } else if (j == 0) {
                        dp[i][j] = i;
                    } else {
                        if (word1.charAt(i-1) == word2.charAt(j-1)) {
                            dp[i][j] = dp[i - 1][j - 1];
                        } else {
                            dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                        }
                    }
                }

            }


            return dp[word1.length()][word2.length()];
        }


    }


}
