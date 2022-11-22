package com.github.myyingjie.leetcode.algorithm.sort;

import java.util.Arrays;

/**
 * created by Yingjie Zheng at 2019-09-16 16:10
 * 希尔排序算法
 *
 * 排序算法大总结
 * https://mp.weixin.qq.com/s/teOGQlslb6aP4AQrx7TTzA
 */
public class 希尔排序 {

    public static void main(String[] args) {
        int[] arr = {5, 2, 8, 1, 2, 6,3};
        shellSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void shellSort(int[] arr) {
        int N = arr.length;
        for (int gap = N >> 1; gap > 0; gap = gap >> 1) {
            for (int i = gap; i < N; i++) {
                insertI(arr, gap, i);
            }
        }
        // int gap = arr.length >> 1;
        // while (gap > 0) {
        //     for (int i = gap; i < arr.length; i++) {
        //         insertI(arr, gap, i);
        //     }
        //
        //     gap = gap >> 1;
        // }
    }

    private static void insertI(int[] arr, int gap, int i) {
        int inserted = arr[i];

        //从后往前比较
        for (int j = i - gap; j >= 0; j -= gap) {
            if (inserted < arr[j]) {
                arr[j + gap] = arr[j];
                arr[j] = inserted;
            } else {
                //说明前边的数据都比这个数小，跳出循环
                break;
            }
        }

    }

    public static void insertSort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            //从后往前比较
            int inserted = arr[i];
            for (int j = i - 1; j >= 0; j--) {
                if (inserted < arr[j]) {
                    //后边的元素往后移
                    arr[j + 1] = arr[j];
                    arr[j] = inserted;

                } else {
                    break;
                }
            }

        }

    }

}
