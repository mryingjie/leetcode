package com.github.myyingjie.leetcode.algorithm;

/**
 * created by Yingjie Zheng at 2019-09-09 14:13
 * 最长回文子串
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 *
 * 解法一、扩展中心
 * 我们知道回文串一定是对称的，所以我们可以每次循环选择一个中心，进行左右扩展，判断左右字符是否相等即可。
 * 由于存在奇数的字符串和偶数的字符串，所以我们需要从一个字符开始扩展，或者从两个字符之间开始扩展，所以总共有 n+n-1 个中心
 * 时间复杂度：O(n²）
 * 空间复杂度：O(1）
 *
 * 解法二、 Manacher's Algorithm 马拉车算法。
 * * 时间复杂度：O(n）
 * * 空间复杂度：O(n）
 * 这里只实现解法一
 */
public class 最长回文子串 {


    public static void main(String[] args) {

        System.out.println(longestPalindrome("abcbc"));
    }


    public static String longestPalindrome(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        int leftIndex = 0;
        int rightIndex = 0;
        int len = 0;
        char[] chars = str.toCharArray();
        int n = (chars.length << 1) - 1; //n个中心点
        for (int i = 0; i < n; i++) {
            int l;
            int r;
            int tmpLen = 0;
            int center = i >> 1;
            int k;

            if (i % 2 == 0) {
                //判断当前以及剩下的所有中心点是不是已经没有可能比当前最长回文子串长
                k = (Math.min(center, chars.length - center - 1) << 1) + 1;
                if (k <= len) {
                    break;
                }
                //当n是偶数的时候 中心点落在在字符数组上
                if (i == 0) {
                    l = 0;
                    r = 0;
                    tmpLen = 1;
                } else if (center == chars.length - 1) {
                    continue;
                } else {
                    tmpLen = 1;
                    l = center - 1;
                    r = center + 1;
                    for (; ; ) {
                        if (chars[l] != chars[r]) {
                            l++;
                            r--;
                            break;
                        }
                        tmpLen += 2;
                        if (l == 0 || r == chars.length - 1) {
                            //循环比较到数组最前或最后一位
                            break;
                        }
                        l--;
                        r++;
                    }

                }

            } else {
                // 当n是奇数中心点落在两个字符之间
                //判断当前以及剩下的所有中心点是不是已经没有可能比当前最长回文子串长
                k = (Math.min(center + 1, chars.length - center) << 1);
                if (k <= len) {
                    System.out.println("break");
                    break;
                }
                l = center;
                r = center + 1;
                for (; ; ) {
                    if (chars[l] != chars[r]) {
                        l++;
                        r--;
                        break;
                    }
                    tmpLen += 2;
                    if (l == 0 || r == chars.length - 1) {
                        break;
                    }
                    l--;
                    r++;
                }
            }
            //判断并保存当前最长的回文子串
            if (tmpLen > len) {
                leftIndex = l;
                rightIndex = r;
                len = tmpLen;
            }
        }


        return str.substring(leftIndex, rightIndex + 1);
    }

}
