package com.github.myyingjie.leetcode.thread;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

/**
 *
 * 输入: "OOHHHH"
 * 输出: "HHOHHO"
 * 解释: "HOHHHO", "OHHHHO", "HHOHOH", "HOHHOH", "OHHHOH", "HHOOHH", "HOHOHH" 和 "OHHOHH" 依然都是有效解。
 *
 * 限制条件:
 * 输入字符串的总长将会是 3n, 1 ≤ n ≤ 50；
 * 输入字符串中的 “H” 总数将会是 2n；
 * 输入字符串中的 “O” 总数将会是 n。
 *
 * 1、初始通道都为两个
 * 2、打印O占用两个通道 打印H占用1个通道
 * 3、打印完O释放两个H的通道  打印完H释放一个O的通道
 */
public class H2O {

    public static void main(String[] args) {
        new H2O().h2o("HHHOOHHHHOOH");
    }



    public void h2o(String str) {
        Semaphore semO = new Semaphore(2);
        Semaphore semH = new Semaphore(2);

        char[] chars = str.toCharArray();

        if(chars.length % 3 != 0){
            throw new RuntimeException("The length of the input string is not a multiple of 3");
        }
        Map<Character, Integer> countMap = new HashMap<Character, Integer>();
        for (char c : chars) {
            countMap.merge(c, 1, Integer::sum);
        }
        if(countMap.get('O') == null || countMap.get('H') == null){
            throw new RuntimeException("H or O is empty");
        }
        if(countMap.get('H')/countMap.get('O')!=2 || countMap.get('H')%countMap.get('O') != 0){
            throw new RuntimeException("The number of H is not twice that of O");
        }


        Integer countO = countMap.get('O');
        Integer countH = countMap.get('H');
        ReleaseOxygen releaseOxygen = new ReleaseOxygen(semO,semH,countO);
        ReleaseHydrogen releaseHydrogen = new ReleaseHydrogen(semO,semH,countH);


        new Thread(releaseOxygen).start();
        new Thread(releaseHydrogen).start();

    }


}

@Data
@AllArgsConstructor
class ReleaseHydrogen implements Runnable {

    private Semaphore O;
    private Semaphore H;

    private Integer count;

    public void run() {
        try {
            for (Integer i = 0; i < count; i++) {
                H.acquire(1);
                System.out.println("H");
                O.release(1);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}


@Data
@AllArgsConstructor
class ReleaseOxygen implements Runnable {

    private Semaphore O;
    private Semaphore H;

    private Integer count;


    public void run() {
        try {
            for (Integer i = 0; i < count; i++) {
                O.acquire(2);
                System.out.println("O");
                H.release(2);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
