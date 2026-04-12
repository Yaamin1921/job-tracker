package com.jobtracker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Table(name = "reminders",schema="job_tracker")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="job_id")
    private Long jobId;

    @Column(name="reminder_time")
    private OffsetDateTime reminderTime;

    @Column(name="type")
    @Enumerated(EnumType.STRING)
    private ReminderType type;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private ReminderStatus status;

    @Column(name="email")
    private String email;
}
