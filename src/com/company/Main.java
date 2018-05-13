package com.company;


public class Main {

    public static void main(String[] args) throws InterruptedException {


          BTree tree = new BTree();
         int items [] = {100,2,92,3,994};


        for (int i:items) {


        }




        new Thread(() -> {
         tree.add(100);
        }).start();

        new Thread(() -> {
            tree.add(2);
        }).start();

        new Thread(() -> {
            tree.add(92);
        }).start();

        new Thread(() -> {
            tree.add(3);
        }).start();

        new Thread(() -> {
            tree.add(10);
        }).start();


        System.out.println(tree.search(92));



        System.exit(0);
    }




}
