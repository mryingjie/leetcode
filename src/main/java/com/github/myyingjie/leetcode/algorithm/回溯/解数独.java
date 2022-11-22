package com.github.myyingjie.leetcode.algorithm.回溯;

/**
 * created by Yingjie Zheng at 2022/11/17 15:44
 */
public class 解数独 {



    boolean backtracking(int[][] board,int[][] result) {
        for (int i = 0; i < board.length; i++) { // 遍历⾏
            for (int j = 0; j < board[0].length; j++) { // 遍历列
                if (board[i][j] != 0) continue;
                for (char k = 1; k <= 9; k++) { // (i, j) 这个位置放k是否合适
                    if (isValid(i, j, k, board)) {
                        board[i][j] = k; // 放置k
                        result[i][j] = k;
                        if (backtracking(board,result)) return true;
                        board[i][j] = 0; // 回溯，撤销k
                        result[i][k] = 0;
                    }
                }
                return false; // 9个数都试完了，都不⾏，那么
            }
        }
        return true; // 遍历完没有返回false，说明找到了合适棋盘位置了
    }
    private boolean isValid(int i, int j, char k, int[][] board) {
        for (int m = 0; m < board.length; m++) {
            int[] line = board[m];
            if(m == i){
                for (int v : line) {
                    if(v == k){
                        return false;
                    }
                }
            }
            int v = line[j];
            if(v == k){
                return false;
            }

        }
        return true;
    }
}
