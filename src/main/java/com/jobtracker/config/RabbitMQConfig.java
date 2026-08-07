package com.jobtracker.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;

public class RabbitMQConfig {

    private RabbitMQConfig() {}

    // Exchange
    public static final String EXCHANGE = "jobtracker.exchange";

    // Queues
    public static final String JOB_QUEUE = "job.queue";
    public static final String STATUS_QUEUE = "status.queue";
    public static final String NOTES_QUEUE = "notes.queue";

    // Routing Keys
    public static final String JOB_CREATED = "job.created";
    public static final String JOB_UPDATED = "job.updated";
    public static final String JOB_DELETED = "job.deleted";

    public static final String STATUS_CREATED = "status.created";
    public static final String STATUS_UPDATED = "status.updated";

    public static final String NOTES_CREATED = "notes.created";
    public static final String NOTES_UPDATED = "notes.updated";
    public static final String NOTES_DELETED = "notes.deleted";

    @Bean
    public Declarables rabbitMQDeclarables() {

        Queue jobQueue = new Queue(JOB_QUEUE);
        Queue statusQueue = new Queue(STATUS_QUEUE);
        Queue notesQueue = new Queue(NOTES_QUEUE);

        TopicExchange exchange = new TopicExchange(EXCHANGE);

        return new Declarables(
                exchange,
                jobQueue,
                statusQueue,
                notesQueue,

                BindingBuilder.bind(jobQueue)
                        .to(exchange)
                        .with("job.*"),

                BindingBuilder.bind(statusQueue)
                        .to(exchange)
                        .with("status.*"),

                BindingBuilder.bind(notesQueue)
                        .to(exchange)
                        .with("notes.*")
        );
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         MessageConverter messageConverter) {

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

   /* @Bean
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
    }*/
}

