package com.github.myyingjie.design.strategy.impl;


import com.github.myyingjie.design.strategy.CalPriceStrategy;
import com.github.myyingjie.design.strategy.annotation.PriceAnnotation;

/**
 * @Author ZhengYingjie
 * @Date 2018/10/29
 * @Description
 */
@PriceAnnotation(min = 40000)
public class testPrice implements CalPriceStrategy {
    @Override
    public Double calPrice(Double orgPrice) {
        return orgPrice * 0.1;
    }
}
