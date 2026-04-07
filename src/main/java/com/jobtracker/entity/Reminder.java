package com.jobtracker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reminders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long jobId;

    private LocalDateTime reminderTime;

    @Enumerated(EnumType.STRING)
    private ReminderType type;

    @Enumerated(EnumType.STRING)
    private ReminderStatus status;
}
