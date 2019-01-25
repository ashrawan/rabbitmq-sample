package com.logica.rabbitmqdemo.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Consumer {

    @RabbitListener(queues = "lbQueue")
    public void receive(String payload, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {

        System.out.println(payload);
        channel.basicNack(tag, false, false); // setting negative acknowledgment on message
        // channel.basicAck(tag, false); // message successfully acknowledge
    }
}
