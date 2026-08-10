package com.jobtracker.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {


    // Exchange
    public static final String EXCHANGE = "jobtracker.exchange";

    // Queues
    public static final String JOB_QUEUE = "job.queue";
    public static final String STATUS_QUEUE = "status.queue";
    public static final String NOTES_QUEUE = "notes.queue";
    public static final String Email_QUEUE = "job.email.queue";

    // Routing Keys
    public static final String JOB_CREATED = "job.created";
    public static final String JOB_UPDATED = "job.updated";
    public static final String JOB_DELETED = "job.deleted";
    public static final String NOTES_CREATED = "notes.created";


    @Bean
    public Declarables rabbitMQDeclarables() {

        Queue jobQueue = new Queue(JOB_QUEUE);
        Queue statusQueue = new Queue(STATUS_QUEUE);
        Queue notesQueue = new Queue(NOTES_QUEUE);
        Queue emailQueue=new Queue(Email_QUEUE);

        TopicExchange exchange = new TopicExchange(EXCHANGE);

        return new Declarables(
                exchange,
                jobQueue,
                statusQueue,
                notesQueue,
                emailQueue,

                BindingBuilder.bind(jobQueue)
                        .to(exchange)
                        .with("job.*"),

                BindingBuilder.bind(statusQueue)
                        .to(exchange)
                        .with("status.*"),

                BindingBuilder.bind(notesQueue)
                        .to(exchange)
                        .with("notes.*"),

                BindingBuilder.bind(emailQueue)
                        .to(exchange)
                        .with("job.*")
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
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory) {

        SimpleRabbitListenerContainerFactory factory =
                new SimpleRabbitListenerContainerFactory();

        factory.setConnectionFactory(connectionFactory);

        factory.setDefaultRequeueRejected(false);

        factory.setAdviceChain(
                RetryInterceptorBuilder
                        .stateless()
                        .maxAttempts(3)
                        .backOffOptions(
                                2000,
                                2.0,
                                10000
                        )
                        .recoverer(new RejectAndDontRequeueRecoverer())
                        .build()
        );

        return factory;
    }
}

