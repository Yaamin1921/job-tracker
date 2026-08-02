package com.jobtracker.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;



public class RabbitMQConfig {
    public static final String EXCHANGE = "job.exchange";
    public static final String QUEUE = "job.created.queue";
    public static final String ROUTING_KEY = "job.created";
    public static final String STATUS_EXCHANGE = "job.status_exchange";
    public static final String STATUS_QUEUE = "job.created.status_queue";
    public static final String STATUS_ROUTING_KEY = "job.status_created";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE);
    }
    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE);
    }
    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(ROUTING_KEY);
    }
    @Bean
    public Queue status_Queue() {
        return new Queue(STATUS_QUEUE);
    }

    @Bean
    public DirectExchange status_Exchange() {
        return new DirectExchange(STATUS_EXCHANGE);
    }
    @Bean
    public Binding status_Binding() {
        return BindingBuilder
                .bind(queue())
                .to(status_Exchange())
                .with(STATUS_ROUTING_KEY);
    }


    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);

        rabbitTemplate.setMessageConverter(converter());

        return rabbitTemplate;
    }


}

