package com.github.myyingjie.design.visitor;

/**
 * created by Yingjie Zheng at 2019-09-16 09:34
 */
public class File extends Entry{

    private String name;
    private int size;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public void printList(String prefix) {
        System.out.println(prefix + "/" + this);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        System.out.println();
    }
}
