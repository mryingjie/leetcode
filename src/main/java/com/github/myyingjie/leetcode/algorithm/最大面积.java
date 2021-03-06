package com.github.myyingjie.leetcode.algorithm;

/**
 * created by Yingjie Zheng at 2019-09-10 09:26
 *
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，
 * 使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 *
 *
 */
public class 最大面积 {

    public static void main(String[] args) {
        int[] arr = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(arr));
    }

    public static int maxArea(int[] arr){
        int left = 0;
        int right = arr.length -1;

        int maxArea = 0;
        while (left <= right){
            if(arr[left] >= arr[right]) {
                maxArea = Math.max((right - left) * arr[right],maxArea);
                right --;
            } else{
                maxArea = Math.max((right - left) * arr[left],maxArea);
                left ++;
            }
        }
        return maxArea;
    }


}
