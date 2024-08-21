package com.example.Integration2.vendingMachine.entryPoint;

import com.example.Integration2.vendingMachine.handlerImpl.ChipsHandler;
import com.example.Integration2.vendingMachine.handlerImpl.ChocolateHandler;
import com.example.Integration2.vendingMachine.handlerImpl.PepsiHandler;
import com.example.Integration2.vendingMachine.handlerImpl.WineHandler;

public class EntryPoint {
	public static void main( String[] args ) {
//		initialize the handlers
		ChocolateHandler chocolateHandler = new ChocolateHandler();
		ChipsHandler chipsHandler = new ChipsHandler();
		WineHandler wineHandler = new WineHandler();
		PepsiHandler pepsiHandler = new PepsiHandler();

//		set up chain of responsibilities. chips first,if not then go for chocolate,if not then go for pepsi.
		chipsHandler.setNextRequestHandler( chocolateHandler );
		chocolateHandler.setNextRequestHandler( pepsiHandler );

		chipsHandler.process( "pepsi" );
//		chipsHandler.process( "wine" );  // will throw exception because wine handler is not chained.
	}
}
