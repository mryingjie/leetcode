package com.github.myyingjie.leetcode.algorithm.回溯;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * created by Yingjie Zheng at 2022/11/17 14:15
 * 给定⼀个仅包含数字 2-9 的字符串，返回所有它能表示的字⺟组合。
 * 给出数字到字⺟的映射如下（与电话按键相同）。注意 1 不对应任何字⺟。
 *
 * 示例:
 * 输⼊："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 说明：尽管上⾯的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 *
 *
 * letterMap[10] = {
 *  "", // 0
 *  "", // 1
 *  "abc", // 2
 *  "def", // 3
 *  "ghi", // 4
 *  "jkl", // 5
 *  "mno", // 6
 *  "pqrs", // 7
 *  "tuv", // 8
 *  "wxyz", // 9
 * };
 */
public class 电话号码的字母组合 {
    static List<String> baseList = Lists.newArrayList(
            "", // 0
            "", // 1
            "abc", // 2
            "def", // 3
            "ghi", // 4
            "jkl", // 5
            "mno", // 6
            "pqrs", // 7
            "tuv", // 8
            "wxyz" // 9
    );
    public static void main(String[] args) {
        List<List<String>> result = method2("12131");
        for (List<String> integers : result) {
            System.out.println(integers);
        }

    }


    public static List<List<String>> method2(String input) {
        List<List<String>> result = new ArrayList<>();
        LinkedList<String> path = new LinkedList<>();
        // 统计坏点的数量
        int badPoint = 0;
        for (int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == '0' || input.charAt(i) == '1'){
                badPoint ++;
            }
        }
        backtracking(input, 0, result, path,badPoint);
        return result;
    }

    private static void backtracking(String input, int start, List<List<String>> result, List<String> path,int badPoint) {
        if(path.size() == input.length() - badPoint){
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = start;  i < input.length(); i++) {
            char c = input.charAt(i);
            int index = Integer.parseInt(c + "");
            String allWords = baseList.get(index);
            for (int j = 0; j < allWords.length(); j++) {
                path.add(allWords.charAt(j) + "");
                backtracking(input, i + 1, result, path,badPoint);
                path.remove(path.size() - 1);
            }

        }
    }


}
