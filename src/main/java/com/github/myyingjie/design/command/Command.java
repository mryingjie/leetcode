package com.github.myyingjie.design.command;

/**
 * created by Yingjie Zheng at 2020-01-16 11:37
 */
public abstract class Command {

    protected int a;


    public Command(int a){
        this.a = a;
    }



    public abstract int execute(int b);
}
