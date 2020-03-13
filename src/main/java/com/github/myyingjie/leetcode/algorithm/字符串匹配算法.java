package com.github.myyingjie.leetcode.algorithm;


/**
 * created by Yingjie Zheng at 2020-02-17 10:11
 * 字符串匹配算法
 * 1、BF暴力算法  依次循环比较无用功很多 性能较差  但是在字符串较短的情况下性能甚至比下边的算法更好比如String的indexOf()
 * 2、RK算法  先比较hashcode值如果相等再依次比较  性能不稳定
 * 3、BM算法  坏字符规则和好后缀规则  实现十分复杂
 * 4、KMP算法  https://mp.weixin.qq.com/s/3gYbmAAFh08BQmT-9quItQ
 * 假设现在文本串S匹配到 i 位置，模式串P匹配到 j 位置
 * 如果j = 0，或者当前字符匹配成功（即S[i] == P[j]），都令i++，j++，继续匹配下一个字符；
 * 如果j != 0，且当前字符匹配失败（即S[i] != P[j]），则令 i 不变，j = next[j]。此举意味着失配时，模式串P相对于文本串S向右移动了j - next [j] 位。
 * 换言之，当匹配失败时，模式串向右移动的位数为：失配字符所在位置 - 失配字符对应的next 值（next 数组的求解会在下文的3.3.3节中详细阐述），即移动的实际位数为：j - next[j]，且此值大于等于1。
 */
public class 字符串匹配算法 {

    public static void main(String[] args) {

        String str1 = "BACABABCCBC";
        String str2 = "BC";
        int i = str1.indexOf(str2);
        System.out.println(i);
        System.out.println(match(str1, str2));
    }

    public static int match(String master, String pattern) {
        if (master == null || pattern == null || master.length() < pattern.length()) {
            return -1;
        }
        // return KMP(master, pattern);
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
            substring = master.substring(i + 1, i + patternLen + 1);
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


    private static int KMP(String master, String pattern) {
        //计算next数组
        int[] next = computeNext(pattern);
        char[] chars = master.toCharArray();
        int j = 0;
        for (int i = 0,len = chars.length; i < len; i++) {

            while (j > 0 && chars[i] != pattern.charAt(j)){
                //遇到坏字符 查询next数组并更换模式串的起点
                 j = next[j];
            }
            if(chars[i] == pattern.charAt(j)){
                j ++ ;
            }
            if(j == pattern.length()){
                //匹配成功 返回下标
                return i - pattern.length() + 1;
            }
        }
        return -1;
    }


    private static int[] computeNext(String pattern) {
        int[] next = new int[pattern.length()];
        //next[0] = next[1] = 0;
        int j = 0;
        for (int i = 2; i < pattern.length(); i++) {
            while (j != 0 && pattern.charAt(j) != pattern.charAt(i - 1)) {
                //从next[i+1]的求解回溯到 next[j]
                j = next[j];
            }
            if (pattern.charAt(j) == pattern.charAt(i - 1)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

}
