package com.example.Integration2.Adapter;

import com.example.Integration2.ExistingImpl.WeightMachine;

public class AdapterImpl implements AdapterInterface{

	WeightMachine weightMachine;

	public AdapterImpl( WeightMachine weightMachine ) {
		this.weightMachine = weightMachine;
	}

	@Override
	public double getWeightInKgs( int numberofBalls) {
		return 0.45359237* this.weightMachine.getWeightInPounds( numberofBalls );
	}
}
