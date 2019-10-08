package com.github.myyingjie.design.visitor;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * created by Yingjie Zheng at 2019-09-16 09:44
 */
public class FileVisitor extends Visitor {

    String currentDir = "";
    String suffix;
    ArrayList files=new ArrayList();

    public FileVisitor(String suffix){
        this.suffix = suffix;
    }

    public void visit(File file) {
        if(file.getName().endsWith(suffix)){
            // System.out.println(currentDir+"/"+file);
            files.add(currentDir+"/"+file);
        }
    }

    public void visit(Directory directory) {
        String saveDir=currentDir;
        currentDir+=("/"+directory.getName());
        Iterator it=directory.iterator();
        while(it.hasNext()){
            Entry entry=(Entry)it.next();
            entry.accept(this);
        }
        currentDir=saveDir;
    }
    Iterator getFiles(){
        return files.iterator();
    }

}
