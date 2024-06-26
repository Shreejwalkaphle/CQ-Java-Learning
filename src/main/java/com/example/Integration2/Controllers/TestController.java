package com.example.Integration2.Controllers;

import com.example.Integration2.models.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;

@RestController
@RequestMapping()
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping()
    @CrossOrigin("*")
    public String hello() {
        // Log a debug message
        logger.info("Request received at /hello/sayHello endpoint.");

        // Return a simple greeting message
        return "Hello! Welcome to the Java application's first endpoint.";
    }
    @GetMapping("/getUsers")
    @CrossOrigin("*")
    public Users getUser() {
        Users user = new Users();
        user.setUsername("exampleUser");
        user.setPassword("examplePassword");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("johndoe@example.com");
        user.setRole("admin");
        user.setDateOfBirth(LocalDate.of(1990, 1, 1));
        user.setAddress("Random Address");
        user.setPhoneNumber("1234567890");
        user.setTasks(Arrays.asList("Task 1", "Task 2", "Task 3"));

        return user;
    }
}
