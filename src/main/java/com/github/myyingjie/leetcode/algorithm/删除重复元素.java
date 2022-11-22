package com.github.myyingjie.leetcode.algorithm;

/**
 * created by Yingjie Zheng at 2019-09-16 14:22
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
 * <p>
 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 */
public class 删除重复元素 {

    public static void main(String[] args) {
        int[] arr = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int i = removeDuplicates(arr);
        System.out.println(i);
    }


    public static int removeDuplicates(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int i = 0;
        int j = 0;
        while (j < arr.length) {
            if (arr[i] != arr[j]) {
                arr[++i] = arr[j];
            }
            j++;
        }
        return i + 1;
    }

}
