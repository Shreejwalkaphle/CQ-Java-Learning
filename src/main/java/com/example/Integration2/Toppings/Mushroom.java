package com.example.Integration2.Toppings;

import com.example.Integration2.BaseInterface.PizzaBase;
import com.example.Integration2.Decorators.PizzaToppingsDecorator;

public class Mushroom extends PizzaToppingsDecorator {
	public Mushroom( PizzaBase pizza ) {
		this.pizza = pizza;
	}

	PizzaBase pizza;
	private int mushroomCost = 100;
	@Override
	public int totalCost( ) {
		return this.pizza.totalCost()+mushroomCost;
	}
}
