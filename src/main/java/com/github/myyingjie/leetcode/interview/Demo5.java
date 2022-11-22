package com.github.myyingjie.leetcode.interview;

/**
 * created by Yingjie Zheng at 2021/8/11 上午11:43
 */
public class Demo5 {

     
    public static void main(String[] args) {
        char[] arr = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
        gundong(arr, 4);
        System.out.println(arr);
    }

    // ABCDEFGH 4
    public static char[] gundong(char[] arr, int time) {
        if(arr.length == 1){
            return arr;
        }
        for (int i = 0; i < time - 1; i++) {
            gundong0(arr);
        }
        return arr;
    }

    private static void gundong0(char[] arr) {
        char tmp = arr[0];
        for (int i = 0; i < arr.length - 1; i++) {
            char tmp0 = arr[i + 1];
            arr[i + 1] = tmp;
            tmp = tmp0;
        }

        arr[0] = tmp;
    }
}
