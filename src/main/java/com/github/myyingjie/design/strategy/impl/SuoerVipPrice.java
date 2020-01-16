package com.github.myyingjie.design.strategy.impl;


import com.github.myyingjie.design.strategy.CalPriceStrategy;
import com.github.myyingjie.design.strategy.annotation.PriceAnnotation;

/**
 * @Author ZhengYingjie
 * @Date 2018/10/26
 * @Description 超级会员8折优惠
 */
@PriceAnnotation(min = 20000,max = 29999)
public class SuoerVipPrice implements CalPriceStrategy {
    @Override
    public Double calPrice(Double orgPrice) {
        return orgPrice * 0.8;
    }
}
