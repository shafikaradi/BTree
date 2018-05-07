package com.Model;

public class BTreeThread implements Runnable {

    private int items [] = {100,2,92,3,994};
    private BTree bTree;

    public BTreeThread(BTree bTree){
        this.bTree = bTree;

    }

    @Override
    public void run(){

        for (int i = 0 ; i < items.length; i++){
            bTree.add(items[i]);
        }




    }

}
