package com.github.myyingjie.design.strategy.impl;


import com.github.myyingjie.design.strategy.CalPriceStrategy;
import com.github.myyingjie.design.strategy.annotation.PriceAnnotation;

/**
 * @Author ZhengYingjie
 * @Date 2018/10/26
 * @Description 金牌会员7折优惠
 */
@PriceAnnotation(min = 30000,max = 39999)
public class GoldPrice implements CalPriceStrategy {

    @Override
    public Double calPrice(Double orgPrice) {
        return orgPrice * 0.7;
    }
}
