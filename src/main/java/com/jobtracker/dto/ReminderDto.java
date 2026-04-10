package com.jobtracker.dto;

import com.jobtracker.entity.ReminderStatus;
import com.jobtracker.entity.ReminderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReminderDto {
    private Long jobId;
    private OffsetDateTime reminderTime;
    private ReminderType type;
    private ReminderStatus status;
    private String email;
}
