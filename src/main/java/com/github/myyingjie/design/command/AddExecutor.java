package com.github.myyingjie.design.command;

/**
 * created by Yingjie Zheng at 2020-01-16 11:41
 */
public class AddExecutor implements Executor {

    @Override
    public int action(int a,int b) {
        return a + b;
    }
}
