package com.example.Integration2.services;

import com.example.Integration2.models.Customer;
import com.example.Integration2.models.PaymentGatewayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankServices {

	@Autowired
	CustomerService customerService;
	public boolean handlePaymentGatewayRequest( PaymentGatewayRequest paymentGatewayRequest ){
		Customer customer = customerService.getCustomerByAccountNumber( paymentGatewayRequest.getAccountNumber() );
		if(customer != null){
			if(paymentGatewayRequest.getPrice() > customer.getBalance()){
				return false;
			}
			return true;
		}
		return false;
	}
}
