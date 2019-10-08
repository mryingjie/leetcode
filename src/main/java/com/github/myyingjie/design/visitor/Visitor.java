package com.github.myyingjie.design.visitor;

/**
 * created by Yingjie Zheng at 2019-09-16 09:38
 */
public abstract class Visitor {

    public abstract void visit(File file);
    public abstract void visit(Directory directory);

}
