package com.github.myyingjie.design.strategy.impl;


import com.github.myyingjie.design.strategy.CalPriceStrategy;
import com.github.myyingjie.design.strategy.annotation.PriceAnnotation;

/**
 * @Author ZhengYingjie
 * @Date 2018/10/26
 * @Description 普通会员九折优惠
 */
@PriceAnnotation(min = 10000,max = 19999)
public class VipPrice implements CalPriceStrategy {
    @Override
    public Double calPrice(Double orgPrice) {
        return orgPrice * 0.9;
    }
}
