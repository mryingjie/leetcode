package com.github.myyingjie.interview;

import java.util.Arrays;
import java.util.Scanner;

/**
 * created by Yingjie Zheng at 2022/12/2 15:41
 */
public class 选择战力最接近的两个战士 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int total = Integer.parseInt(sc.nextLine());
        String n = sc.nextLine();
        String[] s = n.split(" ");
        int[] input = new int[s.length];
        for (int i = 0; i < input.length; i++) {
            input[i] = Integer.parseInt(s[i]);
        }
        Arrays.sort(input);
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < total - 1; i++) {
            ans = Math.min(ans,Math.abs(input[i] - input[i + 1]));
            if(ans == 0){
                break ;
            }
        }

        System.out.println(ans);
    }

}
