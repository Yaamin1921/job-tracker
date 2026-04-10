package com.jobtracker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Table(name = "reminders")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long jobId;

    private OffsetDateTime reminderTime;

    @Enumerated(EnumType.STRING)
    private ReminderType type;

    @Enumerated(EnumType.STRING)
    private ReminderStatus status;

    private String email;
}
