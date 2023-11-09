package io.ince.vms.topicconsumer.controller;

import io.ince.vms.topicconsumer.constants.Constants;
import io.ince.vms.topicconsumer.model.ConsumerMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TopicController {

    @RabbitListener(queues = Constants.QUEUE_A)
    public void receiveA(ConsumerMessage consumerMessage) {
        log.info("Message from queue A => {}", consumerMessage);
    }

    @RabbitListener(queues = Constants.QUEUE_B)
    public void receiveB(ConsumerMessage consumerMessage) {
        log.info("Message from queue B => {}", consumerMessage);
    }

    @RabbitListener(queues = Constants.QUEUE_ALL)
    public void receiveAll(ConsumerMessage consumerMessage) {
        log.info("Message from queue All => {}", consumerMessage);
    }
}
