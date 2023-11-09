package io.ince.vms.topicconsumer.configuration;

import io.ince.vms.topicconsumer.constants.Constants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(Constants.TOPIC_EXCHANGE);
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
    public Queue queueAll() {
        return new Queue(Constants.QUEUE_ALL);
    }

    @Bean
    public Binding bindA(Queue queueA, TopicExchange topicExchange) {
        return BindingBuilder
                .bind(queueA)
                .to(topicExchange)
                .with(Constants.ROUTING_A);
    }

    @Bean
    public Binding bindB(Queue queueB, TopicExchange topicExchange) {
        return BindingBuilder
                .bind(queueB)
                .to(topicExchange)
                .with(Constants.ROUTING_B);
    }

    @Bean
    public Binding bindAll(Queue queueAll, TopicExchange topicExchange) {
        return BindingBuilder
                .bind(queueAll)
                .to(topicExchange)
                .with(Constants.QUEUE_ALL);
    }

    @Bean
    MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
