package com.example.Integration2.vendingMachine.handlerImpl;

import com.example.Integration2.vendingMachine.abstractHandler.VendingMachineRequestHandler;
public class ChocolateHandler extends VendingMachineRequestHandler {
	@Override
	public void process( String nameOfProduct ) {
		if(nameOfProduct.equalsIgnoreCase( "chocolate" )){
			System.out.println("chocolate vended out" );
		}else {
			nextRequestHandler.process( nameOfProduct );
		}
	}
}
