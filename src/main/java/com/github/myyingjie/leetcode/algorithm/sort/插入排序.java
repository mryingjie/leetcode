package com.github.myyingjie.leetcode.algorithm.sort;

import java.util.Arrays;

/**
 * created by Yingjie Zheng at 2022/11/14 16:13
 */
public class 插入排序 {

    public static void main(String[] args) {

        int[] arr = {2,4,1,5};

        sort(arr);
        System.out.println(Arrays.toString(arr));

    }
    public static void sort(int[] arr){
        for (int i = 1; i <arr.length; i++) {
            int tmp0 = arr[i];
            for (int j = i - 1; ; j--){
                if(j < 0){
                    arr[0] = tmp0;
                    break;
                }
                if(arr[j] >= tmp0){
                    arr[j + 1] = arr[j];
                } else {
                    arr[j+1] = tmp0;
                    break;
                }
            }
        }
    }

}
