package com.github.myyingjie.leetcode.algorithm;

import java.util.*;

/**
 * created by Yingjie Zheng at 2020-02-07 17:30
 * <p>
 * 水塘抽样算法
 * <p>
 * 一组数例如不知道长度的链表  实现一个随机取出一个数的算法要求时间复杂度为O(n) 每个数被选取的概率都一样
 * 思路:假设选取k个数
 * 例如长度为n的数组每个数被随机选取的概率都为k/n
 * 则当遇到第i个数时  它被选取的概率都是k/i  保持原有选择的概率是1-k/i
 *
 */
public class 水塘抽样算法 {

    public static void main(String[] args) {
        Iterator<Integer> iterator = Arrays
                .asList(2, 43, 23, 42, 5, 6, 76, 80, 39)
                .iterator();

        System.out.println(getRandom(iterator, 0));
    }




    public static int getRandom(Iterator<Integer> arr) {
        return getRandom(arr, 1).get(0);
    }

    /**
     * 水塘抽样算法
     *
     * @param arr 原始数据
     * @param k   随机返回几个数
     */
    public static List<Integer> getRandom(Iterator<Integer> arr, int k) {
        Random r = new Random();
        List<Integer> res = new ArrayList<>(k);
        if (k == 0) {
            return res;
        }
        int i;
        Integer currentNode;
        if (arr.hasNext()) {
            currentNode = arr.next();
        } else {
            return res;
        }
        // 将前k个数放入结果
        for (int j = 0; j < k; j++) {
            res.add(currentNode);
            if (arr.hasNext()) {
                currentNode = arr.next();
            } else {
                return res;
            }
        }
        i = k;
        while (true) {
            //生成一个[0,i)的随机数 这个随机数小于k的概率就是k/i
            int j = r.nextInt(++i);
            if (j < k) {
                res.add(j, currentNode);
            }
            if (arr.hasNext()) {
                currentNode = arr.next();
            } else {
                break;
            }
        }


        return res;
    }

}
