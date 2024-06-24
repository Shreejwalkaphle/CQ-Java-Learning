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
    @GetMapping("/jenkins")
    @CrossOrigin("*")
    public String jenkins() {
        // Log a debug message
        logger.info("Request received at /jenkins endpoint.");

        // Return a simple greeting message
        return "Hello! Welcome to the Java application's first CICD . by adding this method when i push this code to this branch, it will " +
                "automatically detect the changes and starts to build the java application.";
    }
}
