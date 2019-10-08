package com.github.myyingjie.leetcode.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * created by Yingjie Zheng at 2019-09-05 14:09
 * 字符串全排列
 * 123的全排列  1 2 3， 1 3 2， 2 1 3， 2 3 1， 3 1 2 ， 3 2 1
 */
public class FullyArranged {


    public static void main(String[] args) {
        System.out.println(fullyArranged("aba"));
    }

    private static Set<String> fullyArranged(String str){
        Set<String> set = new HashSet<>();
        return fullyArrangedBase(str.toCharArray(), 0,set);
    }

    private static Set<String> fullyArrangedBase(char[] ss,int i,Set<String> set){
        if(ss==null||i<0 ||i>ss.length){//1
            return null;
        }
        if(i==ss.length-1){//2
            set.add(new String(ss));
        }else{
            for(int j=i;j<ss.length;j++){//3
                char temp=ss[j];//交换前缀,使之产生下一个前缀
                ss[j]=ss[i];
                ss[i]=temp;
                fullyArrangedBase(ss,i+1,set);//4
                temp=ss[j]; //将前缀换回来,继续做上一个的前缀排列.//5
                ss[j]=ss[i];
                ss[i]=temp;
            }
        }
        return set;
    }



}
