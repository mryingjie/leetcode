package com.github.myyingjie.leetcode.algorithm;

import com.github.myyingjie.common.Tuple3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * created by Yingjie Zheng at 2019-09-10 10:40
 * <p>
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 指定的数 ？
 * 找出所有满足条件且不重复的三元组。
 */
public class ThreeSum {

    public static void main(String[] args) {
        int[] arr = {-1, 0, 1, 2, -1, -4,5};
        System.out.println(threeSum(arr, 1));
    }

    public static List<Tuple3<Integer, Integer, Integer>> threeSum(int[] arr, int sum) {
        List<Tuple3<Integer, Integer, Integer>> list = new ArrayList<>();
        if (arr == null || arr.length < 3) {
            return list;
        }

        //第一步对数组进行排序
        Arrays.sort(arr);

        int i = 0;
        int l;
        int right = arr.length - 1;
        int r;
        while (i <= arr.length - 3) {
            //假设三元组也是有序的  先固定最左边的数  后边两个数一定大于等于这个数
            //固定最左边的数
            if (arr[i] > sum) {
                break;
            }
            if(i > 0){
                if (arr[i] == arr[i-1]) {
                    i++;
                    //防止重复
                    continue;
                }
            }

            for (l = i + 1, r = right; l < r; ) {
                int tmpSum = arr[i] + arr[l] + arr[r];
                if (tmpSum == sum) {
                    list.add(new Tuple3<>(arr[i], arr[l], arr[r]));
                    if (arr[l] == arr[l + 1]) {
                        //防止重复
                        l++;
                    }
                    if (arr[r] == arr[r-1]) {
                        r--;
                    }
                    l++;

                } else if (tmpSum > sum) {
                    r--;
                } else {
                    l++;
                }
            }


            i++;
        }


        return list;
    }


}


