package com.github.myyingjie.design.visitor;

import java.util.Iterator;

/**
 * created by Yingjie Zheng at 2019-09-16 09:30
 */
public abstract class Entry {

    public abstract String getName();

    public abstract int getSize();

    public abstract void printList(String prefix);

    public void printList() {
        printList("");

    }

    public Entry add(Entry entry) throws RuntimeException {
        throw new RuntimeException();

    }

    public abstract void accept(Visitor visitor);

    public Iterator iterator() throws RuntimeException {
        throw new RuntimeException();

    }

    public String toString() {
        return getName() + "<" + getSize() + ">";

    }
}
