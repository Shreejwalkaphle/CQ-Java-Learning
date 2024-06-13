package com.example.Integration2.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class EmailSender {
    public String sendEmail() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("shreejwalkaphle5@gmail.com");
        mailSender.setPassword("dchocflsxkdofrzv");

        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");

        mailSender.setJavaMailProperties(properties);

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
