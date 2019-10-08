/**
 * created by Yingjie Zheng at 2019-09-16 09:28
 */
package com.github.myyingjie.design.visitor;

//观察者模式
//组合模式
//间接递归  双重分发   被观察的对象.accept(观察者visitor) 调用 visitor.visit(被观察的对象)   而同时 visitor.visit(被观察的对象)又会调用被观察的对象.accept(visitor)方法实现间接递归