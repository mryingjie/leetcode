package com.github.myyingjie.leetcode.algorithm.贪心;

import java.util.Arrays;

/**
 * created by Yingjie Zheng at 2022/11/16 16:23
 * 假设你是⼀位很棒的家⻓，想要给你的孩⼦们⼀些⼩饼⼲。但是，每个孩⼦最多只能给⼀块饼⼲。
 * 对每个孩⼦ i，都有⼀个胃⼝值 g[i]，这是能让孩⼦们满⾜胃⼝的饼⼲的最⼩尺⼨；并且每块饼⼲ j，都
 * 有⼀个尺⼨ s[j] 。如果 s[j] >= g[i]，我们可以将这个饼⼲ j 分配给孩⼦ i ，这个孩⼦会得到满⾜。你的⽬
 * 标是尽可能满⾜越多数量的孩⼦，并输出这个最⼤数值。
 *
 * 示例 1:
 * 输⼊: g = [1,2,3], s = [1,1]
 * 输出: 1
 * 解释:
 * 你有三个孩⼦和两块⼩饼⼲，3个孩⼦的胃⼝值分别是：1,2,3。
 * 虽然你有两块⼩饼⼲，由于他们的尺⼨都是1，你只能让胃⼝值是1的孩⼦满⾜。
 * 所以你应该输出1。
 * 示例 2:
 * 输⼊: g = [1,2], s = [1,2,3]
 * 输出: 2
 * 解释:
 * 你有两个孩⼦和三块⼩饼⼲，2个孩⼦的胃⼝值分别是1,2。
 * 你拥有的饼⼲数量和尺⼨都⾜以让所有孩⼦满⾜。
 * 所以你应该输出2.
 *
 *
 * 大的饼干优先给大的
 */
public class 发饼干 {

    public static void main(String[] args) {

        int[] childs = {1,2,5,5,5};
        int[] cookies = {2,4,5,1,4};

        System.out.println(distribution(childs,cookies));

    }

    private static int distribution(int[] childs, int[] cookies) {

        // 排序
        Arrays.sort(childs);
        Arrays.sort(cookies);
        int i = cookies.length - 1;
        int j = childs.length -1;
        int result = 0;
        while (i >= 0 && j >= 0){
            int cookie = cookies[i];
            int child = childs[j];
            if(cookie >= child){
                result ++;
                i --;
                j --;
            }else {
                j --;
            }
        }

        return result;
    }


}
