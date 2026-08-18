package com.jobtracker.service;

import java.util.UUID;

public interface ProcessedEventService {
    boolean isAlreadyProcessed(UUID eventId);
    void markAsProcessed(UUID eventId);
}
