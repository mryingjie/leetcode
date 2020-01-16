package com.github.myyingjie.design.strategy;

/**
 * @Author ZhengYingjie
 * @Date 2018/10/26
 * @Description
 */
public class Client {

    public static void main(String[] args) {
        Player player = new Player();

        player.buyGood(1000.0);
        System.out.println("玩家要付的钱:"+player.calLastPrice());

        player.buyGood(10000.0);
        System.out.println("玩家要付的钱:"+player.calLastPrice());
        player.buyGood(10000.0);
        System.out.println("玩家要付的钱:"+player.calLastPrice());
        player.buyGood(10000.0);
        System.out.println("玩家要付的钱:"+player.calLastPrice());
        player.buyGood(10000.0);
        System.out.println("玩家要付的钱:"+player.calLastPrice());
    }
}
