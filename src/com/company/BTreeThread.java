package com.company;

import com.company.BTree;

public class BTreeThread implements Runnable {


    private BTree btree;



    public BTreeThread(){
        btree = new BTree();

    }

    @Override
    public void run(){

        btree.add(1);
        btree.add(2);
        btree.add(100);
        btree.add(3);
        btree.add(22);
        btree.add(1300);

        System.out.println(btree.search(22));





    }

}
