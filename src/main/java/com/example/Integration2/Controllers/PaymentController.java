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

//	@PostMapping("/order")
//	@CrossOrigin("http://localhost:4200/")
//	public ResponseEntity<String> placeOrder(@RequestBody Product product) {
//		// Logging the received product details
//		System.out.println("Received order for product: " + product);
//
//		// Simulate payment processing (mock for now)
//		boolean paymentSuccess = processPayment(product);
//
//		if (paymentSuccess) {
//			return ResponseEntity.ok("Payment successful for product: " + product.getName());
//		} else {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//					.body("Payment failed for product: " + product.getName());
//		}
//	}
//
//	private boolean processPayment(Product product) {
//		// Placeholder for actual payment logic
//		System.out.println("Processing payment for product: " + product.getName());
//		return true; // Assume payment always succeeds for now
//	}
	@PostMapping("/order")
	@CrossOrigin("http://localhost:4200/")
	public ResponseEntity< Map <String, String> > createOrder( @RequestBody Map<String, Object> productDetails) {
		String productName = (String) productDetails.get("name");
		int price = (Integer) productDetails.get("price");

		// Simulating a payment gateway response
		String paymentLink = "https://payment-gateway.com/pay?amount=" + price;

		Map<String, String> response = new HashMap <>();
		response.put("message", "Order created for " + productName);
		response.put("paymentLink", paymentLink); // This would be from a real gateway in production
		return ResponseEntity.ok(response);
	}
}