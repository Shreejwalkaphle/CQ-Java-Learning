package com.example.Integration2.java8.passingfunctionasMethodReference;

public class Main {
    public static void main(String[] args) {
        new SomeClass().someMethod(MyImplClass::myInterfaceMethod);
    }

}
