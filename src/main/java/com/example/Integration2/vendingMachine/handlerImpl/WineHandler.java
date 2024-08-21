package com.example.Integration2.vendingMachine.handlerImpl;

import com.example.Integration2.vendingMachine.abstractHandler.VendingMachineRequestHandler;

public class WineHandler extends VendingMachineRequestHandler {
	@Override
	public void process( String nameOfProduct ) {
		if(nameOfProduct.equalsIgnoreCase( "wine" )){
			System.out.println("wine vended out" );
		}else {
			nextRequestHandler.process( nameOfProduct );
		}
	}
}
