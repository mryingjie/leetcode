package com.github.myyingjie.leetcode.algorithm;

/**
 * created by Yingjie Zheng at 2019-09-16 14:49
 * 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
 * 给定 nums = [0,1,2,2,3,0,4,2], val = 2,
 * 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
 */
public class RemoveElement {

    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 2, 3, 0, 4, 2};
        int i = removeElement(arr, 2);
        System.out.println(i);
    }

    public static int removeElement(int[] arr, int element) {
        int ans = arr.length;
        int i = 0;

        while (i < ans){
            if (arr[i] == element) {
                arr[i] = arr[--ans];
            } else {
                i++;
            }
        }
        return ans;
    }

}
