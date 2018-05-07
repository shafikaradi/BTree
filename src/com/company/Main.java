package com.company;

import com.Model.BTreeThread;
import com.Model.BTree;

public class Main {

    public static void main(String[] args) throws InterruptedException {


       var btree = new BTree();
       Thread t =  new Thread(new BTreeThread(btree),"BTree");

       t.start();


        t.join();
        btree.printItems();


        System.exit(0);
    }




}
