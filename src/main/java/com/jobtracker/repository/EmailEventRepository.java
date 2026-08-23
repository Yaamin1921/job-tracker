package com.jobtracker.repository;

import com.jobtracker.event.EmailEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailEventRepository extends JpaRepository<EmailEvent, UUID> {
}
