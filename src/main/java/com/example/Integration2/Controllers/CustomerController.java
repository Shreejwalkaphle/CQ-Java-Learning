package com.example.Integration2.Controllers;


import com.example.Integration2.models.Customer;
import com.example.Integration2.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
		return ResponseEntity.ok(customerService.getCustomerById(id));
	}


	// Register a new customer
	@PostMapping("/register")
	public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer) {
		return ResponseEntity.ok(customerService.registerCustomer(customer));
	}

	// Login a customer
	@PostMapping("/login")
	public ResponseEntity<Customer> loginCustomer(@RequestBody Customer customer) {
		return ResponseEntity.ok(customerService.loginCustomer( customer.getEmail( ), customer.getPassword( ) ));
	}

	// Get balance
	@GetMapping("/{id}/balance")
	public ResponseEntity<Double> getBalance(@PathVariable Long id) {
		return ResponseEntity.ok(customerService.getBalance(id));
	}

	// Deposit amount
	@PostMapping("/{id}/deposit")
	public ResponseEntity<Double> depositAmount(@PathVariable Long id, @RequestParam double amount) {
		return ResponseEntity.ok(customerService.depositAmount(id, amount));
	}

	// Withdraw amount
	@PostMapping("/{id}/withdraw")
	public ResponseEntity<Double> withdrawAmount(@PathVariable Long id, @RequestParam double amount) {
		return ResponseEntity.ok(customerService.withdrawAmount(id, amount));
	}


}
