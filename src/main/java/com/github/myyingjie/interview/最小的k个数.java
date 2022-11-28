package com.github.myyingjie.interview;

import java.util.*;

/**
 * created by Yingjie Zheng at 2022/11/25 18:18
 * 给定一个长度为 n 的可能有重复值的数组，找出其中不去重的最小的 k 个数。例如数组元素是4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4(任意顺序皆可)。
 * 数据范围：0\le k,n \le 100000≤k,n≤10000，数组中每个数的大小0 \le val \le 10000≤val≤1000
 * 要求：空间复杂度 O(n)O(n) ，时间复杂度 O(nlogk)O(nlogk)
 */
public class 最小的k个数 {

    public static void main(String[] args) {
        int[] input = {4,5,1,6,2,7,3,8,1};
        ArrayList<Integer> integers = GetLeastNumbers_Solution(input, 4);
        System.out.println(integers);


    }

    public static ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> tmp = new ArrayList<>(k);
        for(int i = 0; i < input.length ; i ++){
            int v = input[i];
            if(tmp.size() < k){
                tmp.add(v);
            }else {
                Collections.sort(tmp);
                Integer max = tmp.get(tmp.size() - 1);
                if(v < max){
                    tmp.remove(tmp.size() - 1);
                    tmp.add(v);
                }
            }
        }

        return tmp;
    }

}
