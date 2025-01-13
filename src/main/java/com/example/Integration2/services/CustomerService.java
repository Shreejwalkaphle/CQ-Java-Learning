package com.example.Integration2.services;


import com.example.Integration2.models.Customer;
import com.example.Integration2.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerService {

	private final CustomerRepository customerRepository;

	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public Customer getCustomerById(Long id) {
		return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
	}


	public Customer registerCustomer(Customer customer) {
		customer.setAccountNumber(UUID.randomUUID().toString());
		return customerRepository.save(customer);
	}

	public Customer loginCustomer(String email, String password) {
		Customer customer = customerRepository.findByEmail(email);
		if (customer != null && customer.getPassword().equals(password)) {
			return customer;
		}
		throw new IllegalArgumentException("Invalid email or password");
	}

	public double getBalance(Long id) {
		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Customer not found"));
		return customer.getBalance();
	}

	public double depositAmount(Long id, double amount) {
		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Customer not found"));
		customer.setBalance(customer.getBalance() + amount);
		customerRepository.save(customer);
		return customer.getBalance();
	}

	public double withdrawAmount(Long id, double amount) {
		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Customer not found"));
		if (amount > customer.getBalance()) {
			throw new IllegalArgumentException("Insufficient balance");
		}
		customer.setBalance(customer.getBalance() - amount);
		customerRepository.save(customer);
		return customer.getBalance();
	}

	public Customer getCustomerByAccountNumber(String accountNumber) {
		return customerRepository.findByAccountNumber(accountNumber);
	}
}
