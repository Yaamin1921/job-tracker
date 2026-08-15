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

        TopicExchange exchange =
                new TopicExchange(EXCHANGE);

        TopicExchange dlx =
                new TopicExchange("dead-letter.exchange");


        Queue jobQueue =
                createQueue(JOB_QUEUE, "job.dlq");

        Queue statusQueue =
                createQueue(STATUS_QUEUE, "status.dlq");

        Queue notesQueue =
                createQueue(NOTES_QUEUE, "notes.dlq");

        Queue emailQueue =
                createQueue(Email_QUEUE, "email.dlq");


        Binding jobBinding =
                BindingBuilder.bind(jobQueue)
                        .to(exchange)
                        .with("job.*");

        Binding statusBinding =
                BindingBuilder.bind(statusQueue)
                        .to(exchange)
                        .with("status.*");

        Binding notesBinding =
                BindingBuilder.bind(notesQueue)
                        .to(exchange)
                        .with("notes.*");

        Binding emailBinding =
                BindingBuilder.bind(emailQueue)
                        .to(exchange)
                        .with("email.*");


        // DLQs
        Queue jobDlq =
                new Queue("job.dlq");

        Queue statusDlq =
                new Queue("status.dlq");

        Queue notesDlq =
                new Queue("notes.dlq");

        Queue emailDlq =
                new Queue("email.dlq");


        // DLQ bindings
        Binding jobDlqBinding =
                BindingBuilder.bind(jobDlq)
                        .to(dlx)
                        .with("job.dlq");

        Binding statusDlqBinding =
                BindingBuilder.bind(statusDlq)
                        .to(dlx)
                        .with("status.dlq");

        Binding notesDlqBinding =
                BindingBuilder.bind(notesDlq)
                        .to(dlx)
                        .with("notes.dlq");

        Binding emailDlqBinding =
                BindingBuilder.bind(emailDlq)
                        .to(dlx)
                        .with("email.dlq");


        return new Declarables(
                exchange,
                dlx,

                jobQueue,
                statusQueue,
                notesQueue,
                emailQueue,

                jobDlq,
                statusDlq,
                notesDlq,
                emailDlq,

                jobBinding,
                statusBinding,
                notesBinding,
                emailBinding,

                jobDlqBinding,
                statusDlqBinding,
                notesDlqBinding,
                emailDlqBinding
        );
    }

    private Queue createQueue(String queueName, String dlqRoutingKey) {

        return QueueBuilder
                .durable(queueName)
                .withArgument(
                        "x-dead-letter-exchange",
                        "dead-letter.exchange"
                )
                .withArgument(
                        "x-dead-letter-routing-key",
                        dlqRoutingKey
                )
                .build();
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

