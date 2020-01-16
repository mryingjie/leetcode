package com.github.myyingjie.design.command;

import java.util.ArrayList;

/**
 * created by Yingjie Zheng at 2020-01-16 14:12
 */
public class Calculator {

    private ArrayList<Command> commands = new ArrayList<>();

    private int value;

    public Calculator(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }


    public void addCommand(Command command){
        commands.add(command);
    }

    public void execute(){
        for (Command command : commands) {
            value = command.execute(value);
        }
        commands.clear();
    }

}
