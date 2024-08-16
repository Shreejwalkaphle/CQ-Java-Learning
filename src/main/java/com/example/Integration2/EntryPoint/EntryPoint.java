package com.example.Integration2.EntryPoint;

import com.example.Integration2.BaseInterface.PizzaBase;
import com.example.Integration2.Toppings.Jalepino;
import com.example.Integration2.Toppings.Mushroom;
import com.example.Integration2.baseClasses.Margaritta;

public class EntryPoint {
	public static void main( String[] args ) {
		PizzaBase pizza = new Jalepino( new Mushroom( new Margaritta() ) );
		System.out.println("pizza cost : " + pizza.totalCost() );
	}
}
