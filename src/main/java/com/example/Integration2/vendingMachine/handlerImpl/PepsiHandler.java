package com.example.Integration2.vendingMachine.handlerImpl;

import com.example.Integration2.vendingMachine.abstractHandler.VendingMachineRequestHandler;

public class PepsiHandler extends VendingMachineRequestHandler {
	@Override
	public void process( String nameOfProduct ) {
		if(nameOfProduct.equalsIgnoreCase( "pepsi" )){
			System.out.println("pepsi vended out" );
		}else {
			nextRequestHandler.process( nameOfProduct );
		}
	}
}
