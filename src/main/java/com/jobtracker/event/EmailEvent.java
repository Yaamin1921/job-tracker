package com.jobtracker.event;

import com.jobtracker.entity.EmailStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "email_events")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailEvent {

    @Id
    private UUID eventId;

    @Enumerated(EnumType.STRING)
    private EmailStatus status;

    private int attempts;

    private LocalDateTime createdAt;

    private LocalDateTime sentAt;

    private String lastError;
}