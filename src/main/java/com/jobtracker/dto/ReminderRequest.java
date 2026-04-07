package com.jobtracker.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jobtracker.entity.ReminderType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
public class ReminderRequest {
    private Long jobId;

    private OffsetDateTime reminderTime;

    @Schema(
            description = "Reminder type",
            allowableValues = {"FOLLOW_UP", "INTERVIEW"}
    )
    private ReminderType type;


}
