package com.example.Integration2.Controllers;

import java.util.Arrays;
import java.util.Scanner;

public class test {
    public static void main(String[] arr){

        int[] array = {1,2,3,4,5,6,7,0,0,0,0,0,0,0,4,5,6,4,5,6,1};
        test object = new test();
        System.out.println(object.method(array));
    }

    public int method(int[] array){
        int s = 0;
        int si = 0;
        int ei = 0;

        for(int i=0; i< array.length; i++){
            if(array[i] == 0){
                for(int j=i; j< array.length; j++){
                    if(array[i] == array[j]){
                        s++;

                        if(s >= 3){
                            si = i;
                            ei = j;
                            //i=j;
                        }
                        continue;
                    }
                    if(array[i] != array[j] && s < 3){
                        s = 0;
                    }
                }
            }
        }

        if(s<3){return 0;}

        int begincount = 0;
        int endcount = 0;

        for(int i=0;i<si; i++){
            if(array[i] != 0){
                begincount++;
            }
        }
        for(int i=ei;i<array.length; i++){
            if(array[i] != 0){
                endcount++;
            }
        }

        if(s ==  begincount && s == endcount){
            return 1;
        }


        return 0;
    }

}


