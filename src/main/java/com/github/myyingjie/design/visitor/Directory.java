package com.github.myyingjie.design.visitor;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * created by Yingjie Zheng at 2019-09-16 09:36
 */
public class Directory extends Entry {

    String name;
    ArrayList entrys = new ArrayList();

    public Directory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        int size = 0;
        Iterator it = entrys.iterator();
        while (it.hasNext()) {
            size += ((Entry) it.next()).getSize();
        }
        return size;
    }

    public Entry add(Entry entry) {
        entrys.add(entry);
        return this;
    }

    public Iterator iterator() {
        return entrys.iterator();
    }

    public void printList(String prefix) {
        System.out.println(prefix + "/" + this);
        Iterator it = entrys.iterator();
        Entry entry;
        while (it.hasNext()) {
            entry = (Entry) it.next();
            entry.printList(prefix + "/" + name);
        }
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        System.out.println();
    }

}
