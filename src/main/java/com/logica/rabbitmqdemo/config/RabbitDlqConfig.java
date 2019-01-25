package com.logica.rabbitmqdemo.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitDlqConfig {

    private String queueName = "lbQueue";
    private String exchange = "lbExchange";
    private String routingKey = "lbRouteKey";

    private String deadQueueName = "dlq-lbQueue";
    private String deadExchangeName = "dlq-lbExchange";
    private String deadRoutingKey = "dlq-lbRouteKey";

    @Autowired
    private ConnectionFactory connectionFactory; // ConnectionFactory for creating rabbitmq client connection

    /**
     * Configuring Dead Letter Queue for automatic routing for Negative Acknowledged message
     * x-message-ttl sets 10 seconds time to live
     *
     * @return Queue
     */
    @Bean
    Queue deadQueue() {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-message-ttl", 10000);
        return new Queue(deadQueueName, true, false, false, arguments);
    }

    /**
     * Configuring Exchange for DLQ
     *
     * @return DirectExchange
     */
    @Bean
    DirectExchange deadExchange() {
        return new DirectExchange(deadExchangeName);
    }

    @Bean
    Binding deadQueueBinding(@Qualifier("deadQueue") Queue queue, @Qualifier("deadExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(deadRoutingKey);
    }

    /**
     * Configuring Queue with dead-letter-exchange and routing key arguments
     * These arguments will route message to the configured exchange when negative acknowledgment is received
     *
     * @return Queue
     */
    @Bean
    Queue queue() {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", deadExchangeName);
        arguments.put("x-dead-letter-routing-key", deadRoutingKey);
        return new Queue(queueName, true, false, false, arguments);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    Binding bindingQueue1(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        rabbitTemplate.setExchange(exchange);
        rabbitTemplate.setRoutingKey(routingKey);
        return rabbitTemplate;
    }

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //  must ack/nack via a channel aware listener
        container.setDefaultRequeueRejected(false); // Default behavior on message rejection, setting requeue to false
        return container;
    }

}
