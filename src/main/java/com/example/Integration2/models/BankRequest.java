package com.example.Integration2.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankRequest {

	private String accountNumber;
	private String cvv;
	private String accountHolderName;
	private String productName;
	private double price;
}
