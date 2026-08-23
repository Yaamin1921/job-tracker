package com.jobtracker.service;

import com.jobtracker.event.EmailEvent;

import java.util.UUID;

public interface EmailProcessService {
    EmailEvent getOrCreate(UUID uuid);
    void markProcessing(UUID uuid);
    void markSent(UUID uuid);
    void markFailed(UUID uuid,String error);
}
