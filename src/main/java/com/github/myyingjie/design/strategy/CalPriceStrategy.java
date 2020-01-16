package com.github.myyingjie.design.strategy;

/**
 * @Author ZhengYingjie
 * @Date 2018/10/26
 * @Description 计算价格的策略接口
 */
public interface CalPriceStrategy {

    /**
     *  根据原价获取一个最终的价格
     */
    Double calPrice(Double orgPrice);
}
