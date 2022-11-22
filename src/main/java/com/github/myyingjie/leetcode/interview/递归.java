package com.github.myyingjie.leetcode.interview;

/**
 * created by Yingjie Zheng at 2021/8/4 下午5:04
 */
public class 递归 {


    public static void main(String[] args) {

        System.out.println(getN(5));
    }


    private static int getN(int n) {

        if (n <= 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }

        if (n == 3) {
            return 4;
        }
        return getN(n - 1) + getN(n - 2) + getN(n - 3);
    }
}
