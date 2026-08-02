package com.jobtracker.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobStatusUpdateEvent implements Serializable {
    private Long jobId;
    private String currentStatus;
    private String newStatus;
    private LocalDateTime createdAt;

}
