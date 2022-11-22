package com.github.myyingjie.leetcode.algorithm.sort;

import java.util.Arrays;

/**
 * created by Yingjie Zheng at 2022/11/14 15:52
 */
public class 冒泡排序 {


    public static void main(String[] args) {

        int[] arr = {2,4,2,3,15,6,1,1,4,9};

        sort(arr);
        System.out.println(Arrays.toString(arr));

    }

    public static void sort(int[] arr){
        for (int i = 0; i <arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if(arr[j] > arr[j + 1]){
                    // 交换
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }




}
