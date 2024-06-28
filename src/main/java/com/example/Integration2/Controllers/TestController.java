package com.example.Integration2.Controllers;

import com.example.Integration2.models.Student;
import com.example.Integration2.models.builders.StudentBuilder;
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
    @GetMapping("/build")
    @CrossOrigin("*")
    public String build() {
        StudentBuilder builder = new StudentBuilder();
        return builder.studentId("STD1").
                firstName("shreejwal").
                lastName("kaphle").
                build().toString();
    }
}
