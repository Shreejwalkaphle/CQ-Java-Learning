package com.example.Integration2.services;

import com.example.Integration2.models.BankRequest;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

	// URL of the bank's payment API endpoint
	private static final String BANK_API_URL = "http://localhost:8080/bank/payment";

	public boolean processPayment(BankRequest bankRequest) {
		// Create the HTTP client
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			// Create the POST request
			HttpPost postRequest = new HttpPost(BANK_API_URL);

			// Build the JSON payload
			String payload = buildRequestPayload(bankRequest);

			// Set the payload and headers
			StringEntity entity = new StringEntity(payload);
			postRequest.setEntity(entity);
			postRequest.setHeader("Content-Type", "application/json");

			// Execute the request
			try (CloseableHttpResponse response = client.execute(postRequest)) {
				int statusCode = response.getCode();

				// Handle response
				if (statusCode == 200) {
					String responseBody = EntityUtils.toString(response.getEntity());
					System.out.println("Bank Response: " + responseBody);

					// Return true if payment is successful
					return true;
				} else {
					System.err.println("Payment failed. Status: " + statusCode + " - " + response.getReasonPhrase());
					return false;
				}
			}
		} catch (Exception e) {
			System.err.println("An error occurred while processing the payment: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	private String buildRequestPayload(BankRequest bankRequest) {
		// Manually construct the JSON payload
		return "{"
				+ "\"accountNumber\":\"" + bankRequest.getAccountNumber() + "\","
				+ "\"cvv\":\"" + bankRequest.getCvv() + "\","
				+ "\"accountHolderName\":\"" + bankRequest.getAccountHolderName() + "\","
				+ "\"productName\":\"" + bankRequest.getProductName() + "\","
				+ "\"price\":" + bankRequest.getPrice()
				+ "}";
	}
}


