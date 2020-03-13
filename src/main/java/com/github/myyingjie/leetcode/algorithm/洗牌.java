package com.github.myyingjie.leetcode.algorithm;

import java.util.Arrays;
import java.util.Random;

/**
 * created by Yingjie Zheng at 2020-02-07 18:25
 *
 * 洗牌 将数据随机打乱
 * 洗牌算法正确性的准则：产生的结果必须有 n! 种可能，否则就是错误的。
 * 因为一个长度为 n 的数组的全排列就有 n! 种，也就是说打乱结果总共有 n! 种。
 * 算法必须能够反映这个事实
 *
 * 例如以下代码中假设长度为5 k=4
 * 则
 * 第一次循环 k - i + 1 = 4 - 0 + 1 = 5
 * 第二次循环 k - i + 1 = 4 - 1 + 1 = 4
 * 第三次循环 k - i + 1 = 4 - 2 + 1 = 3
 * 第四次循环 k - i + 1 = 4 - 3 + 1 = 2
 * 第五次循环 k - i + 1 = 4 - 4 + 1 = 1
 * 总的可能就是 5 * 4 * 3 * 2 * 1
 * 最后的一次循环可省略 即 5 * 4 * 3 * 2
 *
 */
public class 洗牌 {

    public static void main(String[] args) {
        Integer[] ints = {1, 2, 3, 3, 5, 2};
        shuffle(ints);
        System.out.println(Arrays.asList(ints));
    }

    /**
     * 洗牌
     */
    public static void shuffle(Integer[] arr) {
        int k = arr.length - 1;
        Random r = new Random();
        for (int i = 0; i < k; i++) {
            //从[i,k]的闭区间里随机选取一个数 每次都有 k - i + 1 种可能
            int j = randomInt(i, k, r);
            swap(arr, i, j);
        }
    }

    /**
     * 从[i,k]的闭区间里随机选取一个数
     */
    public static int randomInt(int min, int max, Random r) {
        if (max < min) {
            int tmp = min;
            min = max;
            max = tmp;
        }
        return r.nextInt(max - min + 1) + min;
    }

    /**
     * 交换数组中 i 和 j索引的值的位置
     */
    public static void swap(Integer[] arr, int i, int j) {
        int n = arr[i];
        arr[i] = arr[j];
        arr[j] = n;
    }

}
