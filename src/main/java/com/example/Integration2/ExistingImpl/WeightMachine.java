package com.example.Integration2.ExistingImpl;

import com.example.Integration2.ExistingInterface.ExistingInterface;

public class WeightMachine implements ExistingInterface {
	private int wieghtOfEachBall = 2; //pounds
	@Override
	public int getWeightInPounds( int numberOfBalls ) {
		System.out.println("weight in pounds is: " + numberOfBalls*wieghtOfEachBall );
		return numberOfBalls*wieghtOfEachBall ;
	}
}
