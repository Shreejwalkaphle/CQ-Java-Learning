package com.example.Integration2.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQMessageListener {
    @RabbitListener(queues = "${rabbitmq.queueName1}")
    public void handleRequest1(String message1) {
        System.out.println("message received from rabbit mq queue1 to listener 1: " + message1);
    }

    @RabbitListener(queues = "${rabbitmq.queueName2}")
    public void handleRequest2(String message2) {
        System.out.println("message received from rabbit mq queue 2 to listener 2: " + message2);
    }

    @RabbitListener(queues = "${rabbitmq.queueName2}")
    public void handleRequest3(String message3) {
        System.out.println("message received from rabbit mq queue 2 to listener 3: " + message3);
    }

}
