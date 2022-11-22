package com.github.myyingjie.leetcode.algorithm.回溯;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * created by Yingjie Zheng at 2022/11/17 14:38
 *
 * 给定⼀个⽆重复元素的数组 candidates 和⼀个⽬标数 target ，找出 candidates 中所有可以使数字和为
 * target 的组合。
 * candidates 中的数字可以⽆限制重复被选取。
 * 说明：
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1：
 * 输⼊：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 *  [7],
 *  [2,2,3]
 * ]
 * 示例 2：
 * 输⼊：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 *  [2,2,2,2],
 *  [2,3,3],
 *  [3,5]
 * ]
 */
public class 组合总和2 {

    public static void main(String[] args) {

        int[] candidates = {2,3,6,7};
        int target = 8;


        List<List<Integer>> result = method2(candidates, target);
        for (List<Integer> integers : result) {
            System.out.println(integers);
        }
    }


    public static void method(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                System.out.println(i + "," + j);
            }
        }

    }


    public static List<List<Integer>> method2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();

        backtracking(candidates, target, 0, result, path,0);
        return result;
    }

    private static void backtracking(int[] candidates, int target, int start, List<List<Integer>> result, List<Integer> path,int sum) {
        if(sum > target){
            return;
        }
        if(sum == target){
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            int candidate = candidates[i];
            path.add(candidate);
            sum += candidate;
            backtracking(candidates,target,i,result,path,sum);
            sum -= candidate;
            path.remove(path.size() - 1);
        }

    }

}
