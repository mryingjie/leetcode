package com.github.myyingjie.leetcode.algorithm.sort;

import java.util.Arrays;

/**
 * created by Yingjie Zheng at 2022/11/14 16:03
 */
public class 选择排序 {


    public static void main(String[] args) {

        int[] arr = {2,4,2,3,15,6,1,1,4,9};

        sort(arr);
        System.out.println(Arrays.toString(arr));

    }
    public static void sort(int[] arr){
        for (int i = 0; i <arr.length - 1; i++) {
            int max = 0;
            for (int j = 0; j < arr.length - i; j++) {
               if(arr[j] > arr[max]){
                   max = j;
               }
            }
            // 交换最后一个元素和max元素的值
            int tmp = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = arr[max];
            arr[max] = tmp;
        }
    }
}
