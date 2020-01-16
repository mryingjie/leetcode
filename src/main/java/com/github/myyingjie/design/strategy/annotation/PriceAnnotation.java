package com.github.myyingjie.design.strategy.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author ZhengYingjie
 * @Date 2018/10/29
 * @Description 有效价格的区间,给策略添加有效区间的设置
 */
@Target(ElementType.TYPE)//只能给类添加的注解
@Retention(RetentionPolicy.RUNTIME)
public @interface PriceAnnotation {

    int max() default Integer.MAX_VALUE;
    int min() default Integer.MIN_VALUE;
}
