package com.github.myyingjie.leetcode.algorithm.回溯;

import java.util.List;

/**
 * created by Yingjie Zheng at 2022/11/17 16:25
 * 给定⼀组不含重复元素的整数数组 nums，返回该数组所有可能的⼦集（幂集）。
 * 说明：解集不能包含重复的⼦集。
 * 示例:
 * 输⼊: nums = [1,2,3]
 * 输出:
 * [
 *  [3],
 *  [1],
 *  [2],
 *  [1,2,3],
 *  [1,3],
 *  [2,3],
 *  [1,2],
 *  []
 * ]
 */
public class 子集 {

    public static void main(String[] args) {
        int[] input = {1,2,3};
        List<List<Integer>> result = method(input);
        for (List<Integer> integers : result) {
            System.out.println(integers);
        }
    }

    private static List<List<Integer>> method(int[] input) {

        return null;
    }


}
