package com.jobtracker.publisher;

import com.jobtracker.config.RabbitMQConfig;
import com.jobtracker.event.NotesEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotesEventPublisher {
    private final RabbitTemplate rabbitTemplate;

    public void publishNoteCreate(NotesEvent event) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.NOTES_ADDED_EXCHANGE,
                RabbitMQConfig.NOTES_ADDED_ROUTING_KEY,
                event
        );
    }
}
