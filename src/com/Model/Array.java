package com.Model;

import javafx.scene.Node;

import java.lang.management.GarbageCollectorMXBean;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Array {


    private int degree = 2;
    private int items [] = {994,2,92};
    private int keys [] = new int[degree * 2 -1];
    private int counter = 0;
    private Node [] children = new Node[2];

    public void add(){

        for(int i = 0; i < items.length; i++){


            add(items[i]);
            counter++;
        }
    }

    public void split(){

    }

    public void add(int item){



         if(counter==0) {
             keys[counter] = item;
         }else{

             int i = 0;
             int incHolder = counter - 1;

             while(i < counter){

                    if(item < keys[i]){

                        while(incHolder >= i){

                            keys[incHolder + 1] = keys[incHolder];


                            incHolder--;
                        }

                        keys[i] = item;
                       break;

                    }else{

                        keys[counter]  = item;


                    }

               i++;
             }



         }



    }

    public void printContentOfKeys(){
       for (int inc = 0; inc < items.length; inc++)
            System.out.println(keys[inc]);

    }

    public boolean isFull(){


        return keys.length == counter;
    }


    public static void main(String [] args){

        Array m = new Array();
        m.add();
        m.printContentOfKeys();



       System.exit(0);
    }



}
