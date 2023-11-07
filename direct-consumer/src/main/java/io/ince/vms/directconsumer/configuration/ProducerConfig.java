package io.ince.vms.directconsumer.configuration;

import io.ince.vms.directconsumer.constants.Constants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerConfig {

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(Constants.DIRECT_EXCHANGE);
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
    MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Binding bindA(Queue queueA, DirectExchange directExchange) {
        return BindingBuilder
                .bind(queueA)
                .to(directExchange)
                .with(Constants.ROUTING_A);
    }

    @Bean
    public Binding bindB(Queue queueB, DirectExchange directExchange) {
        return BindingBuilder
                .bind(queueB)
                .to(directExchange)
                .with(Constants.ROUTING_B);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
