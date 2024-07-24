package com.example.Integration2.java8.passingfunctionasMethodReference;

public class SomeClass {
    public int someMethod(MyInterface myInterface){
     return myInterface.myInterfaceMethod(5,6);
    }
}
