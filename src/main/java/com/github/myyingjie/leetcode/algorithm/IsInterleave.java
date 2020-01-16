package com.github.myyingjie.leetcode.algorithm;

import com.github.myyingjie.common.Tuple2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * created by Yingjie Zheng at 2019-09-26 14:11
 * <p>
 * 给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的  即s3是由s1和s2的字符在不改变原本顺序的情况下组成的
 * 例如 ab ac  则 abac  aacb aabc 都符合 然而 acba就不是 因为必定有一个字符串的字符顺序被打乱了
 * <p>
 * 1、最常规的递归法 其实就是深度优先遍历 认准某个方向不断向前尝试 路不通就回溯 直到能到达终点结束或者所有路都尝试了一遍 可以减枝优化
 * <p>
 * 2、动态规划
 * dp[i][j] 表示s1[0-i)与s2[0-j) 是否能符合s3[0-(i+j))
 * 则dp[i][j]如果为true 则他的左边或上边必须有一个为true 且 为true的那个往坐标为i,j移动时 对应的字符必须相等
 * 总结
 * dp[i][j] = (dp[i-1][j] && s1.chartAt(i-1) == s3.chartAt(i + j -1)) || (dp[i][j-1] && s2.chartAt(j-1) == s3.chartAt(i + j - 1))
 * <p>
 * 3、广度优先遍历 BFS
 */
public class IsInterleave {

    public static void main(String[] args) {
        System.out.println(isInterleave3("aba", "baa", "abaaba"));
    }

    /**
     * 解法1 递归 深度优先遍历
     */
    public static boolean isInterleave1(String s1, String s2, String s3) {
        if (s1 == null) {
            s1 = "";
        }
        if (s2 == null) {
            s2 = "";
        }
        if (s3 == null) {
            s3 = "";
        }
        if (!checkParam(s1, s2, s3)) return false;
        return getAns1(s1, 0, s2, 0, s3, 0);
    }

    public static boolean checkParam(String s1, String s2, String s3) {
        if (s1.equals("") && s2.equals("") && s3.equals("")) {
            return true;
        }
        if (s1.equals("") && !s2.equals("")) {
            return s2.equals(s3);
        }
        if (!s1.equals("") && s2.equals("")) {
            return s1.equals(s3);
        }
        if ((s1.length() + s2.length()) != s3.length()) {
            return false;
        }
        return true;
    }


    private static boolean getAns1(String s1, int i1, String s2, int i2, String s3, int i3) {

        if (s1.length() == i1 + 1 && s2.length() == i2 + 1 && s3.length() == i3 + 1) {
            return true;
        }

        //当i1到了末尾
        if (s1.length() == i1) {
            //只移动i2
            for (int i = i2, len = s2.length(), j = i3; i < len; i++, j++) {
                if (s2.charAt(i) != s3.charAt(j)) {
                    return false;
                }
            }
            return true;
        }
        //当i2到了末尾
        if (s2.length() == i2) {
            //只移动i1
            for (int i = i1, len = s1.length(), j = i3; i < len; i++, j++) {
                if (s1.charAt(i) != s3.charAt(j)) {
                    return false;
                }
            }
            return true;
        }

        //尝试判断 i1 和 i3
        if (s1.charAt(i1) == s3.charAt(i3)) {
            if (getAns1(s1, i1 + 1, s2, i2, s3, i3 + 1)) {
                return true;
            }
        }
        if (s2.charAt(i2) == s3.charAt(i3)) {
            //尝试 判断 i2 和 i3
            if (getAns1(s1, i1, s2, i2 + 1, s3, i3 + 1)) {
                return true;
            }
        }
        // i1 和 i2都移动失败
        return false;
    }

    /**
     * 动态规划
     */
    @SuppressWarnings("all")
    public static boolean isInterleave2(String s1, String s2, String s3) {
        if (s1 == null) {
            s1 = "";
        }
        if (s2 == null) {
            s2 = "";
        }
        if (s3 == null) {
            s3 = "";
        }
        if (!checkParam(s1, s2, s3)) return false;
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        for (int i = 0, i1 = s1.length(); i <= i1; i++) {
            for (int j = 0, i2 = s2.length(); j <= i2; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] && (s2.charAt(j - 1) == s3.charAt(i + j - 1));
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] && (s1.charAt(i - 1) == s3.charAt(i + j - 1));
                } else {
                    dp[i][j] = dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }

    /**
     * 广度优先遍历  层序遍历
     */
    public static boolean isInterleave3(String s1, String s2, String s3) {
        if (s1 == null) {
            s1 = "";
        }
        if (s2 == null) {
            s2 = "";
        }
        if (s3 == null) {
            s3 = "";
        }
        if (!checkParam(s1, s2, s3)) return false;
        //是否遍历过的地图标记
        boolean[][] visited = new boolean[s1.length() + 1][s2.length() + 1];
        Queue<Tuple2<Integer, Integer>> queue = new LinkedList<>();
        visited[0][0] = true;
        queue.add(new Tuple2<>(0, 0));
        while (!queue.isEmpty()) {
            Tuple2<Integer, Integer> curr = queue.poll();
            //已经走到了右下角
            if (curr.x == s1.length() && curr.y == s2.length()) {
                return true;
            }

            //尝试 往右走
            int right = curr.x + 1;
            if (right <= s1.length() &&  s1.charAt(right - 1) == s3.charAt(right + curr.y - 1)) {

                if (!visited[right][curr.y]) {
                    //判断这个点是不是以及入过队了
                    Tuple2<Integer, Integer> tuple = new Tuple2<>(right, curr.y);
                    queue.add(tuple);
                    visited[right][curr.y] = true;
                }
            }

            //尝试 往下走
            int down = curr.y + 1;
            if (down <= s2.length() &&  s2.charAt(down - 1) == s3.charAt(curr.x + down - 1)) {

                if (!visited[curr.x][down] ) {
                    //判断这个点是不是以及入过队了
                    Tuple2<Integer, Integer> tuple = new Tuple2<>(curr.x, down);
                    queue.add(tuple);
                    visited[curr.x][down] = true;
                }
            }

        }
        return false;
    }
}