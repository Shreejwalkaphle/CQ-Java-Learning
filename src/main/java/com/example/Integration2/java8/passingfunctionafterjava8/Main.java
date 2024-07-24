package com.example.Integration2.java8.passingfunctionafterjava8;

public class Main {
    public static void main(String[] args) {
        int result = new SomeClass().someClassMethod((a,b)->{
            System.out.println("this is lambda");
            System.out.println("i can do anything here, like sum"+a+b);
            return a+b;});
        System.out.println(result);
    }
}
