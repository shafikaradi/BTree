package com.company;

import java.lang.reflect.Array;
import java.util.Arrays;

public class BTree {

    private Node root;
    private int degree = 2;


    private static class Lock {}
    private Lock lock = new Lock();

     public BTree(){


         root = new Node(degree, true);
    }

    public synchronized void add(int key){

        synchronized (lock) {
            if (root.counter == 0) {


                root.keys[0] = key;
                root.counter += 1;

            } else {

                var nodeHolder = root;

                if (root.counter == 2 * degree - 1) {

                    Node newNode = new Node(degree, false);
                    root = newNode;

                    newNode.children[0] = nodeHolder;

                    split(newNode, nodeHolder,0);


                    addToNotFullNode(newNode.children[0],key);



                } else {
                    addToNotFullNode(root,key);
                }
            }
        }
    }

    public void addToNotFullNode(Node node,int key){


        var i = node.counter;

        if (node.isLeaf){

            while (i > 0 && key < node.keys[i - 1] )
            {
                node.keys[i] = node.keys[i - 1];
                i--;
            }

            node.keys[i] = key;
            node.counter = node.counter+1;

        } else {

            while (i < node.counter && key > node.keys[i])
                i++;

            if (node.children[i].counter == 2*degree-1) {

                split(node, node.children[i],i);


                if (key > node.keys[i])
                    i++;
            }
            addToNotFullNode(node.children[i],key);
        }

    }



    private void split(Node parentNode,Node childNode, int index){

        var newNode = new Node(degree,childNode.isLeaf);
        newNode.counter = degree - 1;

        for(int i = 0; i < degree - 1; i++){

            newNode.keys[i] = childNode.keys[i + degree];

        }

        if(!childNode.isLeaf){
            for(int i = 0; i < degree; i++){

                newNode.children[i] = childNode.children[ i + degree];

            }
        }

        childNode.counter = degree - 1;

        for(int j = parentNode.counter ; j> index ; j--){

            parentNode.children[j+1] = parentNode.children[j];

        }

        parentNode.children[index + 1] = newNode;

        for(int j = parentNode.counter; j> index; j--){
            parentNode.keys[j + 1] = parentNode.keys[j];
        }

        parentNode.keys[index] = childNode.keys[degree-1];
        childNode.keys[degree-1 ] = 0;

        for(int j = 0; j < degree - 1; j++)
        {
            childNode.keys[j + degree] = 0;
        }



        parentNode.counter++;


    }
    public String search(int key){
       return search(root,key);
    }

    private String search(Node root, int key)
    {
        int i = 0;

        while(i < root.counter && key > root.keys[i])

        {
            i++;
        }

        if(i <= root.counter && key == root.keys[i]){


            return "has been found";
        }

        if(root.isLeaf){

            return "was not found" ;

        }else{

            return search(root.getChild(i),key);

        }
    }

   private class Node
   {

        Node [] children;
        int [] keys;
        int counter = 0;
        boolean isLeaf = false;


        public Node(int degree, boolean isLeaf)
        {
             children = new Node[degree * 2];
             keys = new int[degree * 2 - 1];
             this.isLeaf = isLeaf;
             System.out.println("New Obj is created");
        }




       public Node getChild(int index)
       {
           return children[index];
       }



   }




}
