package com.example.Integration2.Controllers;

import com.example.Integration2.models.Users;
import com.example.Integration2.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    @PostMapping("/setdata")
    @CrossOrigin("*")
    public String setData(@RequestBody Users user) {
        return userService.saveData(user);
    }
    @GetMapping("/getdata")
    @CrossOrigin("*")
    public Optional<Users> getData(@RequestParam int id) {
        System.out.println("user retrieved : "+userService.retrieveData(id));
        return userService.retrieveData(id);
    }

    @PostMapping("/update")
    @CrossOrigin("*")
    public String updateData(@RequestBody Users user) {
        return userService.updateData(user);
    }

    @DeleteMapping("/delete")
    @CrossOrigin("*")
    public String deleteData(@RequestParam int id) {
        return userService.deleteData(id);
    }
}
