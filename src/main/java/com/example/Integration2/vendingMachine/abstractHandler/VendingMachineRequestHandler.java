package com.example.Integration2.vendingMachine.abstractHandler;
public abstract class VendingMachineRequestHandler {
	public VendingMachineRequestHandler nextRequestHandler;
	public void setNextRequestHandler(VendingMachineRequestHandler requestHandler ) {
		this.nextRequestHandler = requestHandler;
	}
	public abstract void  process(String nameOfProduct);

}
