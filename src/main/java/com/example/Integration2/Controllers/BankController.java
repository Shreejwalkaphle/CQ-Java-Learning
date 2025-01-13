package com.example.Integration2.Controllers;

import com.example.Integration2.models.PaymentGatewayRequest;
import com.example.Integration2.services.BankServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/bank")
public class BankController {

	@Autowired
	BankServices bankService;
	@PostMapping("/payment")
	public ResponseEntity<Boolean> handlePaymentGatewayRequest(@RequestBody PaymentGatewayRequest bankRequest) {
		boolean response = bankService.handlePaymentGatewayRequest(bankRequest);

		if (response) {
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.status(400).body(response); // Bad Request for failed payment
		}
	}
}
