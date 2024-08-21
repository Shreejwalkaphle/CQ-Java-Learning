package com.example.Integration2.Client;

import com.example.Integration2.Adapter.AdapterImpl;
import com.example.Integration2.Adapter.AdapterInterface;
import com.example.Integration2.ExistingImpl.WeightMachine;

public class Client {
	public static void main( String[] args ) {
		AdapterInterface adapterInterface = new AdapterImpl( new WeightMachine() );
		int numberofBalls = 67;
		System.out.println("weight in kg is : "+adapterInterface.getWeightInKgs( numberofBalls ) );
	}
}
