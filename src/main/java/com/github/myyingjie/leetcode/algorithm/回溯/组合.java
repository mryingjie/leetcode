package com.github.myyingjie.leetcode.algorithm.回溯;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * created by Yingjie Zheng at 2022/11/16 17:28
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * 示例:
 * 输⼊: n = 4, k = 2
 * 输出:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 *
 * 模板
 * void backtracking(参数) {
 *  if (终⽌条件) {
 *    存放结果;
 *    return;
 *  }
 *  for (选择：本层集合中元素（树中节点孩⼦的数量就是集合的⼤⼩）) {
 *    处理节点;
 *    backtracking(路径，选择列表); // 递归
 *    回溯，撤销处理结果
 *  }
 * }
 */
public class 组合 {

    public static void main(String[] args) {
        List<List<Integer>> result = method2(4, 2);
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

        backtracking(n, k, 1, result, path);
        return result;
    }

    private static void backtracking(int n, int k, int start, List<List<Integer>> result, List<Integer> path) {
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = start;  i <= n - (k - path.size()) + 1; i++) {
            System.out.println("1");
            path.add(i);
            backtracking(n, k, i + 1, result, path);
            path.remove(path.size() - 1);
        }
    }


}
