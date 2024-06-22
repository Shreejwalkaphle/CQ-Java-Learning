package com.example.Integration2.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/getData")
    @CrossOrigin("*")
    public String getData() {
        // Log a debug message
        logger.info("Request received from angular at /getData endpoint.");

        // Return a simple greeting message
        return "{\"message\": \"Hello! Welcome from the Java application's first endpoint.\"}";
    }
}
