package com.jobtracker.publisher;

import com.jobtracker.config.RabbitMQConfig;
import com.jobtracker.event.JobCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobEventPublisher {
    private final RabbitTemplate rabbitTemplate;

    public void publish(JobCreatedEvent event) {

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                event
        );
    }
}
