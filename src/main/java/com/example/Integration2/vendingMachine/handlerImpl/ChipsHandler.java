package com.example.Integration2.vendingMachine.handlerImpl;

import com.example.Integration2.vendingMachine.abstractHandler.VendingMachineRequestHandler;

public class ChipsHandler extends VendingMachineRequestHandler {
	@Override
	public void process( String nameOfProduct) {
		if(nameOfProduct.equalsIgnoreCase( "chips" )){
			System.out.println("chips vended out" );
		}else {
			nextRequestHandler.process( nameOfProduct );
		}
	}
}
