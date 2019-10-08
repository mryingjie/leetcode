package com.github.myyingjie.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
 public  class Tuple3<I, U, P> {
    private I i;
    private P p;
    private U u;
}