package com.jobtracker.publisher;

import com.jobtracker.config.RabbitMQConfig;
import com.jobtracker.event.JobCreatedEvent;
import com.jobtracker.event.JobDeleteEvent;
import com.jobtracker.event.JobStatusUpdateEvent;
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
    public void publishJobStatusUpdate(JobStatusUpdateEvent jobStatusUpdateEvent){
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.STATUS_EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                jobStatusUpdateEvent);
    }

    public void publishJobDeletedEvent(JobDeleteEvent jobDeleteEvent) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.JOB_DELETED_EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                jobDeleteEvent);
    }
}
