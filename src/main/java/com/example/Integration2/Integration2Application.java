package com.example.Integration2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.Integration2.repository")
public class Integration2Application {

	public static void main(String[] args) {
		SpringApplication.run(Integration2Application.class, args);
	}

}
