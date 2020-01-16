package com.github.myyingjie.design.decorator;

/**
 * created by Yingjie Zheng at 2020-01-06 14:54
 */
public class InputStreamImpl implements InputStream {
    @Override
    public int read(byte[] bytes) {
        System.out.println("InputStreamImpl read");
        return bytes.length;
    }
}
