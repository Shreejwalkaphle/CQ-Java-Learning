package com.example.Integration2.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping()
    public ResponseEntity<String> hello() {
        // Log a debug messlogger.info("Request received at /hello/sayHello endpoint.");

        // Return a simple greeting message
        return new ResponseEntity<>("{\"message\": \"Hello! Welcome to the Java application's first endpoint.\"}", HttpStatus.OK);
    }
}
