package com.github.myyingjie.design.decorator;


/**
 * created by Yingjie Zheng at 2020-01-06 14:50
 *
 * 装饰器最典型最经典的应用 是 Java I/O 标准库。
 * InputStream 的子类 FilterInputStream，OutputStream 的子类 FilterOutputStream，
 * Reader 的子类 BufferedReader 以及 FilterReader，
 * 还有 Writer 的子类 BufferedWriter、FilterWriter 以及 PrintWriter 等，它们都是抽象装饰类
 *
 *
 *
 */
public class Client {

    public static void main(String[] args) {
        //原类
        InputStream inputStream = new InputStreamImpl();

        //用装饰器装饰原对象
        InputStream is = new BufferInputStreamImpl(inputStream);
        byte[] bytes = new byte[10];
        is.read(bytes);
    }

}
