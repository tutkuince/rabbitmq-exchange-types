package io.ince.vms.springrabbit.controller;

import io.ince.vms.springrabbit.constants.Constants;
import io.ince.vms.springrabbit.model.ProducerMessage;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

    @Autowired
    public ProducerController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    private final RabbitTemplate rabbitTemplate;
    private DirectExchange directExchange;

    @Autowired
    public void setDirectExchange(DirectExchange directExchange) {
        this.directExchange = directExchange;
    }

    @PostMapping("/messageA")
    public String sendA(@RequestBody ProducerMessage producerMessage) {
        rabbitTemplate.convertAndSend(directExchange.getName(), Constants.ROUTING_A, producerMessage);
        return "Message Sent";
    }

    @PostMapping("/messageB")
    public String sendB(@RequestBody ProducerMessage producerMessage) {
        rabbitTemplate.convertAndSend(directExchange.getName(), Constants.ROUTING_B, producerMessage);
        return "Message Sent";
    }
}
