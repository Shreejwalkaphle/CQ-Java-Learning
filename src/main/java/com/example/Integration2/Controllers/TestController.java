package com.example.Integration2.Controllers;

import com.example.Integration2.Models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
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

    @PostMapping(value = "/user/{id}")
    public User setUser(@PathVariable int id, @RequestBody User user){
        System.out.println(id);
        System.out.println(user.getUserName());
        return user;
    }
}
