package com.example.Integration2.rabbitMQconfigurations;

import com.example.Integration2.Utility.PropertiesFileReader;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Autowired
    PropertiesFileReader fromPropertiesFile;

//    properties file ma rabbit ko connection ko details spring le read garera
//    rabbit message broker sanga connection create gariori yaha connectionfactory variable ma
//    tyo rabbit message broker sanga ko connection ko instance lera ayera rakhdincha.

    @Autowired
    ConnectionFactory connectionFactory;

//    yo connection ko instance le ani hamile RabbitAdmin ko instance create garna sakcham.
    @Bean
    public RabbitAdmin rabbitAdmin() {
        return new RabbitAdmin(connectionFactory);
    }

    public void declareQueueExchangesAndBindings(){
        Queue yourQueue = new Queue(fromPropertiesFile.getQueueName());
        rabbitAdmin().declareQueue(yourQueue);
        DirectExchange yourExchange = new DirectExchange(fromPropertiesFile.getExchangeName());
        rabbitAdmin().declareExchange(yourExchange);
        Binding binding = BindingBuilder.bind(yourQueue).to(yourExchange).with(fromPropertiesFile.getRoutingKey());
        rabbitAdmin().declareBinding(binding);
    }
}