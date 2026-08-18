package com.jobtracker.service.impl;

import com.jobtracker.entity.ProcessedEvent;
import com.jobtracker.repository.ProcessedEventRepository;
import com.jobtracker.service.ProcessedEventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProcessedEventServiceImpl implements ProcessedEventService {
    private ProcessedEventRepository processedEventRepository;
    public boolean isAlreadyProcessed(UUID eventId) {

        if (processedEventRepository.existsById(eventId)) {
            log.info("Event {} already processed", eventId);
            return true;
        }

        return false;
    }
    public void markAsProcessed(UUID eventId) {
        processedEventRepository.save(ProcessedEvent.builder().eventId(eventId).processedAt(LocalDateTime.now()).build());
    }
}
