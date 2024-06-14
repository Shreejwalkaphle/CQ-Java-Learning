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


    @GetMapping("/register")
    public String showRegistrationForm(Model model) { // initialize model object and make it ready to send to the front end
        model.addAttribute("user", new User()); // add the object that we want to bind with the data from the front end
        return "registration_form_page"; // return a total web page. this web page when renders will automatically contain model object
    }
    @PostMapping(value = "/handleRegistration") // when front end hits submit this url gets executed. given, in its form action this url should be defined and in its from method post should be defined
    public String handleRegistration(@ModelAttribute("user") User myUser){ // the binded object from front end is wrapped inside model object and recieved here
        //logic to handle the data from frontend
        return "success_page";
    }
}
