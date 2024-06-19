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
//        declare three different queues
        Queue queue1 = new Queue(fromPropertiesFile.getQueueName1());
        rabbitAdmin().declareQueue(queue1);
        Queue queue2 = new Queue(fromPropertiesFile.getQueueName2());
        rabbitAdmin().declareQueue(queue2);
        Queue queue3 = new Queue(fromPropertiesFile.getQueueName3());
        rabbitAdmin().declareQueue(queue3);

//        declare a single directExchange
        DirectExchange yourExchange = new DirectExchange(fromPropertiesFile.getExchangeName());
        rabbitAdmin().declareExchange(yourExchange);

//        bind the three queues with single direct exchange
//        define routekey for each queue, so that when the key comes exchange will know to which queue should the message be pushed.
        Binding binding1 = BindingBuilder.bind(queue1).to(yourExchange).with(fromPropertiesFile.getRoutingKey1());
        rabbitAdmin().declareBinding(binding1);
        Binding binding2 = BindingBuilder.bind(queue2).to(yourExchange).with(fromPropertiesFile.getRoutingKey2());
        rabbitAdmin().declareBinding(binding2);
        Binding binding3 = BindingBuilder.bind(queue3).to(yourExchange).with(fromPropertiesFile.getRoutingKey3());
        rabbitAdmin().declareBinding(binding3);
    }
}