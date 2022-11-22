package com.github.myyingjie.leetcode.algorithm;

/**
 * created by Yingjie Zheng at 2020-03-13 17:23
 */
public class 二分查找 {


    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(binartSearch2(arr, 10));
    }

    public static int binarySearch(int[] arr, int v) {
        // 递归
        // return binarySearch(arr,0, arr.length-1 , v);


        //循环
        int start = 0;
        int end = arr.length-1;
        int mid = arr.length - 1 >> 1;
        while (true){
            if(start == end && arr[start] != v){
                return -1;
            }
            if(arr[mid] == v){
                return mid;
            }else if(arr[mid] > v){
                end = mid -1;
                mid = start + end >> 1;
            }else {
                start = mid + 1;
                mid = start + end >>1;
            }

        }
    }

    public static int binarySearch(int[] arr,int start, int end, int v) {
        if(start == end && arr[start] != v) {
            return -1;
        }
        int mid = start + end >> 1;
        if (arr[mid] == v) {
            return mid;
        }
        if (arr[mid] > v) {
            return binarySearch(arr, start,mid-1, v);
        }

        return binarySearch(arr, mid + 1 ,end,v);
    }


    public static int binartSearch2(int[] arr, int v){
        return binarySearchBase(arr,0,arr.length -1 ,v);
    }

    public static int binarySearchBase(int[] arr, int start, int end, int v){
        if(start == end && arr[start] != v){
            return -1;
        }
        int mid = (start + end) / 2;
        if(arr[mid] == v){
            return mid;
        }else if(arr[mid] > v){
            return binarySearchBase(arr,start,mid - 1, v);
        }else {
            return binarySearchBase(arr,mid + 1,end,v);
        }

    }


}
