package com.github.myyingjie.design.decorator;

/**
 * created by Yingjie Zheng at 2020-01-06 14:54
 */
public abstract class BufferInputStream implements InputStream{

    private InputStream inputStream;

    BufferInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public int read(byte[] bytes) {
        return inputStream.read(bytes);
    }
}
