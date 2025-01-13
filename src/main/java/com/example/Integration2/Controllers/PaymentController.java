package com.example.Integration2.Controllers;

import com.example.Integration2.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
	@PostMapping("/order")
	@CrossOrigin("http://localhost:4200/")
	public ResponseEntity< Map <String, String> > createOrder( @RequestBody Map<String, Object> productDetails) {
		String productName = (String) productDetails.get("name");
		int price = (Integer) productDetails.get("price");

		// Simulating a payment gateway response
		String paymentLink = "http://localhost:4200?name="+productName+"&amount=" + price;

		Map<String, String> response = new HashMap <>();
		response.put("message", "Order created for " + productName);
		response.put("paymentLink", paymentLink); // This would be from a real gateway in production
		return ResponseEntity.ok(response);
	}
}