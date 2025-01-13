package com.example.Integration2.Controllers;

import com.example.Integration2.models.BankRequest;
import com.example.Integration2.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/api/payment")
public class PaymentGatewayController {

	@Autowired
	PaymentService paymentService;

	@PostMapping
	public ResponseEntity<String> processPayment(@RequestBody BankRequest bankRequest) {
		boolean paymentSuccess = paymentService.processPayment(bankRequest);

		if (paymentSuccess) {
			return ResponseEntity.ok("Payment successful");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Payment failed");
		}
	}

}
