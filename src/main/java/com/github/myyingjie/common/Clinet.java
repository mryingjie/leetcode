package com.github.myyingjie.common;

/**
 * created by Yingjie Zheng at 2020-03-18 11:27
 */
public class Clinet {

    public static void main(String[] args) {
        System.out.println(replace("Hello World ", " ", "%20"));

    }


    public static String replace(String str,String from,String to){
        return str.replace(from, to);
    }

}
