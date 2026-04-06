package com.jobtracker.dto;

import com.jobtracker.entity.ActivityType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDto {
    private Long id;
    private Long jobId;
    @Enumerated(EnumType.STRING)
    private ActivityType action;

    private String notes;

    private LocalDateTime timestamp;
}
