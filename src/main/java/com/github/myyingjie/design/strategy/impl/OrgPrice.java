package com.github.myyingjie.design.strategy.impl;


import com.github.myyingjie.design.strategy.CalPriceStrategy;
import com.github.myyingjie.design.strategy.annotation.PriceAnnotation;

/**
 * @Author ZhengYingjie
 * @Date 2018/10/26
 * @Description 原价返回
 */
@PriceAnnotation(max = 9999)
public class OrgPrice implements CalPriceStrategy {
    /**
     * @param orgPrice
     * @return calPrice 原价返回
     */
    @Override
    public Double calPrice(Double orgPrice) {
        return orgPrice;
    }
}
