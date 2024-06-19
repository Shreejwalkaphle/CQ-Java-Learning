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

    @Value("${rabbitmq.exchangeName}")
    private String exchangeName;

}
