package com.example.Integration2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class EmailSender {
    @Autowired
    JavaMailSender mailSender;
    public String sendEmail() {
        String from = "shreejwalkaphle5@gmail.com";
        String to = "shreejwalkaphle@gmail.com";

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject("This is a plain text email from java side using smtp");
        message.setText("Hello shreejwal! This is a plain text email from java side using smtp");

        mailSender.send(message);
        return "sent";
    }
}
