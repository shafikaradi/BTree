package com.company;

import java.lang.reflect.Array;
import java.util.Arrays;

public class BTree {

    private Node root;
    private int degree = 2;
    private int [] items = {4,3,1,2};

    private static class Lock {};
    private Object lock = new Lock();

    BTree(){

        root = new Node();
    }


    public void add() throws  ArrayIndexOutOfBoundsException{

     //   synchronized (lock) {

        int inc = root.keys.length - 1;
            for (int i = 0; i < items.length; i++) {


                if (i + 1 > root.keys.length) {

                    split(root);
                } else {

                    if (root.counter == 0) {
                        root.add(items[i], i);
                        System.out.println("me");
                    } else

                     for(int j = 0; j < root.keys.length; j++){

                           if(items[i] < root.keys[j]){


                               while(inc >= j){

                                   if(inc == j)
                                       root.keys[j] = items[i];
                                   else
                                       root.keys[inc] = root.keys[inc - 1];



                                   inc--;
                               }



                           }

                     }
                }
            }
     //   }

    }


    private void split(Node node){

        node.children[0] = new Node();
        fillNode(node.children[0]);
        node.children[degree*2-1] = new Node();
        node.traverse();


    }

    private void fillNode(Node node){

       // node.add(item,index);

    }

   private class Node{

        Node [] children;
        int [] keys;
        int counter = 0;


        public Node(){
             children = new Node[degree * 2];
             keys = new int[degree * 2 - 1];
             System.out.println("New Obj is created");
        }


        public void add(int item, int index){

            keys[index] = item;
            counter++;
        }

        public void traverse(){


            for (int i = 0; i < keys.length; i++)
                System.out.println(keys[i]);
        }



   }

}
