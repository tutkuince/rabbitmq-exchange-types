package io.ince.vms.directconsumer.controller;

import io.ince.vms.directconsumer.constants.Constants;
import io.ince.vms.directconsumer.model.ConsumerMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ConsumerController {

    @RabbitListener(queues = Constants.QUEUE_A)
    public void receiveA(ConsumerMessage consumerMessage) {
        log.info("Message from Queue A => {}", consumerMessage);
    }

    @RabbitListener(queues = Constants.QUEUE_B)
    public void receiveB(ConsumerMessage consumerMessage) {
        log.info("Message from Queue B => {}", consumerMessage);
    }
}
