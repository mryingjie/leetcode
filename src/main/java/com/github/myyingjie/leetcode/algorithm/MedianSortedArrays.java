package com.github.myyingjie.leetcode.algorithm;

/**
 * created by Yingjie Zheng at 2019-09-05 17:00
 * 寻找两个有序数组的中位数
 * <p>
 * 使用二分法
 */
public class MedianSortedArrays {

    public static void main(String[] args) {
        int[] arr1 = {1};
        int[] arr2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        System.out.println(findMedianSortedArrays(arr1, arr2));
    }

    public static double findMedianSortedArrays(int[] arr1, int[] arr2) {
        int len1 = arr1.length;
        int len2 = arr2.length;
        boolean isEven = false;
        int k = (len1 + len2 + 1) >> 1; //第几 k小的数字  因为除以2是向下取整 +1是为了使奇数个数字的时候 偶数+1不影响
        if ((len1 + len2) % 2 == 0) {
            isEven = true;
        }


        int i = findMedianSortedArrays(arr1, arr2, k, 0, 0);

        //总长度为偶数 中位数为第 k 和 k+1小的数的平均数
        if (isEven) {
            return (i + findMedianSortedArrays(arr1, arr2, k + 1, 0, 0)) / 2.0;
        }

        return i;
    }

    /**
     * @param arr1   数组1
     * @param arr2   数组2
     * @param k      第几小的数
     * @param start1 arr1的起始索引
     * @param start2 arr2的起始索引
     * @return 第k小的数
     */
    public static int findMedianSortedArrays(int[] arr1, int[] arr2, int k, int start1, int start2) {

        int len1 = arr1.length;
        int len2 = arr2.length;

        if (k == 1) {
            //判断某个数组是否被舍弃完了
            if (len1 <= start1) {
                return arr2[start2];
            }
            if (len2 <= start2) {
                return arr1[start1];
            }
            //两个都没被舍弃完就返回第一个较小的那个数
            return Math.min(arr1[start1], arr2[start2]);
        }

        //如果其中有一个数组被舍弃完了 他的长度刚好就等于他的起始索引  就返回另一个数组的第k小的数
        if (len1 == start1) {
            return arr2[k + start2 - 1];
        }
        if (len2 == start2) {
            return arr1[k + start1 - 1];
        }

        int i = k >> 1; //每次比较两个数据第i小的的数字的大小  并弃掉较小的那个数以及对应数组中比它小的所有数
        int i1 = Math.min(len1, i + start1) - 1;
        int i2 = Math.min(len2, i + start2) - 1;

        if (arr1[i1] < arr2[i2]) {
            return findMedianSortedArrays(arr1, arr2, k - (i1 - start1) - 1, i1 + 1, start2);
        } else {
            return findMedianSortedArrays(arr1, arr2, k - (i2 - start2) - 1, start1, i2 + 1);
        }
    }
}
