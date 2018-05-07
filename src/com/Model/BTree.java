package com.Model;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BTree {


    private final int degree = 2;
    private Node root = null;
    private Node trace;
    private Lock lock;


    public BTree(){



        root = new Node(true);
        lock = new ReentrantLock();
    }




    public void add(int item) {

        trace = root;
        add(item,trace);
    }

    private void add(int item, Node node){

        lock.lock();
        if (!node.isKeysArrayFull())


            node.add(item);
        else {
            split(node);
            traceDown(node,item);

        }



        lock.unlock();

    }

    private void traceDown(Node node, int item){

        var arrHolder = node.keys;
        var count = node.children.length;

        int i = 0;

        while (i < count){

//         if(arrHolder[i] != 0) {

            if(i == 0 && item < arrHolder[i]){

                        node.children[i].add(item);


            }else if(item > arrHolder[i] && item < arrHolder[i+1] && arrHolder[i] != 0 && arrHolder[i+1] != 0) {

                node.children[i].add(item);

            }else  if(item > arrHolder[i] && item > node.children[i].keys[0]){
                node.add(item);
            }

//         }else{
//                node.add(item);
//         }

            i++;

        }


    }

    private void split(Node node) {

        trace.isLeaf = false;

        trace.children[0] = new Node(true);
        trace.children[0].add(root.keys[0]);
        trace.children[degree * 2 - 1] = new Node(true);
        trace.children[degree * 2 - 1].add(trace.keys[2]);
        shiftKeys(trace.keys);

    }

    private void shiftKeys(int [] arr){

        for(int i = 0 ; i < arr.length - 1 ; i++){

            arr[i] = arr[i + 1];

        }

        Arrays.fill(arr,degree - 1,arr.length,0);

    }


    public void printItems(){
        root.printKeys();
    }


    private class Node{

        Node [] children;
        int [] keys;
        int counter = 0;
        boolean isLeaf;


        public Node(boolean isLeaf){
            children = new Node[degree * 2];
            keys = new int[degree * 2 - 1];
            this.isLeaf = isLeaf;
        }



        public void printKeys(){

            for (int i = 0 ; i < keys.length ; i++)
             System.out.println(children[degree * 2 -1].keys[i]);
        }

        public boolean isKeysArrayFull(){


            return counter == keys.length;

        }


        public void add(int item) {


                if (this.counter == 0) {
                    this.keys[this.counter] = item;
                } else {

                    int i = 0;
                    int incHolder = this.counter - 1;

                    while (i < this.counter) {

                        if (item < this.keys[i]) {

                            while (incHolder >= i) {


                                this.keys[incHolder + 1] = this.keys[incHolder];


                                incHolder--;
                            }

                            this.keys[i] = item;

                            break;
                        } else {

                            this.keys[this.counter] = item;


                        }

                        i++;
                    }


                }

                this.counter++;
        }
    }
}
