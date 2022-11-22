package com.github.myyingjie.leetcode.algorithm.回溯;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


/**
 * created by Yingjie Zheng at 2022/11/17 14:52
 *
 * 给定⼀个 没有重复 数字的序列，返回其所有可能的全排列。
 * 示例:
 * 输⼊: [1,2,3]
 * 输出:
 * [
 *  [1,2,3],
 *  [1,3,2],
 *  [2,1,3],
 *  [2,3,1],
 *  [3,1,2],
 *  [3,2,1]
 * ]
 *
 *
 * 如果有重复 对结果去重
 */
public class 全排列 {

    public static void main(String[] args) {

        int[] input = {2,2,3};

        List<List<Integer>> result = array(input);
        List<List<Integer>> collect = result.stream().distinct().collect(Collectors.toList());
        for (List<Integer> integers : collect) {
            System.out.println(integers);
        }
    }

    private static List<List<Integer>> array(int[] input) {

        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[input.length];

        List<Integer> path = new ArrayList<>();
        backtracking(input,0,used,result,path);
        return result;
    }

    private static void backtracking(int[] input, int i, boolean[] used, List<List<Integer>> result,List<Integer> path) {
        if(path.size() == input.length){
            result.add(new ArrayList<>(path));
            return;
        }
        for (int j = i; j < input.length; j++) {
            if(used[j]){
                continue;
            }
            int tmp = input[j];
            path.add(tmp);
            used[j] = true;
            backtracking(input,i,used,result,path);
            path.remove(path.size() -1);
            used[j] = false;
        }


    }

}
