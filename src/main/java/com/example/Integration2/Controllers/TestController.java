package com.example.Integration2.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping()
    public String hello() {
        // Log a debug message
        logger.info("Request received at /hello/sayHello endpoint.");

        // Return a simple greeting message
        return "Hello! Welcome to the Java application's first endpoint.";
    }

//    yesko lagi browser ma :: localhost:8080/user?username="shreejwal"
    @GetMapping("/user")
    public String getUser(@RequestParam String username){
        return "user : "+ username;
    }

//    yesko lagi browser ma :: localhost:8080/user/shreejwal
    @GetMapping("/user/{username}")
    public String getMeUser(@PathVariable String username){
        return "user : "+ username;
    }


}
