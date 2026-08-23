package com.jobtracker.service.impl;

import com.jobtracker.entity.EmailStatus;
import com.jobtracker.event.EmailEvent;
import com.jobtracker.repository.EmailEventRepository;
import com.jobtracker.service.EmailProcessService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class EmailProcessServiceImpl implements EmailProcessService {

    private final EmailEventRepository emailEventRepository;

    public EmailEvent getOrCreate(UUID eventId) {

        return emailEventRepository.findById(eventId)
                .orElseGet(() -> {

                    EmailEvent emailEvent = EmailEvent.builder()
                            .eventId(eventId)
                            .status(EmailStatus.PENDING)
                            .attempts(0)
                            .createdAt(LocalDateTime.now())
                            .build();

                    return emailEventRepository.save(emailEvent);
                });
    }

    public void markProcessing(UUID eventId) {

        EmailEvent emailEvent = emailEventRepository.findById(eventId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Email event not found: " + eventId
                        ));

        emailEvent.setStatus(EmailStatus.PROCESSING);
        emailEvent.setAttempts(emailEvent.getAttempts() + 1);

        emailEventRepository.save(emailEvent);
    }

    public void markSent(UUID eventId) {

        EmailEvent emailEvent = emailEventRepository.findById(eventId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Email event not found: " + eventId
                        ));

        emailEvent.setStatus(EmailStatus.SENT);
        emailEvent.setSentAt(LocalDateTime.now());

        emailEventRepository.save(emailEvent);
    }

    public void markFailed(UUID eventId, String error) {

        EmailEvent emailEvent = emailEventRepository.findById(eventId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Email event not found: " + eventId
                        ));

        emailEvent.setStatus(EmailStatus.FAILED);
        emailEvent.setLastError(error);

        emailEventRepository.save(emailEvent);
    }
}
