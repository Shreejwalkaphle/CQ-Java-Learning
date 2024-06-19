package com.example.Integration2.Utility;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class PropertiesFileReader {
    @Value("${rabbitmq.queueName1}")
    private String queueName1;
    @Value("${rabbitmq.queueName2}")
    private String queueName2;
    @Value("${rabbitmq.queueName3}")
    private String queueName3;

    @Value("${rabbitmq.exchangeName}")
    private String exchangeName;

    @Value("${rabbitmq.routingKey1}")
    private String routingKey1;
    @Value("${rabbitmq.routingKey2}")
    private String routingKey2;
    @Value("${rabbitmq.routingKey3}")
    private String routingKey3;
}
