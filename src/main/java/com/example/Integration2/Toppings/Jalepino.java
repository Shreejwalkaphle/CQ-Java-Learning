package com.example.Integration2.Toppings;

import com.example.Integration2.BaseInterface.PizzaBase;
import com.example.Integration2.Decorators.PizzaToppingsDecorator;

public class Jalepino extends PizzaToppingsDecorator {
	PizzaBase pizza;
	private int jalepinoCost = 30;

//	constructor injection
	public Jalepino( PizzaBase pizza ) {
		this.pizza = pizza;
	}

	@Override
	public int totalCost( ) {
		return this.pizza.totalCost()+jalepinoCost;
	}
}
