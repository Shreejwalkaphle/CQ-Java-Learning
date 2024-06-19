package com.example.Integration2.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQMessageListener {
    @RabbitListener(queues = "${rabbitmq.queueName1}")
    public void handleRequest1(String message1) {
        System.out.println("message received from rabbit mq to listener 1: " + message1);
    }

}
