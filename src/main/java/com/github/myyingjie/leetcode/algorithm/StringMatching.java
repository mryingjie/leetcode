package com.github.myyingjie.leetcode.algorithm;

import java.util.stream.IntStream;

/**
 * created by Yingjie Zheng at 2020-02-17 10:11
 * 字符串匹配算法
 * 1、BF暴力算法  依次循环比较无用功很多 性能较差
 * 2、RK算法  先比较hashcode值如果相等再依次比较  性能不稳定
 * 3、BM算法  坏字符规则和好后缀规则  实现十分复杂
 * 4、KMP算法
 */
public class StringMatching {

    public static void main(String[] args) {

        String str1 = "shucefdscef";
        String str2 = "cef";
        System.out.println(match(str1, str2));
    }

    public static int match(String master, String pattern) {
        if (master == null || pattern == null || master.length() < pattern.length()) {
            return -1;
        }
        return RK(master, pattern);
    }

    private static int RK(String master, String pattern) {
        int patternHashcode = computeHashcode(pattern);
        int patternLen = pattern.length();
        int masterLen = master.length();

        String substring = master.substring(0, patternLen);
        int subHash = computeHashcode(substring);
        for (int i = 0; i <= masterLen - patternLen; i++) {
            if (patternHashcode == subHash) {
                if (checkChars(pattern, substring))
                    return i;
            }
            substring = master.substring(i+1, i + patternLen+1);
            subHash = subHash - master.charAt(i) + master.charAt(i + patternLen);
        }
        return -1;
    }

    private static int computeHashcode(String str) {
        char[] chars = str.toCharArray();
        int hash = 0;
        for (char aChar : chars) {
            hash += aChar;
        }
        return hash;
    }


    private static boolean checkChars(String str1, String str2) {
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        boolean flag = true;
        for (int i = 0, len = chars1.length; i < len; i++) {
            flag = chars1[i] == chars2[i];
            if (!flag)
                break;
        }
        return flag;
    }

}
