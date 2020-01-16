package com.github.myyingjie.design.command;

/**
 * created by Yingjie Zheng at 2020-01-16 11:37
 */
public class Client {


    public static void main(String[] args) {
        Calculator calculator = new Calculator(1);

        calculator.addCommand(new SubCommand(2));
        calculator.addCommand(new AddCommand(4));
        calculator.addCommand(new SubCommand(10));
        calculator.execute();

        System.out.println(calculator.getValue());

    }
}
