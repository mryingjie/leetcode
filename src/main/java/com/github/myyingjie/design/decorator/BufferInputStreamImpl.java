package com.github.myyingjie.design.decorator;

/**
 * created by Yingjie Zheng at 2020-01-06 14:58
 */
public class BufferInputStreamImpl extends BufferInputStream {

    public BufferInputStreamImpl(InputStream inputStream) {
        super(inputStream);
    }


    @Override
    public int read(byte[] bytes) {
        System.out.println("BufferInputStreamImpl read");
        return super.read(bytes);
    }
}
