package com.github.myyingjie.leetcode.algorithm.sort;

import java.util.Arrays;

/**
 * created by Yingjie Zheng at 2022/11/14 19:42
 */
public class 快速排序 {


    public static void main(String[] args) {

        int[] arr = {2,4,1,2,2,2,5};

        sort(arr);
        System.out.println(Arrays.toString(arr));

    }
    public static void sort(int[] arr){
        if(arr.length == 1){
            return;
        }
        fastSort(arr,0,arr.length -1);
    }


    public static void fastSort(int[] arr,int start,int end){
        if(start >= end){
            return;
        }

        int partition = partition(arr, start, end);

        fastSort(arr,start,partition - 1);

        fastSort(arr,partition + 1,end);

    }
    // 分治 返回基准元素位置
    public static int partition(int[] arr,int start,int end){
        int pivot = start;
        while (start < end){
            while (start < end && arr[pivot] < arr[end]){
                end --;
            }
            while (start < end && arr[pivot] >= arr[start]){
                start ++;
            }



            // 交换
            int tmp = arr[start];
            arr[start] = arr[end];
            arr[end] = tmp;
        }

        int tmp = arr[pivot];
        arr[pivot] = arr[start];
        arr[start] = tmp;

        return start;
    }
}
