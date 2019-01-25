package com.logica.rabbitmqdemo.Producer.api;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rabbitTest")
public class ProducerWebController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping(value = "/producer")
    public String producer(@RequestParam(value = "name", required = false, defaultValue = "tester") String name) {
        String message = "hello " + name;
        System.out.println("Displaying msg on console: " + message);
        rabbitTemplate.convertAndSend(message);
        return "Message Successfully sent";
    }
}
