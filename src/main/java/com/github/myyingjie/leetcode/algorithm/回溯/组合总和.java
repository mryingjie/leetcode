package com.github.myyingjie.leetcode.algorithm.回溯;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * created by Yingjie Zheng at 2022/11/17 11:43
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复
 * 的数字。
 * 说明：
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 * 输⼊: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 示例 2:
 * 输⼊: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 */
public class 组合总和 {

    public static void main(String[] args) {
        List<List<Integer>> result = method2(9, 3);
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


    public static List<List<Integer>> method2(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();

        backtracking(n, k, 1, result, path,0);
        return result;
    }

    private static void backtracking(int n, int k, int start, List<List<Integer>> result, List<Integer> path,int sum) {
        if(sum > n){
            return;
        }
        if(path.size() == k && sum == n){
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i <= n; i++) {
            path.add(i);
            sum += i;
            backtracking(n,k,i + 1,result,path,sum);
            sum -= i;
            path.remove(path.size() - 1);
        }

    }

}
