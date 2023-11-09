package io.ince.vms.fanoutconsumer.controller;

import io.ince.vms.fanoutconsumer.constants.Constants;
import io.ince.vms.fanoutconsumer.model.ConsumerMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FanoutController {
    @RabbitListener(queues = {Constants.QUEUE_A, Constants.QUEUE_B})
    public void receive(ConsumerMessage consumerMessage) {
        log.info("Message from queue => {}", consumerMessage);
    }
}
