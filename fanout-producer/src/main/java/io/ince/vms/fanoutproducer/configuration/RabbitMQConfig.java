package io.ince.vms.fanoutproducer.configuration;

import io.ince.vms.fanoutproducer.constants.Constants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.BatchingRabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(Constants.FANOUT_EXCHANGE);
    }

    @Bean
    public Queue queueA() {
        return new Queue(Constants.QUEUE_A);
    }
    @Bean
    public Queue queueB() {
        return new Queue(Constants.QUEUE_B);
    }
    @Bean
    public Binding bindA(Queue queueA, FanoutExchange fanoutExchange) {
        return BindingBuilder
                .bind(queueA)
                .to(fanoutExchange);
    }
    @Bean
    public Binding bindB(Queue queueB, FanoutExchange fanoutExchange) {
        return BindingBuilder
                .bind(queueB)
                .to(fanoutExchange);
    }
    @Bean
    MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
