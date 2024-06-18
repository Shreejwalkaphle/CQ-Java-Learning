package com.example.Integration2.services;

import com.example.Integration2.Utility.PropertiesFileReader;
import com.example.Integration2.rabbitMQconfigurations.RabbitMQConfiguration;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQMessageListener {
    @RabbitListener(queues = "theNameOfYourQueue")
    public void handleRequest(String message) {
        System.out.println("message received from rabbit mq to listener : " + message);
    }
}
