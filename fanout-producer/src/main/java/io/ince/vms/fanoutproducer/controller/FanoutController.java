package io.ince.vms.fanoutproducer.controller;

import io.ince.vms.fanoutproducer.constants.Constants;
import io.ince.vms.fanoutproducer.model.ProducerMessage;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FanoutController {
    private final FanoutExchange fanoutExchange;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public FanoutController(FanoutExchange fanoutExchange, RabbitTemplate rabbitTemplate) {
        this.fanoutExchange = fanoutExchange;
        this.rabbitTemplate = rabbitTemplate;
    }
    @PostMapping("/message")
    public String send(@RequestBody ProducerMessage producerMessage) {
        rabbitTemplate.convertAndSend(fanoutExchange.getName(), null, producerMessage);
        return Constants.MESSAGE_SENT;
    }
}
