package com.example.Integration2.java8.passingfunctionbeforejava8;

public class MainClass {
    public static void main(String[] args) {
        SomeClass someClass = new SomeClass();
        String returned = someClass.someMethod(new MyImplClass());
        System.out.println(returned);
    }
}
