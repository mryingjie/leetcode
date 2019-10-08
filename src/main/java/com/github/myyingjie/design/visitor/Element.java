package com.github.myyingjie.design.visitor;

/**
 * created by Yingjie Zheng at 2019-09-16 09:30
 */
public interface Element {

    public abstract void accept(Visitor visitor);
}
