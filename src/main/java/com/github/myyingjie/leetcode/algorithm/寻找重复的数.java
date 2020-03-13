package com.github.myyingjie.leetcode.algorithm;

import com.github.myyingjie.common.Tuple2;

import java.util.concurrent.ConcurrentHashMap;

/**
 * created by Yingjie Zheng at 2020-03-02 11:02
 * <p>
 * 集合 S 包含从1到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个元素复制了成了集合里面的另外一个元素的值，导致集合丢失了一个整数并且有一个元素重复。
 * <p>
 * 给定一个数组 nums 代表了集合 S 发生错误后的结果。你的任务是首先寻找到重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
 * 给定的数组是无序的。
 * 示例 1:
 * <p>
 * 输入: nums = [1,2,2,4]
 * 输出: [2,3]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/set-mismatch
 * <p>
 * <p>
 * 解法：https://mp.weixin.qq.com/s/o3GQ4fXjPkS04Sr9uPH8ZQ
 */
public class 寻找重复的数 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 4};
        System.out.println(findErrorNums(nums));
    }


    public static Tuple2<Integer, Integer> findErrorNums(int[] arr) {
        Tuple2<Integer, Integer> tuple = new Tuple2<>();
        int[] tmp = new int[arr.length];
        for (int value : arr) {
            if (tmp[value - 1] == 0) {
                tmp[value - 1] = -value;
            } else {
                tmp[value - 1] = -tmp[value - 1];
            }
        }

        for (int i = 0; i < tmp.length; i++) {
            int val = tmp[i];
            if (val > 0) {
                tuple.x = i + 1;
            } else if (val == 0) {
                tuple.y = i + 1;
            }
        }


        return tuple;
    }

}
