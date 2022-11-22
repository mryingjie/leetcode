package com.github.myyingjie.leetcode.algorithm;

/**
 * created by Yingjie Zheng at 2020-03-16 16:31
 * 使用字符串实现加法
 */
public class StringSum {


    public static String sum(String str1, String str2) {

        String minStr = str1.length() > str2.length() ? str2 : str1;
        String maxStr = str1.length() > str2.length() ? str1 : str2;

        //定义一个存贮结果StringBuilder
        StringBuilder result = new StringBuilder();

        int minLength = minStr.length();
        //进位
        int carry = 0;
        //当前位上的数值
        int currentNum = 0;
        //循环小的字符串
        int j = maxStr.length() - 1;
        //从后往前加
        for (int i = minLength - 1; i >= 0; i--, j--) {
            //分别获取两个字符对应的字面数值，然后相加，再加上进位
            currentNum = Integer.parseInt(minStr.charAt(i) + "") + Integer.parseInt(maxStr.charAt(j) + "") + carry;
            //获取进位
            carry = currentNum / 10;
            //处理当前位的最终值
            currentNum %= 10;

            result.append(currentNum);

        }
        if (carry > 0) {
            for (; j >= 0; j--) {
                currentNum = Integer.parseInt(maxStr.charAt(j) + "") + carry;
                //获取进位
                carry = currentNum / 10;
                //处理当前位的最终值
                currentNum %= 10;
                result.append(currentNum);
                if (carry == 0) {
                    j--;
                    break;
                }
            }
        }
        if(carry ==0){
            for (; j >= 0; j--) {
                result.append(maxStr.charAt(j));
            }
        }else {
            result.append(carry);
        }
        return result.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(sum("1234", "9234"));
        System.out.println(1234+9234);
    }

}
