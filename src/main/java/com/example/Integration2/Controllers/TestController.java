package com.example.Integration2.Controllers;

import com.example.Integration2.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class TestController {
    @Autowired
    UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping()
    @CrossOrigin("*")
    public String hello() {
        // Log a debug message
        logger.info("Request received at /hello/sayHello endpoint.");

        // Return a simple greeting message
        return "Hello! Welcome to the Java application's first endpoint.";
    }
    @GetMapping("/getdata")
    @CrossOrigin("*")
    public String getData() {
        System.out.println(userService.serviceMethod());
        return userService.serviceMethod();
    }
}
