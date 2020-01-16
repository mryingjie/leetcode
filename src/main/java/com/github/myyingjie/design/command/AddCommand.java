package com.github.myyingjie.design.command;

/**
 * created by Yingjie Zheng at 2020-01-16 11:39
 */
public class AddCommand extends Command {


    private Executor executor= new AddExecutor();

    public AddCommand(int a) {
        super(a);
    }

    @Override
    public int execute(int b) {
        return executor.action(a,b);
    }
}
