package io.ince.vms.topicproducer.controller;

import io.ince.vms.topicproducer.constants.Constants;
import io.ince.vms.topicproducer.model.ProducerMessage;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {

    private final TopicExchange topicExchange;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public TopicController(TopicExchange topicExchange, RabbitTemplate rabbitTemplate) {
        this.topicExchange = topicExchange;
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/A")
    public String sendA(@RequestBody ProducerMessage producerMessage) {
        rabbitTemplate.convertAndSend(topicExchange.getName(), Constants.ROUTING_A, producerMessage);
        return "Message A sent";
    }
    @PostMapping("/B")
    public String sendB(@RequestBody ProducerMessage producerMessage) {
        rabbitTemplate.convertAndSend(topicExchange.getName(), Constants.ROUTING_B, producerMessage);
        return "Message B sent";
    }
}
