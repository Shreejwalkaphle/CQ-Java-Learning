package com.example.Integration2.Toppings;

import com.example.Integration2.BaseInterface.PizzaBase;
import com.example.Integration2.Decorators.PizzaToppingsDecorator;

public class Cheese extends PizzaToppingsDecorator {
	PizzaBase pizza;
	private int cheeseCost = 50;


	//	constructor injection bata base lai initialize gareko.
	public Cheese( PizzaBase pizzaBase ) {
		this.pizza = pizzaBase;
	}

	@Override
	public int totalCost( ) {
		return pizza.totalCost( )+cheeseCost;
	}
}
