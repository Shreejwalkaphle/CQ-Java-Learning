package com.example.Integration2.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class EmailSender {
    @Autowired
    JavaMailSender mailSender;
    public String sendEmail() throws MessagingException {
        String from = "shreejwalkaphle5@gmail.com";
        String to = "shreejwalkaphle@gmail.com";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setSubject("This is an HTML email");
        helper.setFrom(from);
        helper.setTo(to);

        boolean html = true;
        helper.setText("<b>Hey guys</b>,<br><i style='color:red'>Welcome to my new home</i>", html);

        mailSender.send(message);
        return "sent";
    }
}
