package com.github.myyingjie.leetcode.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * created by Yingjie Zheng at 2019-09-05 15:19
 * 全组合
 * abc-->  a  b  c  ab  ac  bc  abc
 */
public class 字符串全组合 {

    public static void main(String[] args) {
        System.out.println(fullCombination("aba"));
    }


    private static Set<String> fullCombination(String str) {
        Set<String> set = new HashSet<>();
        fullCombination(str.toCharArray(), set);
        return set;
    }

    private static Set<String> fullCombination(char[] chars, Set<String> set) {
        int combNum = 1 << chars.length;//组合的个数有2^n的长度
        int k;
        for (int i = 0; i < combNum; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < chars.length; j++) {
                k = 1 << j;
                if ((k & i) != 0) {//按位与运算，如果结果为1就输出当前位，结果为0不输出
                    sb.append(chars[j]);
                }
            }
            String s = sb.toString();
            if (s.length() != 0) {
                set.add(sb.toString());
            }
        }

        return set;
    }
}
