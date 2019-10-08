package com.github.myyingjie.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created by Yingjie Zheng at 2019-09-16 11:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tuple2<K,V> {

    public K x;
    public V y;

    public K getX() {
        return x;
    }

    public void setX(K x) {
        this.x = x;
    }

    public V getY() {
        return y;
    }

    public void setY(V y) {
        this.y = y;
    }
}
