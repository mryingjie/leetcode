package com.github.myyingjie.leetcode.algorithm.回溯;


import java.util.ArrayList;
import java.util.List;

/**
 * created by Yingjie Zheng at 2019-09-05 15:19
 * 全组合
 * abc-->  a  b  c  ab  ac  bc  abc
 */
public class 字符串全组合 {

    public static void main(String[] args) {

        String s = "abc";

        List<List<Character>> result = fullCombination(s.toCharArray());
        System.out.println(result);
    }

    public static List<List<Character>> fullCombination(char[] characters){
        List<Character> path = new ArrayList<>();
        List<List<Character>> result = new ArrayList<>();
        boolean[] used = new boolean[characters.length];
        baseFullCombination(characters,0,path,result,used);
        return result;

    }

    public static void baseFullCombination(char[] chars,int start,List<Character> path,List<List<Character>> result,  boolean[]  used){
        for (int i = start; i < chars.length; i++) {
            if(used[i]){
                continue;
            }
            path.add(chars[i]);
            used[i]= true;
            result.add(new ArrayList<>(path));
            baseFullCombination(chars,i,path,result,used);
            path.remove(path.size() - 1);
            used[i]= false;
        }

    }
}
