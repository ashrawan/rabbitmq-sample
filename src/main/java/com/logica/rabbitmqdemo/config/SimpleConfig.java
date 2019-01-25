//package com.logica.rabbitmqdemo.config;
//
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class SimpleConfig {
//
//    private String aQueue = "aQueue";
//    private String bQueue = "bQueue";
//
//    private String dirExchange = "dirExchange";
//    private String routingkey = "dr";
//
//    @Bean
//    Queue queue1() {
//        return new Queue(aQueue, false);
//    }
//
//    @Bean
//    Queue queue2() {
//        return new Queue(bQueue, false);
//    }
//
//    @Bean
//    DirectExchange exchange() {
//        return new DirectExchange(dirExchange);
//    }
//
//    @Bean
//    Binding bindingQueue1(@Qualifier("queue1") Queue queue, DirectExchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with(routingkey);
//    }
//
//    @Bean
//    Binding bindingQueue2(@Qualifier("queue2") Queue queue, DirectExchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with(routingkey);
//    }
//
//    @Bean
//    public MessageConverter jsonMessageConverter() {
//        return new Jackson2JsonMessageConverter();
//    }
//
//}
