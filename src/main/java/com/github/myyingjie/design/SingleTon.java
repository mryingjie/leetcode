package com.github.myyingjie.design;

/**
 * created by Yingjie Zheng at 2020-03-12 15:50
 */
public class SingleTon {

    private SingleTon() {
    }

    private static volatile SingleTon singleTon = null;

    public static SingleTon getInstance() {

        //第一次校验
        if (singleTon == null) {

            synchronized (SingleTon.class) {

                //第二次校验
                if (singleTon == null) {
                    singleTon = new SingleTon();
                }
            }
        }
        return singleTon;
    }
}