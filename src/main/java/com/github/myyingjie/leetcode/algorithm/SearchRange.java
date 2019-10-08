package com.github.myyingjie.leetcode.algorithm;

import com.github.myyingjie.common.Tuple2;

/**
 * created by Yingjie Zheng at 2019-09-16 11:24
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置
 * <p>
 * 第一种方法：直接遍历，时间复杂度为O(n)
 * <p>
 * 第二种方法二分法  时间复杂度为O(log n)
 */
public class SearchRange {

    public static void main(String[] args) {
        int[] arr = {5,7,7,7,8,8,10};
        System.out.println(searchRange(arr, 7));
    }


    public static Tuple2<Integer, Integer> searchRange(int[] arr, int target) {
        Tuple2<Integer, Integer> tuple2;
        if (arr == null || arr.length == 0) {
            tuple2 = new Tuple2<>(-1, -1);
        } else {
            //二分法查找
            int l = 0;
            int r = arr.length -1;
            int mid = 0;
            boolean flag = false;
            while (l <= r){
                mid = (l + r) >> 1;
                if(arr[mid] == target){
                    flag = true;
                    break;
                }else if(arr[mid] < target){
                    l = mid + 1;
                }else {
                    r = mid - 1;
                }
            }
            if(flag){
                int lIndex = mid;
                int rIndex = mid;
                for(;lIndex >=0 && arr[lIndex] == target; lIndex --){}
                for(;rIndex <= arr.length -1 &&  arr[rIndex] == target; rIndex ++){}
                tuple2 = new Tuple2<>(lIndex + 1,rIndex - 1);
            }else {
                tuple2 = new Tuple2<>(-1, -1);
            }
        }

        return tuple2;
    }

}
